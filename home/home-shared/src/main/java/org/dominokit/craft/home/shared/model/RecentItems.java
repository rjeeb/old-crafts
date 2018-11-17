package org.dominokit.craft.home.shared.model;

import org.dominokit.domino.api.shared.request.ResponseBean;

import java.util.ArrayList;
import java.util.List;

public class RecentItems implements ResponseBean {

    private List<RecentItem> recentItems = new ArrayList<>();

    public List<RecentItem> getRecentItems() {
        return recentItems;
    }

    public void setRecentItems(List<RecentItem> recentItems) {
        this.recentItems = recentItems;
    }
}
