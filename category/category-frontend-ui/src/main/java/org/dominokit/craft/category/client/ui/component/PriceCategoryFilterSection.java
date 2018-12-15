package org.dominokit.craft.category.client.ui.component;

import elemental2.dom.HTMLElement;
import org.dominokit.craft.shared.model.Filter;
import org.dominokit.craft.shared.model.Filters;
import org.dominokit.domino.ui.forms.DoubleBox;
import org.dominokit.domino.ui.forms.Radio;
import org.dominokit.domino.ui.forms.RadioGroup;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.BaseDominoElement;

import java.util.HashMap;
import java.util.Map;

import static org.jboss.gwt.elemento.core.Elements.p;

public class PriceCategoryFilterSection extends BaseDominoElement<HTMLElement, PriceCategoryFilterSection> implements CategoryFilterSection {

    public static final String F_0_T_25 = "0-25";
    public static final String F_25_T_50 = "25-50";
    public static final String F_50_T_100 = "50-100";
    private static final String U_100 = "100";
    private final DoubleBox lowPriceBox;
    private final DoubleBox highPriceBox;
    private RadioGroup element = RadioGroup.create("price", "Price ($)");
    private Map<String, PriceValue> prices = new HashMap<>();
    private FilterChangeHandler filterChangeHandler;


    public PriceCategoryFilterSection() {
        prices.put(F_0_T_25, new PriceValue(0, 25));
        prices.put(F_25_T_50, new PriceValue(0, 25));
        prices.put(F_50_T_100, new PriceValue(0, 25));
        prices.put(U_100, new PriceValue(100));

        element.styler(style -> style.setMargin("0px")
                .setMarginTop("20px"));
        lowPriceBox = DoubleBox.create();
        highPriceBox = DoubleBox.create();
        element.appendChild(Radio.create("any", "Any price").styler(style -> style.setMargin("0px"))
                .check())
                .appendChild(Radio.create(F_0_T_25, "Under USD 25")
                        .styler(style -> style.setMargin("0px")))
                .appendChild(Radio.create(F_25_T_50, "USD 25 to USD 50")
                        .styler(style -> style.setMargin("0px")))
                .appendChild(Radio.create(F_50_T_100, "USD 50 to USD 100")
                        .styler(style -> style.setMargin("0px")))
                .appendChild(Radio.create(U_100, "Over USD 100")
                        .styler(style -> style.setMargin("0px")))
                .appendChild(Radio.create("custom", "Custom")
                        .appendChild(FlexLayout.create()
                                .styler(style -> style.setPaddingLeft("30px"))
                                .appendChild(FlexItem.create().appendChild(lowPriceBox
                                        .styler(style -> style.setMargin("0px"))
                                        .setPlaceholder("Low")
                                ))
                                .appendChild(FlexItem.create().appendChild(p()
                                        .css(Styles.p_l_10, Styles.p_r_10, Styles.align_center)
                                        .style("line-height: 40px")
                                        .textContent("to")))
                                .appendChild(FlexItem.create().appendChild(highPriceBox
                                        .styler(style -> style.setMargin("0px"))
                                        .setPlaceholder("High")
                                        .addChangeHandler(value -> {
                                            if (!lowPriceBox.isEmpty()) {
                                                filterChangeHandler.onFilterChanged();
                                            }
                                        })
                                ))
                                .appendChild(FlexItem.create()
                                        .appendChild(Icons.ALL.play_arrow().clickable()
                                                .addClickListener(evt -> filterChangeHandler.onFilterChanged()))
                                )
                        )
                        .styler(style -> style.setMargin("0px"))
                );


        init(this);
    }

    public static PriceCategoryFilterSection create() {
        return new PriceCategoryFilterSection();
    }

    @Override
    public HTMLElement asElement() {
        return element.asElement();
    }

    @Override
    public void onFilterChanged(FilterChangeHandler filterChangeHandler) {
        this.filterChangeHandler = filterChangeHandler;
        element.addChangeHandler(value -> {
            if (!value.getValue().equals("custom")) {
                filterChangeHandler.onFilterChanged();
            }
        });
    }

    @Override
    public Filters getFilters() {
        Filters filters = new Filters();
        if (isAny()) {
            return filters;
        } else if (isCustom()) {
            Filter lowPriceFilter = new Filter();
            lowPriceFilter.setName("min");
            lowPriceFilter.setValue(lowPriceBox.getStringValue());

            Filter highPriceFilter = new Filter();
            highPriceFilter.setName("max");
            highPriceFilter.setValue(highPriceBox.getStringValue());

            String filterName = getCustomFilterName();

            filters.addFilter(filterName, lowPriceFilter);
            filters.addFilter(filterName, highPriceFilter);
        } else {
            Radio selectedRadio = element.getSelectedRadio();
            PriceValue priceValue = prices.get(selectedRadio.getValue());
            if (priceValue.min > 0) {
                Filter lowPriceFilter = new Filter();
                lowPriceFilter.setName("min");
                lowPriceFilter.setValue(priceValue.min + "");
                filters.addFilter(selectedRadio.getLabel(), lowPriceFilter);
            }
            if (priceValue.max > 0) {
                Filter highPriceFilter = new Filter();
                highPriceFilter.setName("max");
                highPriceFilter.setValue(priceValue.max + "");
                filters.addFilter(selectedRadio.getLabel(), highPriceFilter);
            }
        }
        return filters;
    }

    @Override
    public void unSelectFilter(String filterName) {
        if (isCustom()) {
            if (getCustomFilterName().equals(filterName)) {
                element.setValue("any");
            }
        } else if (element.getSelectedRadio().getLabel().equals(filterName)) {
            element.setValue("any");
        }
    }

    private String getCustomFilterName() {
        return "USD " + lowPriceBox.getStringValue() + " - USD " + highPriceBox.getStringValue();
    }

    private boolean isAny() {
        return element.getSelectedRadio().getValue().equals("any");
    }

    private boolean isCustom() {
        return element.getSelectedRadio().getValue().equals("custom");
    }

    private class PriceValue {
        private int min;
        private int max;

        private PriceValue(int min, int max) {
            this.min = min;
            this.max = max;
        }

        private PriceValue(int min) {
            this.min = min;
        }
    }
}
