package org.dominokit.craft.category.client.ui.component;

import elemental2.dom.HTMLElement;
import org.dominokit.craft.shared.model.Filter;
import org.dominokit.craft.shared.model.Filters;
import org.dominokit.domino.ui.forms.LocalSuggestBoxStore;
import org.dominokit.domino.ui.forms.Radio;
import org.dominokit.domino.ui.forms.RadioGroup;
import org.dominokit.domino.ui.forms.SuggestBox;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.utils.BaseDominoElement;

public class ShopLocationCategoryFilterSection extends BaseDominoElement<HTMLElement, ShopLocationCategoryFilterSection> implements CategoryFilterSection {

    private static final String ANYWHERE = "anywhere";
    private static final String CUSTOM = "custom";
    private final SuggestBox locationSuggestBox;
    private RadioGroup element = RadioGroup.create("shop-location", "Shop location");
    private FilterChangeHandler filterChangeHandler;

    public ShopLocationCategoryFilterSection() {
        element.styler(style -> style.setMargin("0px")
                .setMarginTop("20px"));
        locationSuggestBox = SuggestBox.create(LocalSuggestBoxStore.create());
        element.appendChild(Radio.create(ANYWHERE, "Anywhere").styler(style -> style.setMargin("0px"))
                .check())
                .appendChild(Radio.create("jordan", "Jordan")
                        .styler(style -> style.setMargin("0px")))
                .appendChild(Radio.create(CUSTOM, "Custom")
                        .appendChild(locationSuggestBox
                                .styler(style -> style.setMargin("0px"))
                                .setPlaceholder("Enter location")
                                .setLeftAddon(Icons.ALL.location_on())
                                .setRightAddon(Icons.ALL.play_arrow().clickable()
                                        .addClickListener(evt -> filterChangeHandler.onFilterChanged())
                                )
                                .addChangeHandler(value -> filterChangeHandler.onFilterChanged())
                        )
                        .styler(style -> style.setMargin("0px"))
                );

        init(this);
    }

    public static ShopLocationCategoryFilterSection create() {
        return new ShopLocationCategoryFilterSection();
    }

    @Override
    public HTMLElement asElement() {
        return element.asElement();
    }

    @Override
    public void onFilterChanged(FilterChangeHandler filterChangeHandler) {
        this.filterChangeHandler = filterChangeHandler;
        element.addChangeHandler(value -> {
            if (!value.getValue().equals(CUSTOM)) {
                filterChangeHandler.onFilterChanged();
            }
        });
    }

    @Override
    public Filters getFilters() {
        Filters filters = new Filters();
        Filter filter = new Filter();
        filter.setName("ship_location");
        if (isAnywhere()) {
            return filters;
        } else if (isCustom()) {
            filter.setValue(locationSuggestBox.getValue());
            filters.addFilter(getCustomFilterName(), filter);
        } else {
            filter.setValue(element.getSelectedRadio().getValue());
            filters.addFilter(getFilterName(), filter);
        }
        return filters;
    }

    private String getCustomFilterName() {
        return "Shop location: " + locationSuggestBox.getValue();
    }

    private boolean isCustom() {
        return element.getSelectedRadio().getValue().equals(CUSTOM);
    }

    private boolean isAnywhere() {
        return element.getSelectedRadio().getValue().equals("anywhere");
    }

    @Override
    public void unSelectFilter(String filter) {
        if (isCustom()) {
            if ((getCustomFilterName()).equals(filter)) {
                locationSuggestBox.clear();
                element.setValue("anywhere");
            }
        } else if (getFilterName().equals(filter)) {
            element.setValue("anywhere");
        }
    }

    private String getFilterName() {
        return "Shop location: " + element.getSelectedRadio().getLabel();
    }
}
