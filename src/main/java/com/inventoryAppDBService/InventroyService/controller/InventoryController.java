package com.inventoryAppDBService.InventroyService.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryAppDBService.InventroyService.model.UserInventory;
import com.inventoryAppDBService.InventroyService.model.UserItem;
import com.inventoryAppDBService.InventroyService.repository.UserInventoryRepository;
import springfox.documentation.annotations.ApiIgnore;




@RestController
public class InventoryController {

    @Autowired
    UserInventoryRepository userInvenRepo;

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping(value = "/")
    public String healthCheck(){
        String timeStamp = new SimpleDateFormat("HHmmss_yyyyMMdd").format(Calendar.getInstance().getTime());
        return "ok@"+timeStamp;
    }
    
    @GetMapping("/inventory")
    public List<UserInventory> getUsersInventory(){

        return userInvenRepo.findAll();

    }

    @PostMapping("/inventory")
    public  UserInventory addUserInventory(@RequestBody UserInventory userInventor){
        return userInvenRepo.save(userInventor);
    }
        
    // @PatchMapping("/inventory/{id}")
    // public  UserInventory updateUserInventory(@PathVariable String id, @RequestBody UserItem userItem){
    //     UserInventory ui = new UserInventory();
    //     ui.setUserId(id);
    //     return userInvenRepo.findOne(Example.of());
    // }
}
