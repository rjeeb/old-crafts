package org.dominokit.craft.items.client.views;

import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;

import static org.dominokit.craft.items.client.views.ItemsView.ItemsViewUiHandlers;

public interface ItemsView extends ContentView, HasUiHandlers<ItemsViewUiHandlers> {

    ItemResource save();

    interface ItemsViewUiHandlers extends UiHandlers {

        void onCreate();

        void onRemoveImage(ImageResource imageResource);
    }
}