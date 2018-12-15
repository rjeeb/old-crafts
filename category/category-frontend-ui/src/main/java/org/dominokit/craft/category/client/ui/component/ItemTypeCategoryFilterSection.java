package org.dominokit.craft.category.client.ui.component;

import elemental2.dom.HTMLElement;
import org.dominokit.craft.shared.model.Filter;
import org.dominokit.craft.shared.model.Filters;
import org.dominokit.domino.ui.forms.Radio;
import org.dominokit.domino.ui.forms.RadioGroup;
import org.dominokit.domino.ui.utils.BaseDominoElement;

public class ItemTypeCategoryFilterSection extends BaseDominoElement<HTMLElement, ItemTypeCategoryFilterSection> implements CategoryFilterSection {

    private RadioGroup element = RadioGroup.create("item-type", "Item type");

    public ItemTypeCategoryFilterSection() {
        element.styler(style -> style.setMargin("0px")
                .setMarginTop("20px"));
        element
                .appendChild(Radio.create("all", "All items")
                        .styler(style -> style.setMargin("0px"))
                        .check())
                .appendChild(Radio.create("handmade", "Handmade")
                        .styler(style -> style.setMargin("0px")))
                .appendChild(Radio.create("vintage", "Vintage")
                        .styler(style -> style.setMargin("0px"))
                );

        init(this);
    }

    @Override
    public Filters getFilters() {
        Filters filters = new Filters();
        Radio selectedRadio = element.getSelectedRadio();
        if (selectedRadio.getValue().equals("all")) {
            return filters;
        }
        Filter filter = new Filter();
        filter.setName(selectedRadio.getName());
        filter.setValue(selectedRadio.getValue());
        filters.addFilter(selectedRadio.getLabel(), filter);
        return filters;
    }

    @Override
    public void unSelectFilter(String filter) {
        if (element.getSelectedRadio().getLabel().equals(filter)) {
            element.setValue("all");
        }
    }

    public static ItemTypeCategoryFilterSection create() {
        return new ItemTypeCategoryFilterSection();
    }

    @Override
    public HTMLElement asElement() {
        return element.asElement();
    }

    @Override
    public void onFilterChanged(FilterChangeHandler filterChangeHandler) {
        element.addChangeHandler(value -> filterChangeHandler.onFilterChanged());
    }
}
