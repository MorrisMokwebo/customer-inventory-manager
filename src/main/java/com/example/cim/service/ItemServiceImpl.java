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

        if(itemExists(item)){
            throw  new RuntimeException("Item with name: "+item.getItemName()+" already exists");
        }

        return itemRepository.save(item);
    }

    @Override
    public Item updateItemDetails(Item item, Long itemId) {

        Optional<Item> existingItem = getItem(itemId);

        if(existingItem.isPresent()){
            itemRepository.save(item);
        }

        throw  new RuntimeException("Item with id: "+itemId+" does not exists");
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

    private boolean itemExists(Item item){
       Item existingItem = itemRepository.findByItemName(item.getItemName());

       if(existingItem == null){
           return  true;
       }
       return false;
    }

}
