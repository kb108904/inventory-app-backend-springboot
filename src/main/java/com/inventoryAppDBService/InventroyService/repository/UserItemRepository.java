package com.inventoryAppDBService.InventroyService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.inventoryAppDBService.InventroyService.model.UserItem;

public interface UserItemRepository extends MongoRepository<UserItem, String>{

    List<UserItem> findAllByOwnerId(String ownerId);
}
