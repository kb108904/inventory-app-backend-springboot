package com.inventoryAppDBService.InventroyService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.inventoryAppDBService.InventroyService.model.UserInventory;

public interface UserInventoryRepository extends MongoRepository<UserInventory, String>{
    
}
