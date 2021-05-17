package business.entities;

import java.util.List;

public class Requesty {

    private int id;
    private int width;
    private int length;
    private int shed_length;
    private int shed_width;
    private double price;
    private double profit;
    private User user;
    private List<Item> itemList;


    public Requesty(int width, int length, int shed_length, int shed_width, User user, List<Item> itemList) {
        this.width = width;
        this.length = length;
        this.shed_length = shed_length;
        this.shed_width = shed_width;
        this.price = price;
        this.profit = profit;
        this.user = user;
        id = user.getId();
        this.itemList = itemList;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getShed_length() {
        return shed_length;
    }

    public void setShed_length(int shed_length) {
        this.shed_length = shed_length;
    }

    public int getShed_width() {
        return shed_width;
    }

    public void setShed_width(int shed_width) {
        this.shed_width = shed_width;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
