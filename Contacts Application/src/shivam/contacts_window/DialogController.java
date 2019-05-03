package shivam.contacts_window;

import shivam.dataModel.ContactDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DialogController  {

    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField mno;
    @FXML
    private TextField email;

    public ContactDataModel getNewData(){
        String fn=fname.getText();
        String ln=lname.getText();
        String mn=mno.getText();
        String em=email.getText();
        return new ContactDataModel(fn,ln,mn,em);
    }

    public void editContact(ContactDataModel contact){
        fname.setText(contact.getF_name());
        lname.setText(contact.getL_name());
        mno.setText(contact.getM_no());
        email.setText(contact.getEmail());

    }
}
