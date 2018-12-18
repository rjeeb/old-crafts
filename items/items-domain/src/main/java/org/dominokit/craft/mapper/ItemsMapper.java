package org.dominokit.craft.mapper;

import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.model.Item;

public class ItemsMapper {

    public static ItemsMapper create() {
        return new ItemsMapper();
    }

    public ItemResource toResource(Item item) {
        ItemResource itemResource = new ItemResource();
        itemResource.setTitle(item.getTitle());
        itemResource.setDescription(item.getDescription());
        itemResource.setAmount(item.getPrice());
        return itemResource;
    }

    public Item fromResource(ItemResource itemResource) {
        return null;
    }
}
