package org.dominokit.craft.repository;

import org.dominokit.craft.model.ItemImage;

public interface ImagesRepository {
    ItemImage findByReference(String reference);

    ItemImage save(ItemImage itemImage);
}
