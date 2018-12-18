package org.dominokit.craft.model;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Item {

    public abstract String getTitle();

    public abstract String getDescription();

    public abstract double getPrice();
}
