package org.dominokit.craft.category.client.ui.component;

import elemental2.dom.HTMLElement;
import org.dominokit.craft.shared.model.Filters;
import org.jboss.gwt.elemento.core.IsElement;

public interface CategoryFilterSection extends IsElement<HTMLElement> {

    void onFilterChanged(FilterChangeHandler filterChangeHandler);

    Filters getFilters();

    void unSelectFilter(String filter);

    interface FilterChangeHandler {
        void onFilterChanged();
    }
}
