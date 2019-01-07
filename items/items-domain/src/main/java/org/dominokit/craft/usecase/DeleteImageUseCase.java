package org.dominokit.craft.usecase;

import io.reactivex.Single;
import org.dominokit.craft.exception.ImageNotFoundException;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.mapper.ItemImageMapper;
import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.repository.ImagesRepository;
import org.dominokit.craft.response.DeleteImageResponse;

import static java.util.Objects.isNull;

public class DeleteImageUseCase extends BaseUseCase<ImageResource, DeleteImageResponse> {
    private final ImagesRepository imagesRepository;
    private final ItemImageMapper<ImageResource> mapper;

    public DeleteImageUseCase(ImagesRepository imagesRepository, ItemImageMapper<ImageResource> mapper) {
        this.imagesRepository = imagesRepository;
        this.mapper = mapper;
    }

    @Override
    protected Single<DeleteImageResponse> doExecute(ImageResource request) {
        return Single.just(request)
                .map(this::mapToItemImage)
                .flatMap(this::deleteImage)
                .map(this::toResponse);
    }

    private DeleteImageResponse toResponse(ItemImage itemImage) {
        DeleteImageResponse response = new DeleteImageResponse();
        response.setDeletedImage(mapper.fromModel(itemImage));
        return response;
    }

    private Single<ItemImage> deleteImage(ItemImage itemImage) {
        return imagesRepository.remove(itemImage);
    }

    private ItemImage mapToItemImage(ImageResource imageResource) {
        if (isNull(imageResource.getReference()) || imageResource.getReference().isEmpty()) {
            throw new ImageNotFoundException(imageResource.getReference());
        }
        return mapper.toModel(imageResource);
    }
}
