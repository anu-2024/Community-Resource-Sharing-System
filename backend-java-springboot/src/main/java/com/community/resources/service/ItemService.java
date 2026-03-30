
package com.community.resources.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.community.resources.model.Item;
import com.community.resources.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository repo;

    public ItemService(ItemRepository repo){
        this.repo = repo;
    }

    public Item addItem(Item item){
        item.setStatus("Available");
        return repo.save(item);
    }

    public List<Item> getAll(){
        return repo.findAll();
    }

    public List<Item> getByCategory(String category){
        return repo.findByCategory(category);
    }

    public Item updateStatus(Long id,String status){
        Item item = repo.findById(id).orElseThrow();
        item.setStatus(status);
        return repo.save(item);
    }
}
