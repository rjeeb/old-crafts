package org.dominokit.craft.repository.mapper;

import org.dominokit.craft.mapper.ItemImageMapper;
import org.dominokit.craft.model.ImmutableItemImage;
import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.repository.entity.ImageEntity;

public class ItemImageEntityMapper implements ItemImageMapper<ImageEntity> {

    @Override
    public ImageEntity fromModel(ItemImage itemImage) {
        ImageEntity entity = new ImageEntity();
        entity.setItemReference(itemImage.itemReference());
        entity.setPath(itemImage.path());
        entity.setReference(itemImage.reference());
        return entity;
    }

    @Override
    public ItemImage toModel(ImageEntity entity) {
        return ImmutableItemImage.builder()
                .reference(entity.getReference())
                .path(entity.getPath())
                .itemReference(entity.getItemReference())
                .build();
    }
}
