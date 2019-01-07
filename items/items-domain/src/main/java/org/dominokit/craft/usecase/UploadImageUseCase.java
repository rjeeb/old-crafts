package org.dominokit.craft.usecase;

import io.reactivex.Single;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.mapper.ItemImageMapper;
import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.repository.ImagesRepository;
import org.dominokit.craft.response.UploadImageResponse;

import java.util.UUID;

public class UploadImageUseCase extends BaseUseCase<ImageResource, UploadImageResponse> {
    private final ImagesRepository imagesRepository;
    private final ItemImageMapper<ImageResource> mapper;

    public UploadImageUseCase(ImagesRepository imagesRepository, ItemImageMapper<ImageResource> mapper) {
        this.imagesRepository = imagesRepository;
        this.mapper = mapper;
    }

    @Override
    protected Single<UploadImageResponse> doExecute(ImageResource request) {
        return Single.just(request)
                .map(this::setReference)
                .map(this::mapToItemImage)
                .flatMap(this::saveImage)
                .map(this::toResponse);
    }

    private ImageResource setReference(ImageResource imageResource) {
        imageResource.setReference(UUID.randomUUID().toString());
        return imageResource;
    }

    private Single<ItemImage> saveImage(ItemImage itemImage) {
        return imagesRepository.save(itemImage);
    }

    private ItemImage mapToItemImage(ImageResource imageResource) {
        return mapper.toModel(imageResource);
    }

    private UploadImageResponse toResponse(ItemImage created) {
        UploadImageResponse response = new UploadImageResponse();
        response.setCreatedImage(mapper.fromModel(created));
        return response;
    }
}
