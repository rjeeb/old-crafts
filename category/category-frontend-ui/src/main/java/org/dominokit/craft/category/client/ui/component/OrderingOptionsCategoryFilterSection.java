package org.dominokit.craft.category.client.ui.component;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.dominokit.craft.shared.model.Filter;
import org.dominokit.craft.shared.model.Filters;
import org.dominokit.domino.ui.forms.CheckBox;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.ui.utils.HasChangeHandlers;

import static org.jboss.gwt.elemento.core.Elements.h;

public class OrderingOptionsCategoryFilterSection extends BaseDominoElement<HTMLElement, OrderingOptionsCategoryFilterSection> implements CategoryFilterSection {

    private DominoElement<HTMLDivElement> element = DominoElement.div();
    private final CheckBox acceptCraftsGiftCards;
    private final CheckBox customizable;
    private final CheckBox canBeGiftWrapped;

    public OrderingOptionsCategoryFilterSection() {
        element.styler(style -> style.setMargin("0px"));
        acceptCraftsGiftCards = CheckBox.create("Accept Crafts gift cards");
        customizable = CheckBox.create("Customizable");
        canBeGiftWrapped = CheckBox.create("Can be gift wrapped");

        element
                .appendChild(h(6).textContent("Shipping options"))
                .appendChild(acceptCraftsGiftCards
                        .filledIn()
                        .styler(style -> style.setMargin("0px")))
                .appendChild(customizable
                        .filledIn()
                        .styler(style -> style.setMargin("0px")))
                .appendChild(canBeGiftWrapped
                        .filledIn()
                        .styler(style -> style.setMargin("0px"))
                );

        init(this);
    }

    @Override
    public Filters getFilters() {
        Filters filters = new Filters();
        if (acceptCraftsGiftCards.getValue()) {
            Filter filter = new Filter();
            filter.setName("gift-card");
            filter.setValue("true");
            filters.addFilter(acceptCraftsGiftCards.getLabel(), filter);
        }
        if (customizable.getValue()) {
            Filter filter = new Filter();
            filter.setName("customizable");
            filter.setValue("true");
            filters.addFilter(customizable.getLabel(), filter);
        }
        if (canBeGiftWrapped.getValue()) {
            Filter filter = new Filter();
            filter.setName("gift-wrapped");
            filter.setValue("true");
            filters.addFilter(canBeGiftWrapped.getLabel(), filter);
        }
        return filters;
    }

    @Override
    public void unSelectFilter(String filter) {
        if (acceptCraftsGiftCards.getLabel().equals(filter)) {
            acceptCraftsGiftCards.uncheck();
        }
        if (customizable.getLabel().equals(filter)) {
            customizable.uncheck();
        }
        if (canBeGiftWrapped.getLabel().equals(filter)) {
            canBeGiftWrapped.uncheck();
        }
    }

    public static OrderingOptionsCategoryFilterSection create() {
        return new OrderingOptionsCategoryFilterSection();
    }

    @Override
    public HTMLElement asElement() {
        return element.asElement();
    }

    @Override
    public void onFilterChanged(FilterChangeHandler filterChangeHandler) {
        HasChangeHandlers.ChangeHandler<Boolean> orderingOptionsChangeHandler = value -> filterChangeHandler.onFilterChanged();
        acceptCraftsGiftCards.addChangeHandler(orderingOptionsChangeHandler);
        customizable.addChangeHandler(orderingOptionsChangeHandler);
        canBeGiftWrapped.addChangeHandler(orderingOptionsChangeHandler);
    }
}
