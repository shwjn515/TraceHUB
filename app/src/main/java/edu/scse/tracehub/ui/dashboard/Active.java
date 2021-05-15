package edu.scse.tracehub.ui.dashboard;

public class Active {
    String a_id;
    String user;
    String text;
    public Active(String a_id, String user, String text) {
        this.a_id = a_id;
        this.user = user;
        this.text = text;
    }

    public Active() {
    }

    public String getA_id() {
        return a_id;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }
}
