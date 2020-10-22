package com.applaudostudios.demo.services;

import com.applaudostudios.demo.config.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.config.exceptions.ItemNotFoundException;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;

public interface ItemService {
    ItemResponse saveItem(ItemRequest itemRequest) throws ItemAlreadyCreatedException;
    ItemResponse updateItem(ItemRequest itemRequest, Long itemId) throws ItemNotFoundException;
}
