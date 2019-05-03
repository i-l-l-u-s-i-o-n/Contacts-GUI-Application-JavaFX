package shivam.contacts_window;

import shivam.authentication.User_Authentication;
import shivam.dataModel.ContactDataModel;
import shivam.dataModel.ContactsDataSource;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;

public class Contacts_Controller {



    ObservableList<ContactDataModel> contacts=FXCollections.observableArrayList();


    @FXML
    private TableView table;
    @FXML
    private TableColumn fName;
    @FXML
    private TableColumn lName;
    @FXML
    private TableColumn MNO;
    @FXML
    private TableColumn eMail;
    @FXML
    private BorderPane border;



    ContactsDataSource dataSource=new ContactsDataSource();




    public void initialize(){
//        contacts.add(new Contact("Shivam","Shukla","9999014429","shivamshukla2297@gmail.com"));
//        contacts.add(new Contact("Tim","Buchalka","876947332","timbuchalka@gmail.com"));
//        contacts.add(new Contact("Andrew","Milton","787765445","milton34@gmail.com"));

        dataSource.createTableContact();
        for (ContactDataModel contact:dataSource.queryContact(User_Authentication.uid)){
            contacts.add(contact);
        }

        fName.setSortable(true);
        fName.setSortType(TableColumn.SortType.ASCENDING);
        fName.setCellValueFactory(new PropertyValueFactory<Contact,String>("f_name"));
        lName.setCellValueFactory(new PropertyValueFactory<Contact,String>("l_name"));
        MNO.setCellValueFactory(new PropertyValueFactory<Contact,String>("m_no"));
        eMail.setCellValueFactory(new PropertyValueFactory<Contact,String>("email"));
        table.setItems(contacts);

        fName.setCellFactory(column -> {
            return new TableCell<Contact, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {

                        setText(item);

                        // Style all dates in March with a different color.
//                        if (item.compareTo("Shivam")==0) {
//                            setTextFill(Color.CHOCOLATE);
//                            setStyle("-fx-background-color: yellow");
//                        } else {
//                            setTextFill(Color.BLACK);
//                            setStyle("");
//                        }
                    }
                }
            };
        });

        table.setRowFactory(
                new Callback<TableView<Contact>, TableRow<Contact>>() {
                    @Override
                    public TableRow<Contact> call(TableView<Contact> tableView) {
                        final TableRow<Contact> row = new TableRow<>();
                        final ContextMenu rowMenu = new ContextMenu();
                        MenuItem editItem = new MenuItem("Edit");
                        editItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                showOnEditDialog((ContactDataModel) table.getSelectionModel().getSelectedItem());
                            }
                        });
                        MenuItem removeItem = new MenuItem("Delete");
                        removeItem.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                int index=contacts.indexOf(table.getSelectionModel().getSelectedItem());
                                dataSource.deleteContact(contacts.get(index).getM_no());
                                contacts.remove(table.getSelectionModel().getSelectedItem());
                                //table.getItems().remove(row.getItem());
                                table.setItems(contacts);
                            }
                        });
                        rowMenu.getItems().addAll(editItem,removeItem);

                        // only display context menu for non-null items:
                        row.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                        .then(rowMenu)
                                        .otherwise((ContextMenu)null));
                        return row;
                    }
                });

    }


    @FXML
    public void showDilogPane(){
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.initOwner(border.getScene().getWindow());

        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("dilog.fxml"));
        try {

            // It won't work as to add item , we can't create direct instance of DilogController Class.
            // So we have to getController from the dialog.fxml file.

            //Parent root=FXMLLoader.load(getClass().getResource("dilog.fxml"));
            //dialog.getDialogPane().setContent(root);


            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Couldn't load DialogPane !!");
            e.printStackTrace();
        }

        dialog.setTitle("Add ContactDataModel ");

        ButtonType addButton =new ButtonType("ADD");
        dialog.getDialogPane().getButtonTypes().add(addButton);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result=dialog.showAndWait();
        if (result.isPresent() && result.get()==addButton){
            DialogController dialogController=fxmlLoader.getController();
            ContactDataModel contact=dialogController.getNewData();
            contacts.add(contact);
            if (User_Authentication.uid!=-1){

                dataSource.insertData(contact.getF_name(),contact.getL_name(),contact.getM_no(),contact.getEmail(),User_Authentication.uid);
            }
            table.setItems(contacts);
            fName.setSortType(TableColumn.SortType.ASCENDING);
        }


    }


    public void handleEXIT(){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit Contacts ");
        alert.setTitle("Exit");
        alert.setContentText("Are you sure to EXIT ?");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.OK){
            System.exit(0);
        }
    }


    @FXML
    public void showOnEditDialog(ContactDataModel contact){
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.initOwner(border.getScene().getWindow());
        dialog.setTitle("Edit ContactDataModel");
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("dilog.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Can't load Dialog pane !");
            e.printStackTrace();
        }

        ButtonType save=new ButtonType("Save");
        dialog.getDialogPane().getButtonTypes().add(save);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController=fxmlLoader.getController();
//        ContactDataModel oldContact=(ContactDataModel) table.getSelectionModel().getSelectedItem();
        dialogController.editContact(contact);
//        dialogController.editContact((ContactDataModel) table.getSelectionModel().getSelectedItem());

        Optional<ButtonType> result=dialog.showAndWait();
        if (result.isPresent()&& result.get()==save){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure to update an existing contact ?");
            alert.setTitle("Confirm update");
            alert.setHeaderText("Save changes?");
            Optional<ButtonType> result1=alert.showAndWait();
            if (result1.isPresent() && result1.get()==ButtonType.OK){

                ContactDataModel updatedContact=dialogController.getNewData();
                contacts.add(contacts.indexOf(contact),updatedContact);
                contacts.remove(contact);
                dataSource.updateContactData(updatedContact,contact.getM_no());

//                ContactDataModel updatedContact=(ContactDataModel)table.getSelectionModel().getSelectedItem();
//                dataSource.deleteContact(updatedContact.getM_no());
//                dataSource.updateContactData(updatedContact,oldContact.getM_no());
//                dialogController.updateContact(updatedContact);
//                dataSource.insertData(updatedContact.getF_name(),updatedContact.getL_name(),updatedContact.getM_no(),updatedContact.getEmail());


            }
        }
    }







}
