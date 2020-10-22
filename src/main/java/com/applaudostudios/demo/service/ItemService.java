package com.applaudostudios.demo.service;

import com.applaudostudios.demo.configuration.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.configuration.exceptions.ItemNotFoundException;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;

public interface ItemService {
    ItemResponse saveItem(ItemRequest itemRequest) throws ItemAlreadyCreatedException;
    ItemResponse updateItem(ItemRequest itemRequest, Long itemId) throws ItemNotFoundException;
}
