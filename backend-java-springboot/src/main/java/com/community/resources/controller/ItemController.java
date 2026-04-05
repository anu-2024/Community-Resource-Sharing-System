
package com.community.resources.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.community.resources.model.Item;
import com.community.resources.service.ItemService;

@RestController
@RequestMapping("/api/items")
@CrossOrigin
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }

    @PostMapping
    public Item addItem(@RequestBody Item item){
        return service.addItem(item);
    }

    @GetMapping
    public List<Item> getAll(){
        return service.getAll();
    }

    @GetMapping("/category/{cat}")
    public List<Item> getByCategory(@PathVariable String cat){
        return service.getByCategory(cat);
    }

   @PutMapping("/{id}/status")
public Item updateStatus(
        @PathVariable Long id,
        @RequestParam String status,
        @RequestParam String ownerName){

    Item item = service.getItemById(id);

    // Only owner can update item status
    if(!item.getOwnerName().equals(ownerName)){
        throw new RuntimeException("Only the owner can update item status");
    }

    return service.updateStatus(id,status);
}
}
