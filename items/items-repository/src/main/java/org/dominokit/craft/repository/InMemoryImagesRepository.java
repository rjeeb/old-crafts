package org.dominokit.craft.repository;

import org.dominokit.craft.model.ItemImage;

import java.util.HashMap;
import java.util.Map;

public class InMemoryImagesRepository implements ImagesRepository {

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
