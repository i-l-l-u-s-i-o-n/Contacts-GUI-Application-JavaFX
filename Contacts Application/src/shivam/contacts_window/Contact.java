package shivam.contacts_window;

import javafx.beans.property.SimpleStringProperty;


public class Contact {

    private SimpleStringProperty fname;
    private SimpleStringProperty lname;
    private SimpleStringProperty mno;
    private SimpleStringProperty email;

    public Contact(String fname, String lname, String mno, String email) {
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.mno = new SimpleStringProperty(mno);
        this.email =new SimpleStringProperty(email);
    }

    public String getFname() {
        return fname.get();
    }

    public SimpleStringProperty fnameProperty() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getLname() {
        return lname.get();
    }

    public SimpleStringProperty lnameProperty() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public String getMno() {
        return mno.get();
    }

    public SimpleStringProperty mnoProperty() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno.set(mno);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
