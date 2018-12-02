package org.dominokit.craft.home.client.presenters;

import org.dominokit.craft.home.client.requests.Requests;
import org.dominokit.craft.home.client.views.HomeView;
import org.dominokit.craft.home.shared.model.Feature;
import org.dominokit.craft.home.shared.model.GiftCategory;
import org.dominokit.craft.home.shared.model.Review;
import org.dominokit.craft.layout.shared.extension.LayoutContentChangeEvent;
import org.dominokit.craft.shared.model.Item;
import org.dominokit.domino.api.client.annotations.ListenTo;
import org.dominokit.domino.api.client.annotations.Presenter;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.domino.api.client.mvp.view.DominoView;
import org.dominokit.domino.api.shared.extension.MainDominoEvent;
import org.dominokit.domino.api.shared.extension.MainEventContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Presenter
public class HomePresenter extends ViewBaseClientPresenter<HomeView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePresenter.class);

    @ListenTo(event = MainDominoEvent.class)
    public void listenToMainEvent(MainEventContext context) {
        revealHomePage();
    }

    private void revealHomePage() {
        fireEvent(LayoutContentChangeEvent.class, () -> () -> view.getContent());
    }

    @Override
    public DominoView.RevealedHandler onRevealed() {
        return () -> {
            view.setHeader("If it’s handcrafted, vintage, custom, or unique, it’s on Craft.");
            fetchNews();
            fetchFeatures();
            fetchRecentItems();
            fetchGiftsCategories();
            fetchReviews();
        };
    }

    private void fetchReviews() {
        Requests.reviewRequests().list()
                .onSuccess(response -> {
                    view.setReviewHeader("Recent reviews from happy people");
                    List<Review> reviews = response.asList();
                    for (Review review : reviews) {
                        view.addReview(review);
                    }

                }).onFailed(failedResponse -> {

        }).send();
    }

    private void fetchGiftsCategories() {
        Requests.giftCategoryRequests().list()
                .onSuccess(response -> {
                    view.setGiftsCategoryHeader("Shop for gifts");
                    List<GiftCategory> categories = response.asList();
                    for (GiftCategory category : categories) {
                        view.addGiftCategory(category);
                    }
                }).onFailed(failedResponse -> {

        }).send();
    }

    private void fetchRecentItems() {
        Requests.recentItemsRequests().list()
                .onSuccess(response -> {
                    view.setRecentItemsHeader("Recently viewed & more");
                    List<Item> recentItems = response.asList();
                    for (Item recentItem : recentItems) {
                        view.addRecentItem(recentItem);
                    }
                }).onFailed(failedResponse -> {

        }).send();
    }

    private void fetchFeatures() {
        Requests.featuresRequests().list()
                .onSuccess(response -> {
                    List<Feature> features = response.asList();
                    for (Feature feature : features) {
                        view.addFeature(feature);
                    }
                }).onFailed(failedResponse -> {

        }).send();
    }

    private void fetchNews() {
        Requests.previewRequests().get()
                .onSuccess(response -> {
                    view.setPreview(response);
                }).onFailed(failedResponse -> {

        }).send();
    }
}
