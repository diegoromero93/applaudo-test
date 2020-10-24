package com.applaudostudios.demo.services.impl;

import com.applaudostudios.demo.config.exceptions.ItemAlreadyCreatedException;
import com.applaudostudios.demo.config.exceptions.ItemNotFoundException;
import com.applaudostudios.demo.controllers.request.ItemSearchByRequest;
import com.applaudostudios.demo.controllers.request.ItemRequest;
import com.applaudostudios.demo.controllers.request.PaginationRequest;
import com.applaudostudios.demo.controllers.request.UpdateItemRequest;
import com.applaudostudios.demo.controllers.response.ItemResponse;
import com.applaudostudios.demo.enums.ItemStatusEnum;
import com.applaudostudios.demo.models.Item;
import com.applaudostudios.demo.repositories.ItemRepository;
import com.applaudostudios.demo.services.ItemService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @NonNull
    private final ItemRepository itemRepository;

    @NonNull
    private final ModelMapper modelMapper;

    @Override
    public ItemResponse saveItem(ItemRequest itemRequest) throws ItemAlreadyCreatedException{
        Item item = getModelFromRequest(itemRequest);

        if(itemRepository.existsByIdOrName(itemRequest.getId(), itemRequest.getName()))
            throw new ItemAlreadyCreatedException("Item with id: " + item.getId() + " or with name: " + item.getName() + " already exist");

        Item newItem = itemRepository.saveAndFlush(item);
        return getResponseFromModel(newItem);
    }

    @Override
    public ItemResponse updateItem(final UpdateItemRequest updateItemRequest, final Long itemId) throws ItemNotFoundException {

        final  Item item = itemRepository.findById(itemId)
                .orElseThrow( () -> new ItemNotFoundException("Item with id: " + itemId  + " does not exist"));

        item.setName(updateItemRequest.getName());
        item.setBuyingPrice(updateItemRequest.getBuyingPrice());
        item.setSellingPrice(updateItemRequest.getSellingPrice());
        item.setStatus(ItemStatusEnum.getItemStatusEnum(updateItemRequest.getStatus().getCode()));
        Item updatedItem = itemRepository.saveAndFlush(item);

        return getResponseFromModel(updatedItem);
    }

    @Override
    public void deleteItem(Long itemId) throws ItemNotFoundException {
        final  Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item with id: " + itemId  + " does not exist"));
        itemRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }

    @Override
    public ItemResponse getItem(Long itemId) throws ItemNotFoundException {
        final Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item with id: " + itemId  + " does not exist"));
        return getResponseFromModel(item);
    }

    @Override
    public Page<ItemResponse> getAllItemsByFilter(ItemSearchByRequest itemSearchByRequest) {
        String status = itemSearchByRequest.getItemStatus() != null ? itemSearchByRequest.getItemStatus().getCode() : null;
        String enteredBy = itemSearchByRequest.getItemEnteredByUser() != null ? itemSearchByRequest.getItemEnteredByUser() : null;

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

    private Item getModelFromRequest(ItemRequest itemRequest){
        return modelMapper.map(itemRequest, Item.class);
    }

    private ItemResponse getResponseFromModel(Item item){
        final ItemResponse response = modelMapper.map(item, ItemResponse.class);
        response.setEnteredByUser(item.getAudit().getEnteredByUser());
        response.setEnteredDate(item.getAudit().getEnteredDate());
        response.setLastModifiedByUser(item.getAudit().getLastModifiedByUser());
        response.setLastModifiedDate(item.getAudit().getLastModifiedDate());
        return response;
    }
}
