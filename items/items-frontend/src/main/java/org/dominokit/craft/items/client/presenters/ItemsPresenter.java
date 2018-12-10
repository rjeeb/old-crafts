package org.dominokit.craft.items.client.presenters;

import org.dominokit.craft.items.client.views.ItemsView;
import org.dominokit.craft.layout.shared.extension.CategorySelectedEvent;
import org.dominokit.craft.layout.shared.extension.CategorySelectedEventContext;
import org.dominokit.domino.api.client.annotations.ListenTo;
import org.dominokit.domino.api.client.annotations.Presenter;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Presenter
public class ItemsPresenter extends ViewBaseClientPresenter<ItemsView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemsPresenter.class);

}