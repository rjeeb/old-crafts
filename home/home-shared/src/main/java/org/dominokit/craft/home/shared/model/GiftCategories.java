package org.dominokit.craft.home.shared.model;

import org.dominokit.domino.api.shared.request.ResponseBean;

import java.util.ArrayList;
import java.util.List;

public class GiftCategories implements ResponseBean {
    private List<GiftCategory> categories = new ArrayList<>();

    public List<GiftCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<GiftCategory> categories) {
        this.categories = categories;
    }
}
