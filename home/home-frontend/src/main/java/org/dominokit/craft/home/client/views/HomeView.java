package org.dominokit.craft.home.client.views;

import org.dominokit.craft.home.shared.model.Feature;
import org.dominokit.craft.home.shared.model.GiftCategory;
import org.dominokit.craft.home.shared.model.Preview;
import org.dominokit.craft.home.shared.model.Review;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.client.mvp.view.ContentView;

public interface HomeView extends ContentView {
    void setHeader(String header);

    void setPreview(Preview preview);

    void addFeature(Feature feature);

    void addRecentItem(ItemResource recentItemResource);

    void setRecentItemsHeader(String recentItemsHeader);

    void addGiftCategory(GiftCategory category);

    void setGiftsCategoryHeader(String header);

    void setReviewHeader(String header);

    void addReview(Review review);
}