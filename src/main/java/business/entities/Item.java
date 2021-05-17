package business.entities;

public class Item {

   private String info;
    private String name;
    private double price;
    private int id;
    private int quantity=1;
    private int lenght;
    private int width;
    public Item(int id, String name, double price, int lenght, int width) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.width = width;
        this.lenght = lenght;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setId(int id) {
        this.id = id;
    }
}
