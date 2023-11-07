package com.inventoryAppDBService.InventroyService.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryAppDBService.InventroyService.model.UserItem;
import com.inventoryAppDBService.InventroyService.repository.UserItemRepository;

import springfox.documentation.annotations.ApiIgnore;

@RestController
public class InventoryController {

    @Autowired
    UserItemRepository userItemsRepo;

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping(value = "/")
    public String healthCheck() {
        String timeStamp = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").format(Calendar.getInstance().getTime());
        return "ok @ " + timeStamp;
    }

    @GetMapping(value = { "/inventory/", "/inventory/{ownerId}" })
    public List<UserItem> getUsersInventory(@PathVariable(required = false) String ownerId) {
        if (ownerId != null) {
            return userItemsRepo.findAllByOwnerId(ownerId);
        } else {
            return userItemsRepo.findAll();
        }

    }

    @PostMapping("/inventory/{ownerId}/item/{itemId}")
    public String addItem(@PathVariable String ownerId, @PathVariable String itemId, @RequestBody UserItem userItem) {
        userItem.setOwnerId(ownerId);
        userItemsRepo.save(userItem);
        return "Data added";
    }

    @PutMapping("/inventory/{ownerId}/item/{itemId}")
    public String updateItem(@PathVariable String ownerId, @PathVariable String itemId,
            @RequestBody UserItem userItem) {
        if (isExistingItem(itemId)) {
            return "Item already exists";
        } else {
            userItem.setOwnerId(ownerId);
            userItemsRepo.save(userItem);
            return "Item Created";
        }
    }

    private boolean isExistingItem(String itemId) {
        return null != userItemsRepo.findById(itemId);
    }
}
