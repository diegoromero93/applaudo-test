package com.applaudostudios.demo.controllers;

import com.applaudostudios.demo.configuration.exceptions.BadRequestException;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import com.applaudostudios.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/app/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String initial( @RequestBody ItemRequest itemRequest) {
        return "asdfaf";
        //return itemService.saveItem(itemRequest);
    }
}
