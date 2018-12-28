package org.dominokit.craft.layout.client.views;

import org.dominokit.craft.shared.model.Category;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;
import org.dominokit.domino.api.client.mvp.view.View;
import org.dominokit.domino.api.shared.extension.Content;

import java.util.function.Consumer;

import static org.dominokit.craft.layout.client.views.LayoutView.LayoutViewUiHandlers;

public interface LayoutView extends View, HasUiHandlers<LayoutViewUiHandlers> {
    void show();

    void addPrimaryAction(String actionTitle, ActionHandler actionHandler);

    void addSecondaryAction(String actionTitle, ActionHandler actionHandler);

    void setSearchHandler(Consumer<String> searchValueConsumer);

    void setContent(Content content);

    void addFooterCategory(String category);

    void addFooterCategoryItem(String category, String itemTitle, String url);

    void addCategory(Category category, Consumer<Category> categorySelectionHandler);

    @FunctionalInterface
    interface ActionHandler {
        void onActionExecuted();
    }

    interface LayoutViewUiHandlers extends UiHandlers {

        void onNewItemRequested();
    }
}