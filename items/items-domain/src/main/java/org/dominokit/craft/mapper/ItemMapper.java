package org.dominokit.craft.mapper;

import org.dominokit.craft.model.ListingItem;

public interface ItemMapper<T> {

    ListingItem toModel(T dto);

    T fromModel(ListingItem listingItem);
}
