package org.dominokit.craft.usecase.repository;

import io.reactivex.Observable;
import io.reactivex.Single;
import org.dominokit.craft.model.ListingItem;
import org.dominokit.craft.repository.ItemsRepository;

import java.util.HashMap;
import java.util.Map;

public class TestItemsRepository implements ItemsRepository {

    private Map<String, ListingItem> items = new HashMap<>();

    @Override
    public Single<ListingItem> save(ListingItem listingItem) {
        return Single.just(listingItem)
                .doOnSuccess(this::doSave);
    }

    private ListingItem doSave(ListingItem listingItem1) {
        return items.put(listingItem1.reference(), listingItem1);
    }

    @Override
    public Observable<ListingItem> findAll() {
        return Observable.fromIterable(items.values());
    }

    @Override
    public Single<ListingItem> findByReference(String reference) {
        if (items.containsKey(reference)) {
            return Single.just(items.get(reference));
        }
        return Single.error(new ItemNotFoundException(reference));
    }
}
