package org.dominokit.craft.category.client.ui.component.itemform.variations.type;

import elemental2.dom.HTMLDivElement;
import org.jboss.gwt.elemento.core.IsElement;

public interface VariationType extends IsElement<HTMLDivElement> {
    VariationType setRemoveHandler(RemoveHandler removeHandler);

    @FunctionalInterface
    interface RemoveHandler {
        void onRemove();
    }
}
