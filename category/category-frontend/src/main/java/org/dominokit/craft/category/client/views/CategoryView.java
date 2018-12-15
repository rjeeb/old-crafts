package org.dominokit.craft.category.client.views;

import org.dominokit.craft.items.shared.model.Item;
import org.dominokit.craft.shared.model.Filters;
import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;

import java.util.Collection;
import java.util.List;

public interface CategoryView extends ContentView, HasUiHandlers<CategoryView.CategoryViewUiHandlers> {
    void setTitle(String title);

    void viewFilters(Collection<String> filters);

    void unSelectFilter(String filter);

    void setItems(List<Item> items);

    interface CategoryViewUiHandlers extends UiHandlers {
        void onFiltersChanged(List<Filters> filters);

        void onFilterRemoved(String filter);
    }
}