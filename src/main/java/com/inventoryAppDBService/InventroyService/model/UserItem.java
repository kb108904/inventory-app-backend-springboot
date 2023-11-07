package com.inventoryAppDBService.InventroyService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userItem")
public class UserItem {
    @Id
    private String id;
    private String ownerId;
    private String itemId;
    private String name;
    private String description;
    private int quantity;
    private String img;

    public UserItem(){

    }

    @Override
    public String toString() {
        return "UserItem [id=" + id + ", ownerId=" + ownerId + ", itemId=" + itemId + ", name=" + name
                + ", description=" + description + ", quantity=" + quantity + ", img=" + img + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    
}
