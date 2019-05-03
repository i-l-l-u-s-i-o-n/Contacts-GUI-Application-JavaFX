package shivam.dataModel;

public class ContactDataModel {
    String f_name;
    String l_name;
    String m_no;
    String email;

    public ContactDataModel(String f_name, String l_name, String m_no, String email) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.m_no = m_no;
        this.email = email;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getM_no() {
        return m_no;
    }

    public void setM_no(String m_no) {
        this.m_no = m_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return f_name+" "+l_name+" "+m_no+" "+email;
    }
}
