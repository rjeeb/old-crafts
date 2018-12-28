package org.dominokit.craft.usecase;

import org.dominokit.craft.exception.ImageNotFoundException;
import org.dominokit.craft.exception.InvalidItemException;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.mapper.ItemsMapper;
import org.dominokit.craft.model.ImmutableItemImage;
import org.dominokit.craft.model.Item;
import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.repository.ImagesRepository;
import org.dominokit.craft.repository.ItemsRepository;
import org.dominokit.craft.response.CreateItemResponse;

import java.util.Collections;
import java.util.UUID;

import static java.util.Objects.isNull;

public class CreateItemUseCase extends BaseUseCase<ItemResource, CreateItemResponse> {
    private ItemsRepository itemsRepository;
    private ImagesRepository imagesRepository;

    public CreateItemUseCase(ItemsRepository itemsRepository, ImagesRepository imagesRepository) {
        this.itemsRepository = itemsRepository;
        this.imagesRepository = imagesRepository;
    }

    @Override
    protected CreateItemResponse doExecute(ItemResource itemResource) {
        try {
            itemResource.setReference(UUID.randomUUID().toString());
            Item item = ItemsMapper.create().fromResource(itemResource);
            linkAndSaveImages(item);
            Item createdItem = itemsRepository.save(item);
            CreateItemResponse response = new CreateItemResponse();
            response.setCreatedItem(ItemsMapper.create().toResource(createdItem));
            return response;
        } catch (NullPointerException e) {
            throw new InvalidItemException(Collections.singletonList(createViolation(e.getMessage(), e.getMessage() + ".is.null")));
        }
    }

    private void linkAndSaveImages(Item item) {
        item.imageReferences()
                .stream()
                .map(this::findImage)
                .map(itemImage -> setItemReference(itemImage, item))
                .forEach(imagesRepository::save);
    }

    private ItemImage findImage(String reference) {
        ItemImage itemImage = imagesRepository.findByReference(reference);
        if (isNull(itemImage)) {
            throw new ImageNotFoundException(reference);
        }
        return itemImage;
    }

    private ItemImage setItemReference(ItemImage itemImage, Item item) {
        return ImmutableItemImage.copyOf(itemImage)
                .withItemReference(item.reference());
    }
}
