package dev.gafilianog.insorma.data.model;

public class User {

    private final int id;
    private final String phoneNumber;
    private final String password;
    private final String emailAddress;
    private String username;

    public User(int id, String phoneNumber, String password, String emailAddress, String username) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.emailAddress = emailAddress;
        this.username = username;
    }

    public int getId() {
        return id;
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
