package com.example.cim.service;

import com.example.cim.model.Item;
import com.example.cim.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item saveItem(Item item) {

        if(itemExists(item.getItemName())){
            throw  new RuntimeException("Item with name: "+item.getItemName()+" already exists");
        }

        return itemRepository.save(item);
    }

    @Override
    public Item updateItemDetails(Item item, Long itemId) {

        Optional<Item> existingItem = getItem(itemId);

        if(existingItem.isEmpty()){
            throw  new RuntimeException("Item with id: "+itemId+" does not exists");
        }

        existingItem.get().setItemName(item.getItemName());
        existingItem.get().setDescription(item.getDescription());
        existingItem.get().setPrice(item.getPrice());
        existingItem.get().setQuantityAvailable(item.getQuantityAvailable());

        return itemRepository.save(existingItem.get());
    }

    private Optional<Item> getItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public List<Item> retrieveAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public void removeItemById(Long itemId) {
        Optional<Item> existingItem = getItem(itemId);

        if (existingItem.isEmpty()){
            throw  new RuntimeException("Item with id: "+itemId+" does not exists");
        }
        itemRepository.deleteById(itemId);
    }

    @Override
    public Item retrieveItemById(Long itemId) {
        Optional<Item> existingItem = getItem(itemId);

        if (existingItem.isEmpty()){
            throw  new RuntimeException("Item with id: "+itemId+" does not exists");
        }
        return existingItem.get();
    }

    private boolean itemExists(String itemName){
       Item existingItem = itemRepository.findByItemName(itemName);
        return existingItem != null;
    }

}
