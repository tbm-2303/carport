package business.entities;

public class Order {

    public Order(int width, int length, int shed_length, int shed_width, double price,double profit, int user_id, String info, String status)
    {
        this.width = width;
        this.length = length;
        this.shed_length = shed_length;
        this.shed_width = shed_width;
        this.price = price;
        this.profit = profit;
        this.info = info;
        this.user_id = user_id;
        this.status = status;

    }

    private int id; // just used to demo retrieval of autogen keys in UserMapper
    private int width;
    private int length;
    private int shed_length;
    private int shed_width;
    private double price;
    private double profit;
    private int user_id;
    private String timestamp;
    private String info;
    private String status;

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
