package org.dominokit.craft.repository;

import org.dominokit.craft.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestItemsRepository implements ItemsRepository {

    private Map<String, Item> items = new HashMap<>();

    @Override
    public void save(Item item) {
        items.put(item.getName(), item);
    }

    @Override
    public List<Item> getAll() {
        if (items.isEmpty()) {
            return null;
        }
        return new ArrayList<>(items.values());
    }
}
