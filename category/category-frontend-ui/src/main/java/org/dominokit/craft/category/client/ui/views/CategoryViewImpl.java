package org.dominokit.craft.category.client.ui.views;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.dominokit.craft.category.client.presenters.CategoryPresenter;
import org.dominokit.craft.category.client.ui.component.*;
import org.dominokit.craft.category.client.views.CategoryView;
import org.dominokit.craft.shared.model.Filter;
import org.dominokit.craft.shared.model.Filters;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.chips.Chip;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.view.BaseElementView;

import java.util.List;
import java.util.Map;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.h;

@UiView(presentable = CategoryPresenter.class)
public class CategoryViewImpl extends BaseElementView<HTMLDivElement> implements CategoryView {

    private DominoElement<HTMLHeadingElement> titleElement;

    @Override
    public void init(HTMLDivElement root) {
        titleElement = DominoElement.of(h(1)).addCss("category-title");

        CategoryFilterListComponent categoryFilterListComponent = CategoryFilterListComponent.create()
                .addFilterSection(ShippingCategoryFilterSection.create())
                .addFilterSection(ShopLocationCategoryFilterSection.create())
                .addFilterSection(ItemTypeCategoryFilterSection.create())
                .addFilterSection(PriceCategoryFilterSection.create())
                .addFilterSection(OrderingOptionsCategoryFilterSection.create())
                .addFilterSection(ShipToCategoryFilterSection.create());

        FlexLayout filtersFlexLayout = FlexLayout.create();
        DominoElement.of(root)
                .addCss("category-content")
                .appendChild(titleElement.asElement())
                .appendChild(Row.create()
                        .appendChild(Column.span3()
                                .appendChild(categoryFilterListComponent)
                        )
                        .appendChild(Column.span9()
                                .appendChild(div()
                                        .add(filtersFlexLayout)
                                )
                        )
                );

        categoryFilterListComponent.onFilterChanged(filters -> {
            filtersFlexLayout.clearElement();
            for (Filters secionFilters : filters) {
                for (Map.Entry<String, List<Filter>> filterEntry : secionFilters.getFilters().entrySet()) {
                    filtersFlexLayout
                            .appendChild(FlexItem.create()
                                    .appendChild(Chip.create(filterEntry.getKey())
                                            .setColorScheme(ColorScheme.BLACK)
                                            .setRemovable(true))
                            );
                }
            }
        });
    }

    @Override
    public HTMLDivElement createRoot() {
        return div().asElement();
    }

    @Override
    public void setTitle(String title) {
        titleElement.setTextContent(title);
    }
}