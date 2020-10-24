package com.applaudostudios.demo.services;

import com.applaudostudios.demo.config.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.config.exceptions.ItemDeleteException;
import com.applaudostudios.demo.config.exceptions.ItemNotFoundException;
import com.applaudostudios.demo.controllers.request.ItemSearchByRequest;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.request.PaginationRequest;
import com.applaudostudios.demo.controllers.request.UpdateItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import org.springframework.data.domain.Page;

public interface ItemService {

    /**
     * Creates am Item
     * @param itemRequest
     * @return ItemRequest of the created Item
     * @throws ItemAlreadyCreatedException
     */
    ItemResponse createItem(ItemRequest itemRequest) throws ItemAlreadyCreatedException;

    /**
     * Updates an Item
     * @param updateItemRequest
     * @param itemId
     * @return ItemRequest of the updated Item
     * @throws ItemNotFoundException
     */
    ItemResponse updateItem(UpdateItemRequest updateItemRequest, Long itemId) throws ItemNotFoundException;

    /**
     * Deletes an Item of the specified id
     * @param itemId
     * @throws ItemDeleteException
     */
    void deleteItem(Long itemId)  throws ItemDeleteException;

    /**
     * Deletes all Items
     */
    void deleteAll();

    /**
     * Get an Item of the specified id
     * @param itemId
     * @return
     * @throws ItemNotFoundException
     */
    ItemResponse getItem(Long itemId) throws ItemNotFoundException;

    /**
     * Get an Item with filters
     * @param itemSearchByRequest
     * @return
     */
    Page<ItemResponse> getAllItemsByFilter(ItemSearchByRequest itemSearchByRequest);

    /**
     * Get an Item with filters
     * @param paginationRequest
     * @return
     */
    Page<ItemResponse> getAllItemsPaginated(PaginationRequest paginationRequest);

    /**
     * Get all Items
     * @return
     */
    Page<ItemResponse> getAllItems();
}
