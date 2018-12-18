package org.dominokit.craft.category.client.presenters;

import org.dominokit.craft.category.client.requests.Requests;
import org.dominokit.craft.category.client.views.CategoryView;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.layout.shared.extension.CategorySelectedEvent;
import org.dominokit.craft.layout.shared.extension.CategorySelectedEventContext;
import org.dominokit.craft.layout.shared.extension.LayoutContentChangeEvent;
import org.dominokit.craft.shared.model.Category;
import org.dominokit.craft.shared.model.Filters;
import org.dominokit.domino.api.client.annotations.ListenTo;
import org.dominokit.domino.api.client.annotations.Presenter;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.domino.api.client.mvp.view.DominoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Presenter
public class CategoryPresenter extends ViewBaseClientPresenter<CategoryView> implements CategoryView.CategoryViewUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryPresenter.class);
    private Category category;

    @ListenTo(event = CategorySelectedEvent.class)
    public void listenToCategorySelectedEvent(CategorySelectedEventContext context) {
        category = context.getCategory();
        revealView();
    }

    private void revealView() {
        fireEvent(LayoutContentChangeEvent.class, () -> () -> view.getContent());
    }

    @Override
    public DominoView.RevealedHandler onRevealed() {
        return () -> {
            view.setTitle(category.getTitle());
            loadItems();
        };
    }

    private void loadItems() {
        Requests.categoryRequests()
                .list()
                .onSuccess(response -> {
                    List<ItemResource> itemResources = response.getItems();
                    view.setItems(itemResources);
                    view.setItems(itemResources);
                    view.setItems(itemResources);
                    view.setItems(itemResources);
                    view.setItems(itemResources);
                    view.setItems(itemResources);
                    view.setItems(itemResources);
                    view.setItems(itemResources);
                })
                .onFailed(failedResponse -> {

                })
                .send();
    }

    @Override
    public void onFiltersChanged(List<Filters> filters) {
        List<String> collect = filters.stream()
                .map(f -> f.getFilters().keySet())
                .flatMap(Set::stream)
                .collect(Collectors.toList());
        view.viewFilters(collect);
    }

    @Override
    public void onFilterRemoved(String filter) {
        view.unSelectFilter(filter);
    }
}