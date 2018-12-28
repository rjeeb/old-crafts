package org.dominokit.craft.repository;

import org.dominokit.craft.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryItemsRepository implements ItemsRepository {

    private Map<String, Item> items = new HashMap<>();

    @Override
    public Item save(Item item) {
        items.put(item.title(), item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Item findByTitle(String title) {
        return items.getOrDefault(title, null);
    }
}
