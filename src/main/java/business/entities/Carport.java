package business.entities;

import java.util.List;

public class Carport {


    public Carport(double price, int length, int width, int shed_length, int shed_width, String roof_type, String info)
    {
        this.price = price;
        this.length = length;
        this.width = width;
        this.shed_length = shed_length;
        this.shed_width = shed_width;
        this.roof_type = roof_type;
        this.info = info;
    }

    private int id;
    private double selling_price;
    private double profit;
    private List<Item> itemList;
    private String image_text;

    private double price;
    private int length;
    private int width;
    private int shed_length;
    private int shed_width;
    private String roof_type;
    private String info;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getRoof_type() {
        return roof_type;
    }

    public void setRoof_type(String roof_type) {
        this.roof_type = roof_type;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String getImage_text() {
        return image_text;
    }

    public void setImage_text(String image_text) {
        this.image_text = image_text;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getShed_width() {
        return shed_width;
    }

    public void setShed_width(int shed_width) {
        this.shed_width = shed_width;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
