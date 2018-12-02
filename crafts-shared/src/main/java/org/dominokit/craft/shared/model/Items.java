package org.dominokit.craft.shared.model;

import org.dominokit.domino.api.shared.request.ResponseBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

import java.util.ArrayList;
import java.util.List;

@JSONMapper
public class Items implements ResponseBean {

    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
