package com.inventoryAppDBService.InventroyService.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserInventory")
public class UserInventory {

    private String userId;
    private List<UserItem> items;
    
    public UserInventory() {
    }

    @Override
    public String toString() {
        return "UserInventory [userId=" + userId + ", items=" + items + "]";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UserItem> getItems() {
        return items;
    }

    public void setItems(List<UserItem> items) {
        this.items = items;
    }
}
