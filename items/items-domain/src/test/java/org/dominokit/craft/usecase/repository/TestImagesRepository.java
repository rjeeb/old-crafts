package org.dominokit.craft.usecase.repository;

import io.reactivex.Single;
import org.dominokit.craft.exception.ImageNotFoundException;
import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.repository.ImagesRepository;

import java.util.HashMap;
import java.util.Map;

public class TestImagesRepository implements ImagesRepository {

    private Map<String, ItemImage> images = new HashMap<>();

    @Override
    public Single<ItemImage> findByReference(String reference) {
        return Single.create(emitter -> {
            if (images.containsKey(reference)) {
                emitter.onSuccess(images.get(reference));
            } else {
                emitter.onError(new ImageNotFoundException(reference));
            }
        });
    }

    @Override
    public Single<ItemImage> save(ItemImage itemImage) {
        return Single.create(emitter -> {
            images.put(itemImage.reference(), itemImage);
            emitter.onSuccess(itemImage);
        });
    }

    @Override
    public Single<ItemImage> remove(ItemImage itemImage) {
        return Single.create(emitter -> {
            images.remove(itemImage.reference());
            emitter.onSuccess(itemImage);
        });
    }
}
