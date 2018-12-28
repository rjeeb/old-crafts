package org.dominokit.craft.repository;

import org.dominokit.craft.model.Item;

import java.util.List;

public interface ItemsRepository {
    Item save(Item item);

    List<Item> findAll();

    Item findByTitle(String title);
}
