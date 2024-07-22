package com.example.cim.service;

import com.example.cim.model.Item;

import java.util.List;

public interface ItemService {
    Item saveItem(Item item);
    Item updateItemDetails(Item item, Long itemId);
    List<Item> retrieveAllItem();
    void removeItemById(Long itemId);
    Item retrieveItemById(Long itemId);
}
