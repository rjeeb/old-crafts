package org.dominokit.craft.repository;

import io.reactivex.Observable;
import io.reactivex.Single;
import org.dominokit.craft.model.ListingItem;

public interface ItemsRepository {
    Single<ListingItem> save(ListingItem listingItem);

    Observable<ListingItem> findAll();

    Single<ListingItem> findByReference(String reference);

    class ItemNotFoundException extends RuntimeException {

        public ItemNotFoundException(String reference) {
            super(reference);
        }
    }
}
