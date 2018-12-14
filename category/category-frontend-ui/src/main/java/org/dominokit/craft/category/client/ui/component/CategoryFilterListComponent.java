package org.dominokit.craft.category.client.ui.component;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.dominokit.craft.shared.model.Filters;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CategoryFilterListComponent extends BaseDominoElement<HTMLElement, CategoryFilterListComponent> {

    private DominoElement<HTMLDivElement> element = DominoElement.div().addCss("category-filter-list");
    private List<CategoryFilterSection> filterSections = new ArrayList<>();
    private Consumer<List<Filters>> filtersChangeHandler;

    public CategoryFilterListComponent() {
        init(this);
    }

    public static CategoryFilterListComponent create() {
        return new CategoryFilterListComponent();
    }

    public CategoryFilterListComponent addFilterSection(CategoryFilterSection filterSection) {
        filterSections.add(filterSection);
        filterSection.onFilterChanged(() -> {
            List<Filters> allFilters = new ArrayList<>();
            for (CategoryFilterSection section : filterSections) {
                allFilters.add(section.getFilters());
            }
            filtersChangeHandler.accept(allFilters);
        });
        element.appendChild(DominoElement.div()
                .addCss("category-filter-section")
                .appendChild(filterSection)
        );
        return this;
    }

    public void onFilterChanged(Consumer<List<Filters>> filtersChangeHandler) {
        this.filtersChangeHandler = filtersChangeHandler;
    }

    @Override
    public HTMLElement asElement() {
        return element.asElement();
    }
}
