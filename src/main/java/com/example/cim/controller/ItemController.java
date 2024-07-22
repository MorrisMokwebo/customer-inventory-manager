package com.example.cim.controller;

import com.example.cim.model.Item;
import com.example.cim.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/save")
    public Item saveItem(@RequestBody Item item){
        return itemService.saveItem(item);
    }

    @GetMapping("/all")
    public List<Item> getAllItems(){
        return itemService.retrieveAllItem();
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable Long itemId){
        return itemService.retrieveItemById(itemId);
    }

    @DeleteMapping("/{itemId}")
    public void removeItemById(@PathVariable Long itemId){
        itemService.removeItemById(itemId);
    }

    @PutMapping("/update/{itemId}")
    public Item updateItemDetails(@RequestBody Item item, @PathVariable Long itemId){
        return itemService.updateItemDetails(item,itemId);
    }

}
