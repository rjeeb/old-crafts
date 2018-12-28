package org.dominokit.craft.items.client.ui.variations.type;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.items.shared.model.Variation;
import org.jboss.gwt.elemento.core.IsElement;

public interface VariationType extends IsElement<HTMLDivElement> {
    VariationType setRemoveHandler(RemoveHandler removeHandler);

    Variation getVariation();

    @FunctionalInterface
    interface RemoveHandler {
        void onRemove();
    }
}
