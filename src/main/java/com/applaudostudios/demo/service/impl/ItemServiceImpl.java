package com.applaudostudios.demo.service.impl;

import com.applaudostudios.demo.configuration.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.configuration.exceptions.ItemNotFoundException;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import com.applaudostudios.demo.enums.ItemStatusEnum;
import com.applaudostudios.demo.models.Item;
import com.applaudostudios.demo.models.User;
import com.applaudostudios.demo.repositories.ItemRepository;
import com.applaudostudios.demo.repositories.UserRepository;
import com.applaudostudios.demo.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemResponse saveItem(ItemRequest itemRequest) throws ItemAlreadyCreatedException{
        Item item = getModelFromRequest(itemRequest);

        if(itemRepository.existsByIdOrName(itemRequest.getId(), itemRequest.getName()))
            throw new ItemAlreadyCreatedException("Item with id: " + item.getId() + " or with name: " + item.getName() + " already exist");

        item.setEnteredByUser(getCurrentUser());
        Item newItem = itemRepository.saveAndFlush(item);
        return getRequestFromModel(newItem);
    }

    @Override
    public ItemResponse updateItem(ItemRequest itemRequest, Long itemId) throws ItemNotFoundException {
        if(!itemRepository.existsById(itemId))
            throw new ItemNotFoundException("Item with id: " + itemId  + " does not exist");

        Item item = itemRepository.findById(itemId).get();
        item.setName(itemRequest.getName());
        item.setBuyingPrice(itemRequest.getBuyingPrice());
        item.setSellingPrice(itemRequest.getSellingPrice());
        item.setStatus(ItemStatusEnum.getItemStatusEnum(itemRequest.getStatus()));
        item.setLastModifiedByUser(getCurrentUser());
        Item updatedItem = itemRepository.saveAndFlush(item);

        return getRequestFromModel(updatedItem);
    }


    private User getCurrentUser(){
        return userRepository.getOne(1l);
    }
    private Item getModelFromRequest(ItemRequest wordRequest){
        return modelMapper.map(wordRequest, Item.class);
    }

    private ItemResponse getRequestFromModel(Item item){
        return modelMapper.map(item, ItemResponse.class);
    }
}
