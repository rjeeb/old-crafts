package org.dominokit.craft.repository;

import io.reactivex.Single;
import org.dominokit.craft.model.ItemImage;

public interface ImagesRepository {
    Single<ItemImage> findByReference(String reference);

    Single<ItemImage> save(ItemImage itemImage);

    Single<ItemImage> remove(ItemImage itemImage);
}
