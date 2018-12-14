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

public class ShippingCategoryFilterSection extends BaseDominoElement<HTMLElement, ShippingCategoryFilterSection> implements CategoryFilterSection {

    private final CheckBox freeShipping;
    private final CheckBox maxProcessing1Day;
    private final CheckBox maxProcessing3Days;
    private DominoElement<HTMLDivElement> element = DominoElement.div();

    public ShippingCategoryFilterSection() {
        element.styler(style -> style.setMargin("0px"));
        freeShipping = CheckBox.create("Free shipping");
        maxProcessing1Day = CheckBox.create("Ready to ship in 1 business day");
        maxProcessing3Days = CheckBox.create("Ready to ship within 3 business days");
        element
                .appendChild(h(6).textContent("Shipping"))
                .appendChild(freeShipping
                        .filledIn()
                        .styler(style -> style.setMargin("0px")))
                .appendChild(maxProcessing1Day
                        .filledIn()
                        .styler(style -> style.setMargin("0px")))
                .appendChild(maxProcessing3Days
                        .filledIn()
                        .styler(style -> style.setMargin("0px"))
                );

        init(this);
    }

    public static ShippingCategoryFilterSection create() {
        return new ShippingCategoryFilterSection();
    }

    @Override
    public HTMLElement asElement() {
        return element.asElement();
    }

    @Override
    public void onFilterChanged(FilterChangeHandler filterChangeHandler) {
        HasChangeHandlers.ChangeHandler<Boolean> shippingChangeHandler = value -> filterChangeHandler.onFilterChanged();
        freeShipping.addChangeHandler(shippingChangeHandler);
        maxProcessing1Day.addChangeHandler(shippingChangeHandler);
        maxProcessing3Days.addChangeHandler(shippingChangeHandler);
    }

    @Override
    public Filters getFilters() {
        Filters filters = new Filters();
        if (freeShipping.isChecked()) {
            Filter filter = new Filter();
            filter.setName("free_shipping");
            filter.setValue("true");
            filters.addFilter(freeShipping.getLabel(), filter);
        }
        if (maxProcessing1Day.isChecked()) {
            Filter filter = new Filter();
            filter.setName("max_processing_days");
            filter.setValue("1");
            filters.addFilter(maxProcessing1Day.getLabel(), filter);
        }
        if (maxProcessing3Days.isChecked()) {
            Filter filter = new Filter();
            filter.setName("max_processing_days");
            filter.setValue("3");
            filters.addFilter(maxProcessing3Days.getLabel(), filter);
        }
        return filters;
    }
}
