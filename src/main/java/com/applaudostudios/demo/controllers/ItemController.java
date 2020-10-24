package com.applaudostudios.demo.controllers;

import com.applaudostudios.demo.config.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.config.exceptions.ItemDeleteException;
import com.applaudostudios.demo.config.exceptions.ItemNotFoundException;
import com.applaudostudios.demo.controllers.request.ItemSearchByRequest;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.request.PaginationRequest;
import com.applaudostudios.demo.controllers.request.UpdateItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import com.applaudostudios.demo.services.ItemService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/app/item")
@RequiredArgsConstructor
public class ItemController {

    @NonNull
    private final ItemService itemService;

    /**
     * Method to create an Item
     * @param itemRequest
     * @return ItemResponse
     * @throws ItemAlreadyCreatedException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse createOrFail(@Valid @RequestBody ItemRequest itemRequest) throws ItemAlreadyCreatedException {
        return itemService.createItem(itemRequest);
    }

    /**
     * Method to update an specific item
     * @param updateItemRequest
     * @param itemId
     * @return
     * @throws ItemNotFoundException
     */
    @PutMapping("/{itemId}")
    public ItemResponse updateOrFail(@Valid @RequestBody UpdateItemRequest updateItemRequest, @PathVariable Long itemId) throws ItemNotFoundException {
        return itemService.updateItem(updateItemRequest, itemId);
    }

    /**
     * Method to delete an specific item
     * @param itemId
     * @throws ItemNotFoundException
     */
    @DeleteMapping("/{itemId}")
    public void deleteOrFail(@PathVariable Long itemId) throws ItemDeleteException {
        itemService.deleteItem(itemId);
    }

    /**
     * Method to delete all Items
     */
    @DeleteMapping
    public void deleteAll() {
        itemService.deleteAll();
    }

    /**
     * Method to get an specific Item
     * @param itemId
     * @return
     * @throws ItemNotFoundException
     */
    @GetMapping("/{itemId}")
    public ItemResponse getItem(@PathVariable Long itemId) throws ItemNotFoundException {
        return itemService.getItem(itemId);
    }

    /**
     * Method to get All Items
     * @return
     */
    @GetMapping
    public Page<ItemResponse> getItem() {
        return itemService.getAllItems();
    }

    /**
     * Method to get All Items created by an user and with a specific status
     * @param itemSearchByRequest
     * @return
     */
    @GetMapping(params = { "itemStatus" , "itemEnteredByUser"})
    public Page<ItemResponse> getItemSearchByAttributes(@Valid ItemSearchByRequest itemSearchByRequest) {
        return itemService.getAllItemsByFilter(itemSearchByRequest);
    }

    /**
     * Method to get Items paginated
     * @param paginationRequest
     * @return
     */
    @GetMapping(params = { "pageSize" , "page", "sortBy"})
    public Page<ItemResponse> getItemSearchByAttributes(@Valid PaginationRequest paginationRequest) {
        return itemService.getAllItemsPaginated(paginationRequest);
    }


}
