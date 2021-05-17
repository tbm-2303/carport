package business.entities;

import java.util.List;

public class User {

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    private int id;
    private String email;
    private String password;
    private String role;
    private boolean contactPing = false; // sæt til true hvis kunden ønsker en konsultation.
    private List<Requesty> offerList;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addOfferToList(Requesty offer) {
        offerList.add(offer);
    }

    public boolean isContactPing() {
        return contactPing;
    }

    public List<Requesty> getOfferList() {
        return offerList;
    }

    public void setContactPing(boolean contactPing) {
        this.contactPing = contactPing;
    }

    public void setOfferList(List<Requesty> offerList) {
        this.offerList = offerList;
    }
}


