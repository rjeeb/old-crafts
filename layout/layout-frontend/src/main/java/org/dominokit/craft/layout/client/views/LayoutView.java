package org.dominokit.craft.layout.client.views;

import org.dominokit.domino.api.client.mvp.view.View;
import org.dominokit.domino.api.shared.extension.Content;

import java.util.function.Consumer;

public interface LayoutView extends View {
    void show();

    void addPrimaryAction(String actionTitle, ActionHandler actionHandler);

    void addSecondaryAction(String actionTitle, ActionHandler actionHandler);

    void setSearchHandler(Consumer<String> searchValueConsumer);

    void setContent(Content content);

    void addFooterCategory(String category);

    void addFooterCategoryItem(String category, String itemTitle, String url);

    void addMenuItem(String menuTitle);

    @FunctionalInterface
    interface ActionHandler {
        void onActionExecuted();
    }
}