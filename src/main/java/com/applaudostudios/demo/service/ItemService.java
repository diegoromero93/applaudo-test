package com.applaudostudios.demo.service;

import com.applaudostudios.demo.configuration.exceptions.BadRequestException;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;

public interface ItemService {
    ItemResponse saveItem(ItemRequest itemRequest) throws BadRequestException;
}
