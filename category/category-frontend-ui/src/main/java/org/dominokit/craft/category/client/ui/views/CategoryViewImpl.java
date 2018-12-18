package org.dominokit.craft.category.client.ui.views;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.dominokit.craft.category.client.presenters.CategoryPresenter;
import org.dominokit.craft.category.client.ui.component.*;
import org.dominokit.craft.category.client.ui.component.itemform.ItemForm;
import org.dominokit.craft.category.client.views.CategoryView;
import org.dominokit.craft.items.client.ui.component.ItemComponent;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.chips.Chip;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.grid.flex.FlexWrap;
import org.dominokit.domino.ui.themes.Theme;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.view.BaseElementView;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;

import java.util.Collection;
import java.util.List;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.h;

@UiView(presentable = CategoryPresenter.class)
public class CategoryViewImpl extends BaseElementView<HTMLDivElement> implements CategoryView {

    private DominoElement<HTMLHeadingElement> titleElement;
    private CategoryViewUiHandlers uiHandlers;
    private FlexLayout filtersFlexLayout;
    private CategoryFilterListComponent categoryFilterListComponent;
    private FlexLayout itemsFlexLayout;

    @Override
    public void init(HTMLDivElement root) {
        HtmlContentBuilder<HTMLDivElement> itemsContainer = div();

        titleElement = DominoElement.of(h(1)).addCss("category-title");

        categoryFilterListComponent = CategoryFilterListComponent.create()
                .addFilterSection(ShippingCategoryFilterSection.create())
                .addFilterSection(ShopLocationCategoryFilterSection.create())
                .addFilterSection(ItemTypeCategoryFilterSection.create())
                .addFilterSection(PriceCategoryFilterSection.create())
                .addFilterSection(OrderingOptionsCategoryFilterSection.create())
                .addFilterSection(ShipToCategoryFilterSection.create());

        filtersFlexLayout = FlexLayout.create()
                .setWrap(FlexWrap.WRAP_TOP_TO_BOTTOM);
        itemsFlexLayout = FlexLayout.create()
                .setWrap(FlexWrap.WRAP_TOP_TO_BOTTOM);

        DominoElement.of(root)
                .addCss("category-content")
                .appendChild(Row.create()
                        .appendChild(Column.span10()
                                .offset1()
                                .appendChild(titleElement.asElement())
                                .appendChild(Button.createDefault("ADD ITEM")
                                        .addClickListener(evt -> {
                                            DominoElement.of(root)
                                                    .clearElement()
                                                    .appendChild(new ItemForm());
                                        }))
                                .appendChild(Row.create()
                                        .appendChild(Column.span3()
                                                .appendChild(categoryFilterListComponent)
                                        )
                                        .appendChild(Column.span9()
                                                .appendChild(itemsContainer
                                                        .add(filtersFlexLayout)
                                                )
                                                .appendChild(itemsFlexLayout)
                                        )
                                )
                        )
                );

        categoryFilterListComponent.onFilterChanged(filters -> {
            uiHandlers.onFiltersChanged(filters);
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

    @Override
    public void viewFilters(Collection<String> filtersNames) {
        filtersFlexLayout.clearElement();
        filtersNames.forEach(filterName -> filtersFlexLayout
                .appendChild(FlexItem.create()
                        .appendChild(Chip.create(filterName)
                                .setColorScheme(Theme.currentTheme.getScheme())
                                .setRemovable(true)
                                .addRemoveHandler(() -> uiHandlers.onFilterRemoved(filterName)))
                ));
    }

    @Override
    public void unSelectFilter(String filter) {
        categoryFilterListComponent.unSelectFilter(filter);
    }

    @Override
    public void setItems(List<ItemResource> itemResources) {
        for (ItemResource itemResource : itemResources) {
            itemsFlexLayout.appendChild(FlexItem.create()
                    .styler(style -> style.setWidth("250px"))
                    .appendChild(ItemComponent.create(itemResource)));
        }
    }

    @Override
    public void setUiHandlers(CategoryViewUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }
}