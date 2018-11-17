package org.dominokit.craft.home.client.views;

import org.dominokit.craft.home.shared.model.*;
import org.dominokit.domino.api.client.mvp.view.ContentView;

public interface HomeView extends ContentView {
    void setHeader(String header);

    void setPreview(Preview preview);

    void addFeature(Feature feature);

    void addRecentItem(RecentItem recentItem);

    void setRecentItemsHeader(String recentItemsHeader);

    void addGiftCategory(GiftCategory category);

    void setGiftsCategoryHeader(String header);

    void setReviewHeader(String header);

    void addReview(Review review);
}