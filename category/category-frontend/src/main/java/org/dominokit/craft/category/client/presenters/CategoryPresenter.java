package org.dominokit.craft.category.client.presenters;

import org.dominokit.craft.category.client.views.CategoryView;
import org.dominokit.craft.layout.shared.extension.CategorySelectedEvent;
import org.dominokit.craft.layout.shared.extension.CategorySelectedEventContext;
import org.dominokit.craft.layout.shared.extension.LayoutContentChangeEvent;
import org.dominokit.craft.shared.model.Category;
import org.dominokit.domino.api.client.annotations.ListenTo;
import org.dominokit.domino.api.client.annotations.Presenter;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Presenter
public class CategoryPresenter extends ViewBaseClientPresenter<CategoryView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryPresenter.class);

    @ListenTo(event = CategorySelectedEvent.class)
    public void listenToCategorySelectedEvent(CategorySelectedEventContext context) {
        revealView(context.getCategory());
    }

    private void revealView(Category category) {
        fireEvent(LayoutContentChangeEvent.class, () -> () -> view.getContent(() -> {
            view.setTitle(category.getTitle());
        }));
    }
}