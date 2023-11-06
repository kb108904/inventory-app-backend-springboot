package com.inventoryAppDBService.InventroyService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.inventoryAppDBService.InventroyService.model.Inventory;

public interface InventoryRepository extends MongoRepository<Inventory, String>{

}
