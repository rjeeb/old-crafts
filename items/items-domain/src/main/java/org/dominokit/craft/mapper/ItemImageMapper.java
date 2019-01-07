package org.dominokit.craft.mapper;

import org.dominokit.craft.model.ItemImage;

public interface ItemImageMapper<T> {

    T fromModel(ItemImage itemImage);

    ItemImage toModel(T dto);
}
