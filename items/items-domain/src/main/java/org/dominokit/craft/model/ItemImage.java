package org.dominokit.craft.model;

import org.dominokit.craft.exception.InvalidImageException;
import org.dominokit.craft.items.shared.model.Violation;
import org.immutables.value.Value;

import java.util.Collections;
import java.util.Optional;

@Value.Immutable
public abstract class ItemImage {

    public abstract Optional<String> reference();

    public abstract Optional<String> itemReference();

    public abstract String path();


    @Value.Check
    protected void check() {
        if (path().isEmpty()) {
            Violation violation = new Violation("path", "path.is.empty");
            throw new InvalidImageException(Collections.singletonList(violation));
        }
    }
}
