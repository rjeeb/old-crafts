package org.dominokit.craft.repository;

import org.dominokit.craft.model.ImmutableItem;
import org.dominokit.craft.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryItemsRepository implements ItemsRepository {

    private Map<String, Item> items = new HashMap<>();

    public InMemoryItemsRepository() {
        items.put("item1", createItem("item1"));
        items.put("item2", createItem("item2"));
        items.put("item3", createItem("item3"));
        items.put("item4", createItem("item4"));
    }

    private Item createItem(String name) {
        return ImmutableItem
                .builder()
                .title(name)
                .description("Item with name " + name + " has description")
                .price(450.98)
                .build();
    }

    @Override
    public Item save(Item item) {
        items.put(item.getTitle(), item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items.values());
    }
}
