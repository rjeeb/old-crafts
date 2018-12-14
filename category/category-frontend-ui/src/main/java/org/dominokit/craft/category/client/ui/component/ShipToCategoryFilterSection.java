package org.dominokit.craft.category.client.ui.component;

import elemental2.dom.HTMLElement;
import org.dominokit.craft.shared.model.Filter;
import org.dominokit.craft.shared.model.Filters;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.SelectOption;
import org.dominokit.domino.ui.utils.BaseDominoElement;

public class ShipToCategoryFilterSection extends BaseDominoElement<HTMLElement, ShipToCategoryFilterSection> implements CategoryFilterSection {

    private Select<String> element = Select.create("Ship to");

    public ShipToCategoryFilterSection() {
        element.styler(style -> style.setMargin("0px")
                .setMarginTop("20px"));
        element.appendChild(SelectOption.create("JO", "Jordan"))
                .selectAt(0);

        init(this);
    }

    public static ShipToCategoryFilterSection create() {
        return new ShipToCategoryFilterSection();
    }

    @Override
    public HTMLElement asElement() {
        return element.asElement();
    }

    @Override
    public void onFilterChanged(FilterChangeHandler filterChangeHandler) {
        element.addChangeHandler(value -> filterChangeHandler.onFilterChanged());
    }

    @Override
    public Filters getFilters() {
        Filters filters = new Filters();
        Filter filter = new Filter();
        filter.setName("ship_to");
        filter.setValue(element.getValue());
        filters.addFilter("Ship to: " + element.getSelectedOption().getKey(), filter);
        return filters;
    }
}
