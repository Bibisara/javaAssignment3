package kz.aitu.entities;

import java.util.Date;

public class Medicine {
    private int id;
    private String name;
    private int price;
    private Date expiration;
    private String manufacturer;
    private String type;
    private int userId;

    public Medicine(){

    }

    public Medicine(String name, int price, Date expiration, String manufacturer, String type, int userId){
        setName(name);
        setPrice(price);
        setExpiration(expiration);
        setManufacturer(manufacturer);
        setType(type);
        setUserId(userId);
    }

    public Medicine(int id, String name, int price, Date expiration, String manufacturer, String type, int userId){
        setId(id);
        setName(name);
        setPrice(price);
        setExpiration(expiration);
        setManufacturer(manufacturer);
        setType(type);
        setUserId(userId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public java.sql.Date getExpiration() {
        return (java.sql.Date) expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() { return userId; }

    public void setUserId(int user_id) { this.userId = userId; }

    @Override
    public String toString(){
        return "Medicine{ id: " + id
                + "; name: " + name
                + "; price: " + price
                + "; expiration date: " + expiration
                + "; manufacturer: " + manufacturer
                + "; type of medicine: " + type
                + "; user id: " + userId
                + " }";
    }

}
