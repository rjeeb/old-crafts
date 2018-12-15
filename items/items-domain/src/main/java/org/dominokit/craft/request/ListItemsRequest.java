package org.dominokit.craft.request;

import org.immutables.value.Value;

import java.util.Map;

@Value.Immutable
public abstract class ListItemsRequest {
    public abstract Map<String, String> filters();
}
