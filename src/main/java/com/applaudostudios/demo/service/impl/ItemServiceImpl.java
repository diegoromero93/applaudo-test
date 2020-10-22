package com.applaudostudios.demo.service.impl;

import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import com.applaudostudios.demo.repositories.ItemRepository;
import com.applaudostudios.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Override
    public ItemResponse saveItem(ItemRequest itemRequest) {
        return null;
    }
}
