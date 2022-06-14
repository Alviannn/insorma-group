package dev.juviga.insorma.data.model;

public class User {

    private int id;
    private String username;
    private final String emailAddress;
    private final String phoneNumber;
    private final String password;

    public User(int id, String username, String emailAddress, String phoneNumber, String password) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
