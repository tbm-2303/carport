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
    private List<Request> offerList;
    private String number;
    private String adress;
    private String name;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public void addOfferToList(Request offer) {
        offerList.add(offer);
    }

    public boolean isContactPing() {
        return contactPing;
    }

    public List<Request> getOfferList() {
        return offerList;
    }

    public void setContactPing(boolean contactPing) {
        this.contactPing = contactPing;
    }

    public void setOfferList(List<Request> offerList) {
        this.offerList = offerList;
    }
}


