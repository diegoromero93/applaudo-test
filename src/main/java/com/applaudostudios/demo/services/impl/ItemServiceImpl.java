package com.applaudostudios.demo.services.impl;

import com.applaudostudios.demo.config.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.config.exceptions.ItemNotFoundException;
import com.applaudostudios.demo.controllers.request.ItemSearchByRequest;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.request.PaginationRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import com.applaudostudios.demo.enums.ItemStatusEnum;
import com.applaudostudios.demo.models.Item;
import com.applaudostudios.demo.models.User;
import com.applaudostudios.demo.repositories.ItemRepository;
import com.applaudostudios.demo.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

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

        Item item = itemRepository.findById(itemId)
                .orElseThrow( () -> new ItemNotFoundException("Item with id: " + itemId  + " does not exist"));

        item.setName(itemRequest.getName());
        item.setBuyingPrice(itemRequest.getBuyingPrice());
        item.setSellingPrice(itemRequest.getSellingPrice());
        item.setStatus(ItemStatusEnum.getItemStatusEnum(itemRequest.getStatus()));
        item.setLastModifiedByUser(getCurrentUser());
        Item updatedItem = itemRepository.saveAndFlush(item);

        return getRequestFromModel(updatedItem);
    }

    @Override
    public void deleteItem(Long itemId) throws ItemNotFoundException {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item with id: " + itemId  + " does not exist"));
        itemRepository.delete(item);

    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }

    @Override
    public ItemResponse getItem(Long itemId) throws ItemNotFoundException {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item with id: " + itemId  + " does not exist"));
        return getRequestFromModel(item);
    }

    @Override
    public Page<ItemResponse> getAllItemsByFilter(ItemSearchByRequest itemSearchByRequest) {
        String status = itemSearchByRequest.getItemStatus() != null ? itemSearchByRequest.getItemStatus().getCode() : null;
        Long enteredBy = itemSearchByRequest.getItemEnteredByUser() != null ? itemSearchByRequest.getItemEnteredByUser() : null;

        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Item> pagedResult = itemRepository.findItemsByRequest(pageable, status, enteredBy);

        return new PageImpl<>(pagedResult.stream()
                .map(item -> modelMapper.map(item, ItemResponse.class))
                .collect(Collectors.toList()), pageable, pagedResult.getTotalElements());
    }

    @Override
    public Page<ItemResponse> getAllItemsPaginated(PaginationRequest paginationRequest) {

        Integer itemsPerPage = paginationRequest.getPageSize();
        Integer pageNo = paginationRequest.getPage();
        Sort.Order sort = new Sort.Order(Sort.Direction.ASC, paginationRequest.getSortBy());

        Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(sort));

        Page<Item> pagedResult = itemRepository.findItemsByRequest(pageable, null, null);
        return new PageImpl<>(pagedResult.stream()
                .map(item -> modelMapper.map(item, ItemResponse.class))
                .collect(Collectors.toList()), pageable, pagedResult.getTotalElements());
    }

    @Override
    public Page<ItemResponse> getAllItems() {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Item> pagedResult = itemRepository.findItemsByRequest(pageable, null, null);
        return new PageImpl<>(pagedResult.stream()
                .map(item -> modelMapper.map(item, ItemResponse.class))
                .collect(Collectors.toList()), pageable, pagedResult.getTotalElements());
    }


    private User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    private Item getModelFromRequest(ItemRequest wordRequest){
        return modelMapper.map(wordRequest, Item.class);
    }

    private ItemResponse getRequestFromModel(Item item){
        return modelMapper.map(item, ItemResponse.class);
    }
}
