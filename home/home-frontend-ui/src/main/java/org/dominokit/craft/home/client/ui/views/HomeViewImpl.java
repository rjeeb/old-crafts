package org.dominokit.craft.home.client.ui.views;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.dominokit.craft.home.client.presenters.HomePresenter;
import org.dominokit.craft.home.client.ui.components.*;
import org.dominokit.craft.home.client.views.HomeView;
import org.dominokit.craft.home.shared.model.Feature;
import org.dominokit.craft.home.shared.model.GiftCategory;
import org.dominokit.craft.home.shared.model.Preview;
import org.dominokit.craft.home.shared.model.Review;
import org.dominokit.craft.items.client.ui.component.ItemComponent;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.Row_12;
import org.dominokit.domino.ui.loaders.Loader;
import org.dominokit.domino.ui.loaders.LoaderEffect;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.view.BaseElementView;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.h;

@UiView(presentable = HomePresenter.class)
public class HomeViewImpl extends BaseElementView<HTMLDivElement> implements HomeView {

    private DominoElement<HTMLHeadingElement> headerElement;
    private DominoElement<HTMLDivElement> previewContainer;
    private DominoElement<HTMLDivElement> featuresContainer;
    private DominoElement<HTMLDivElement> recentItemsContainer;
    private DominoElement<HTMLDivElement> giftCategoriesContainer;
    private DominoElement<HTMLDivElement> reviewsContainer;
    private Row_12 featuresRow;
    private Row_12 recentItemsRow;
    private Row_12 giftCategoryRow;
    private Row_12 reviewsRow;

    @Override
    public void init(HTMLDivElement root) {
        headerElement = DominoElement.of(h(1));
        previewContainer = DominoElement.of(div());
        featuresContainer = DominoElement.of(div());
        recentItemsContainer = DominoElement.of(div());
        giftCategoriesContainer = DominoElement.of(div());
        reviewsContainer = DominoElement.of(div());
        DominoElement.of(root)
                .appendChild(Row.create()
                        .appendChild(Column.span10()
                                .offset1()
                                .appendChild(headerElement)
                        ))
                .appendChild(Row.create()
                        .appendChild(Column.span10()
                                .offset1()
                                .appendChild(previewContainer)))
                .appendChild(Row.create()
                        .appendChild(Column.span10()
                                .offset1()
                                .appendChild(featuresContainer))
                )
                .appendChild(Row.create()
                        .appendChild(Column.span10()
                                .offset1()
                                .appendChild(recentItemsContainer))
                )
                .appendChild(Row.create()
                        .appendChild(Column.span10()
                                .offset1()
                                .appendChild(giftCategoriesContainer))
                )
                .appendChild(Row.create()
                        .appendChild(Column.span10()
                                .offset1()
                                .appendChild(reviewsContainer))
                );

        featuresRow = Row.create();
        featuresContainer.appendChild(featuresRow);

        previewContainer.styler(style -> style
                .add("preview-container"));

        recentItemsRow = Row.create();
        recentItemsContainer
                .styler(style -> style.setMarginTop("70px"))
                .appendChild(recentItemsRow);

        giftCategoryRow = Row.create();

        giftCategoriesContainer
                .styler(style -> style.setMarginTop("70px"))
                .appendChild(giftCategoryRow);

        reviewsRow = Row.create();
        reviewsContainer
                .styler(style -> style.setMarginTop("70px"))
                .appendChild(reviewsRow);
    }

    @Override
    public HTMLDivElement createRoot() {
        return div().asElement();
    }

    @Override
    public void setHeader(String header) {
        headerElement.setTextContent(header);
    }

    @Override
    public void addFeature(Feature feature) {
        featuresRow
                .appendChild(Column.span4()
                        .centerContent()
                        .appendChild(FeatureComponent.create(feature)));
    }

    @Override
    public void addRecentItem(ItemResource recentItemResource) {
        recentItemsRow.appendChild(Column.span2()
                .appendChild(ItemComponent.create(recentItemResource)));
    }

    @Override
    public void setRecentItemsHeader(String recentItemsHeader) {
        recentItemsContainer.insertFirst(h(2).textContent(recentItemsHeader));
    }

    @Override
    public void addGiftCategory(GiftCategory category) {
        giftCategoryRow.appendChild(Column.span2()
                .appendChild(GiftCategoryComponent.create(category)));
    }

    @Override
    public void setGiftsCategoryHeader(String header) {
        giftCategoriesContainer.insertFirst(h(2).textContent(header));
    }

    @Override
    public void setReviewHeader(String header) {
        reviewsContainer.insertFirst(h(2).textContent(header));
    }

    @Override
    public void addReview(Review review) {
        reviewsRow.appendChild(Column.span4()
                .appendChild(ReviewComponent.create(review))
        );
    }

    @Override
    public void setPreview(Preview preview) {
        previewContainer.clearElement();
        Loader loader = Loader.create(previewContainer, LoaderEffect.ROTATION);
        loader.start();
        previewContainer.appendChild(new PreviewComponent(preview)
                .setImageLoadedHandler(loader::stop));
    }
}