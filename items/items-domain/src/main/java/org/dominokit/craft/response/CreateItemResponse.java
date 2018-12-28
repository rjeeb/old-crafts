package org.dominokit.craft.response;

import org.dominokit.craft.items.shared.model.ItemResource;

public class CreateItemResponse {
    private ItemResource createdItem;

    public ItemResource getCreatedItem() {
        return createdItem;
    }

    public void setCreatedItem(ItemResource createdItem) {
        this.createdItem = createdItem;
    }
}
