
package com.community.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.community.resources.model.Item;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategory(String category);
}
