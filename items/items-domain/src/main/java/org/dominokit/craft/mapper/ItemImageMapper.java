package org.dominokit.craft.mapper;

import org.dominokit.craft.model.ImmutableItemImage;
import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.items.shared.model.ImageResource;

import static java.util.Optional.ofNullable;

public class ItemImageMapper {

    public static ItemImageMapper create() {
        return new ItemImageMapper();
    }

    public ItemImage fromResource(ImageResource imageResource) {
        return ImmutableItemImage.builder()
                .path(imageResource.getImagePath())
                .reference(ofNullable(imageResource.getReference()))
                .build();
    }

    public ImageResource toResource(ItemImage itemImage) {
        ImageResource imageResource = new ImageResource();
        imageResource.setImagePath(itemImage.path());
        imageResource.setReference(itemImage.reference().orElse(null));
        return imageResource;
    }
}
