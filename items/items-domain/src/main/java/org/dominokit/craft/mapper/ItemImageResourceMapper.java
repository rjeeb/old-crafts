package org.dominokit.craft.mapper;

import org.dominokit.craft.exception.InvalidImageException;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.items.shared.model.Violation;
import org.dominokit.craft.model.ImmutableItemImage;
import org.dominokit.craft.model.ItemImage;

import java.util.Collections;

public class ItemImageResourceMapper implements ItemImageMapper<ImageResource> {
    @Override
    public ImageResource fromModel(ItemImage itemImage) {
        ImageResource imageResource = new ImageResource();
        imageResource.setImagePath(itemImage.path());
        imageResource.setReference(itemImage.reference());
        return imageResource;
    }

    @Override
    public ItemImage toModel(ImageResource imageResource) {
        try {
            return ImmutableItemImage.builder()
                    .path(imageResource.getImagePath())
                    .reference(imageResource.getReference())
                    .build();
        } catch (NullPointerException e) {
            throw new InvalidImageException(Collections.singletonList(new Violation(e.getMessage(), e.getMessage() + ".is.null")));
        }
    }

}
