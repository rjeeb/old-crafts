package org.dominokit.craft.repository;

import org.dominokit.craft.model.Item;

import java.util.List;

public interface ItemsRepository {
    void save(Item item);

    List<Item> getAll();
}
