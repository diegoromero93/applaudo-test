package com.applaudostudios.demo.service.impl;

import com.applaudostudios.demo.configuration.exceptions.BadRequestException;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import com.applaudostudios.demo.models.Item;
import com.applaudostudios.demo.repositories.ItemRepository;
import com.applaudostudios.demo.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemResponse saveItem(ItemRequest itemRequest) throws BadRequestException {
        Item item = getModelFromRequest(itemRequest);
        if(itemRepository.existsById(item.getId())) throw new BadRequestException("Item with id: " + item.getId() + " already exist");
        itemRepository.save(item);
        return getRequestFromModel(item);
    }

    private Item getModelFromRequest(ItemRequest wordRequest){
        return modelMapper.map(wordRequest, Item.class);
    }

    private ItemResponse getRequestFromModel(Item item){
        return modelMapper.map(item, ItemResponse.class);
    }
}
