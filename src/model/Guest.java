package model;

public class Guest {

    private String guestName;
    private String phoneNumber;
    private String email;
    private String idProof;

    public Guest(String guestName, String phoneNumber, String email, String idProof) {
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idProof = idProof;
    }

    // Getters
    public String getGuestName() {
        return guestName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getIdProof() {
        return idProof;
    }

    // Setters
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    @Override
    public String toString() {
        return "Guest Details\n" +
               "-----------------------------\n" +
               "Name         : " + guestName + "\n" +
               "Phone Number : " + phoneNumber + "\n" +
               "Email        : " + email + "\n" +
               "ID Proof     : " + idProof + "\n" +
               "-----------------------------";
    }
}