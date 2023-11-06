package com.inventoryAppDBService.InventroyService.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserInventory")
public class Inventory {
    private List<UserInventory> inventory;

    public Inventory() {
    }

    public List<UserInventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<UserInventory> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Inventory [inventory=" + inventory + "]";
    }
       
}
