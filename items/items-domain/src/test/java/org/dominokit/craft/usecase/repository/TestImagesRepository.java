package org.dominokit.craft.usecase.repository;

import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.repository.ImagesRepository;

import java.util.HashMap;
import java.util.Map;

public class TestImagesRepository implements ImagesRepository {

    private Map<String, ItemImage> images = new HashMap<>();

    @Override
    public ItemImage findByReference(String reference) {
        return images.getOrDefault(reference, null);
    }

    @Override
    public ItemImage save(ItemImage itemImage) {
        if (itemImage.reference().isPresent()) {
            images.put(itemImage.reference().get(), itemImage);
            return itemImage;
        }
        return null;
    }
}
