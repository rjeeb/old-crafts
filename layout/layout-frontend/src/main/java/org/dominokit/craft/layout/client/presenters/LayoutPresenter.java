package org.dominokit.craft.layout.client.presenters;

import org.dominokit.craft.layout.client.views.LayoutView;
import org.dominokit.craft.layout.shared.extension.LayoutContentChangeEvent;
import org.dominokit.craft.layout.shared.extension.LayoutContentChangeEventContext;
import org.dominokit.domino.api.client.annotations.ListenTo;
import org.dominokit.domino.api.client.annotations.Presenter;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.domino.api.shared.extension.MainDominoEvent;
import org.dominokit.domino.api.shared.extension.MainEventContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Presenter
public class LayoutPresenter extends ViewBaseClientPresenter<LayoutView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LayoutPresenter.class);

    @Override
    protected void initView(LayoutView view) {
        view.addPrimaryAction("Sign in", () -> {
            LOGGER.info("sign in clicked");
        });
        view.addSecondaryAction("Register", () -> {

        });
        view.addSecondaryAction("Sell on Craft", () -> {

        });

        view.setSearchHandler(searchValue -> {

        });

        view.addFooterCategory("Shop");
        view.addFooterCategoryItem("Shop", "Gift cards", "url");
        view.addFooterCategoryItem("Shop", "Crafts blog", "url");
        view.addFooterCategory("Sell");
        view.addFooterCategoryItem("Sell", "Sell on Crafts", "url");
        view.addFooterCategoryItem("Sell", "Teams", "url");
        view.addFooterCategoryItem("Sell", "Forums", "url");
        view.addFooterCategory("About");
        view.addFooterCategoryItem("About", "Crafts, Inc.", "url");
        view.addFooterCategoryItem("About", "Policies", "url");
        view.addFooterCategoryItem("About", "Investors", "url");
        view.addFooterCategoryItem("About", "Careers", "url");
        view.addFooterCategoryItem("About", "Press", "url");
        view.addFooterCategoryItem("About", "Legal imprint", "url");
        view.addFooterCategory("Follow Crafts");
        view.addFooterCategoryItem("Follow Crafts", "Facebook", "url");
        view.addFooterCategoryItem("Follow Crafts", "Twitter", "url");
        view.addFooterCategoryItem("Follow Crafts", "Pinterest", "url");
        view.addFooterCategoryItem("Follow Crafts", "Instagram", "url");

        view.addMenuItem("Jewelry & Accessories");
        view.addMenuItem("Clothing & Shoes");
        view.addMenuItem("Home & Living");
        view.addMenuItem("Wedding & Party");
        view.addMenuItem("Toys & Entertainment");
        view.addMenuItem("Art & Collectibles");
        view.addMenuItem("Craft Supplies & Tools");
        view.addMenuItem("Vintage");
    }

    @ListenTo(event = MainDominoEvent.class)
    public void listenToMainEvent(MainEventContext context) {
        view.show();
    }

    @ListenTo(event = LayoutContentChangeEvent.class)
    public void listenToLayoutContentChangeEvent(LayoutContentChangeEventContext contentChangeEventContext) {
        view.setContent(contentChangeEventContext.getContent());
    }
}