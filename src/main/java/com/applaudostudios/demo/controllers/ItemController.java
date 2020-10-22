package com.applaudostudios.demo.controllers;

import com.applaudostudios.demo.config.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.config.exceptions.ItemNotFoundException;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import com.applaudostudios.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/app/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse createOrFail(@Valid @RequestBody ItemRequest itemRequest) throws ItemAlreadyCreatedException {
        return itemService.saveItem(itemRequest);
    }

    @PutMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemResponse updateOrFail(@Valid @RequestBody ItemRequest itemRequest, @PathVariable Long itemId) throws ItemNotFoundException {
        return itemService.updateItem(itemRequest, itemId);
    }
}
