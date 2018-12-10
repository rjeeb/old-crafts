package org.dominokit.craft.layout.shared.extension;

import org.dominokit.craft.shared.model.Category;
import org.dominokit.domino.api.shared.extension.EventContext;

public interface CategorySelectedEventContext extends EventContext {
    Category getCategory();
}
