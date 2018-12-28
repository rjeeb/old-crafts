package org.dominokit.craft.items.client.presenters;

import org.dominokit.craft.items.client.requests.ItemsRequestsFactory;
import org.dominokit.craft.items.client.views.ItemsView;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.layout.shared.extension.LayoutContentChangeEvent;
import org.dominokit.craft.layout.shared.extension.NewItemEvent;
import org.dominokit.craft.layout.shared.extension.NewItemEventContext;
import org.dominokit.domino.api.client.annotations.ListenTo;
import org.dominokit.domino.api.client.annotations.Presenter;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.dominokit.craft.items.client.views.ItemsView.ItemsViewUiHandlers;

@Presenter
public class ItemsPresenter extends ViewBaseClientPresenter<ItemsView> implements ItemsViewUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemsPresenter.class);

    @ListenTo(event = NewItemEvent.class)
    public void listenToNewItemEvent(NewItemEventContext context) {
        fireEvent(LayoutContentChangeEvent.class, () -> () -> view.getContent());
    }

    @Override
    public void onCreate() {
        ItemResource itemResource = view.save();
        ItemsRequestsFactory.INSTANCE
                .create(itemResource)
                .onSuccess(response -> {
                    LOGGER.info("created");
                })
                .onFailed(failedResponse -> {
                    LOGGER.error("failed to create", failedResponse.getThrowable());
                })
                .send();
    }
}