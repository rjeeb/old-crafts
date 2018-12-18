package org.dominokit.craft.category.client.requests;

import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.shared.request.ResponseBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

import java.util.ArrayList;
import java.util.List;

@JSONMapper
public class ItemResources implements ResponseBean {
    private List<ItemResource> items = new ArrayList<>();

    public List<ItemResource> getItems() {
        return items;
    }

    public void setItems(List<ItemResource> items) {
        this.items = items;
    }
}
