package org.dominokit.craft.model;

import org.dominokit.craft.exception.InvalidImageException;
import org.dominokit.craft.items.shared.model.Violation;
import org.immutables.value.Value;

import java.util.Collections;

@Value.Immutable
public abstract class ItemImage {

    public abstract String reference();

    public abstract String path();

    @Nullable
    public abstract String itemReference();

    @Value.Check
    protected void check() {
        if (path().isEmpty()) {
            Violation violation = new Violation("path", "path.is.empty");
            throw new InvalidImageException(Collections.singletonList(violation));
        }
    }
}
