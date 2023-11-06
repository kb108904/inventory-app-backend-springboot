package com.inventoryAppDBService.InventroyService.model;

public class UserItem {
    private String name;
    private String description;
    private int quantity;
    private String img;

    public UserItem(){

    }

    @Override
    public String toString() {
        return "UserItem [name=" + name + ", description=" + description + ", quantity=" + quantity + ", img=" + img + "]";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    
}
