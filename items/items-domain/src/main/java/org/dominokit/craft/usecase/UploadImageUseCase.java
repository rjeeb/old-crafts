package org.dominokit.craft.usecase;

import org.dominokit.craft.exception.InvalidImageException;
import org.dominokit.craft.mapper.ItemImageMapper;
import org.dominokit.craft.model.ImmutableItemImage;
import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.repository.ImagesRepository;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.response.UploadImageResponse;

import java.util.Collections;
import java.util.UUID;

public class UploadImageUseCase extends BaseUseCase<ImageResource, UploadImageResponse> {
    private ImagesRepository imagesRepository;

    public UploadImageUseCase(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    protected UploadImageResponse doExecute(ImageResource request) {
        try {
            ItemImage itemImage = ItemImageMapper.create().fromResource(request);
            ImmutableItemImage immutableItemImage = ImmutableItemImage.copyOf(itemImage)
                    .withReference(UUID.randomUUID().toString());
            ItemImage createdItemImage = imagesRepository.save(immutableItemImage);
            UploadImageResponse response = new UploadImageResponse();
            response.setCreatedImage(ItemImageMapper.create().toResource(createdItemImage));
            return response;
        } catch (NullPointerException e) {
            throw new InvalidImageException(Collections.singletonList(createViolation(e.getMessage(), e.getMessage() + ".is.null")));
        }
    }
}
