package com.applaudostudios.demo.controllers;

import com.applaudostudios.demo.config.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.config.exceptions.ItemNotFoundException;
import com.applaudostudios.demo.controllers.request.ItemSearchByRequest;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.request.PaginationRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import com.applaudostudios.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


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

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrFail(@PathVariable Long itemId, Principal principal) throws ItemNotFoundException {
        itemService.deleteItem(itemId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() {
        itemService.deleteAll();
    }

    @GetMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemResponse getItem(@PathVariable Long itemId) throws ItemNotFoundException {
        return itemService.getItem(itemId);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ItemResponse> getItem() {
        return itemService.getAllItems();
    }

    @GetMapping(params = { "itemStatus" , "itemEnteredByUser"})
    @ResponseStatus(HttpStatus.OK)
    public Page<ItemResponse> getItemSearchByAttributes(@Valid ItemSearchByRequest itemSearchByRequest) {
        return itemService.getAllItemsByFilter(itemSearchByRequest);
    }

    @GetMapping(params = { "pageSize" , "page", "sortBy"})
    @ResponseStatus(HttpStatus.OK)
    public Page<ItemResponse> getItemSearchByAttributes(@Valid PaginationRequest paginationRequest) {
        return itemService.getAllItemsPaginated(paginationRequest);
    }


}
