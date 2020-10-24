package com.applaudostudios.demo.services;

import com.applaudostudios.demo.config.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.config.exceptions.ItemNotFoundException;
import com.applaudostudios.demo.controllers.request.ItemSearchByRequest;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.request.PaginationRequest;
import com.applaudostudios.demo.controllers.request.UpdateItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import org.springframework.data.domain.Page;

public interface ItemService {
    ItemResponse saveItem(ItemRequest itemRequest) throws ItemAlreadyCreatedException;
    ItemResponse updateItem(UpdateItemRequest updateItemRequest, Long itemId) throws ItemNotFoundException;
    void deleteItem(Long itemId)  throws ItemNotFoundException;
    void deleteAll();
    ItemResponse getItem(Long itemId) throws ItemNotFoundException;
    Page<ItemResponse> getAllItemsByFilter(ItemSearchByRequest itemSearchByRequest);
    Page<ItemResponse> getAllItemsPaginated(PaginationRequest paginationRequest);
    Page<ItemResponse> getAllItems();
}
