package org.dominokit.craft.usecase;

import io.reactivex.Observable;
import io.reactivex.Single;
import org.dominokit.craft.exception.InvalidItemException;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.mapper.ItemMapper;
import org.dominokit.craft.model.ImmutableItemImage;
import org.dominokit.craft.model.ListingItem;
import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.repository.ImagesRepository;
import org.dominokit.craft.repository.ItemsRepository;
import org.dominokit.craft.response.CreateItemResponse;

import java.util.Collections;
import java.util.UUID;

public class CreateItemUseCase extends BaseUseCase<ItemResource, CreateItemResponse> {
    private final ItemsRepository itemsRepository;
    private final ImagesRepository imagesRepository;
    private final ItemMapper<ItemResource> mapper;

    public CreateItemUseCase(ItemsRepository itemsRepository, ImagesRepository imagesRepository, ItemMapper<ItemResource> mapper) {
        this.itemsRepository = itemsRepository;
        this.imagesRepository = imagesRepository;
        this.mapper = mapper;
    }

    @Override
    protected Single<CreateItemResponse> doExecute(ItemResource itemResource) {
        return Single.just(itemResource)
                .map(this::setReference)
                .map(this::mapToItem)
                .flatMap(this::updateImagesItemReference)
                .flatMap(this::saveItem)
                .map(this::toResponse);
    }

    private ItemResource setReference(ItemResource itemResource) {
        itemResource.setReference(UUID.randomUUID().toString());
        return itemResource;
    }

    private ListingItem mapToItem(ItemResource itemResource) {
        try {
            return mapper.toModel(itemResource);
        } catch (NullPointerException e) {
            throw new InvalidItemException(Collections.singletonList(createViolation(e.getMessage(), e.getMessage() + ".is.null")));
        }
    }

    private Single<ListingItem> updateImagesItemReference(ListingItem listingItem) {
        return Single.create(emitter -> Observable.fromIterable(listingItem.imageReferences())
                .flatMap(this::findImage)
                .doOnError(emitter::onError)
                .doOnNext(itemImage -> linkAndSave(itemImage, listingItem))
                .doOnComplete(() -> emitter.onSuccess(listingItem))
                .subscribe());
    }

    private Observable<ItemImage> findImage(String reference) {
        return imagesRepository.findByReference(reference).toObservable();
    }

    private void linkAndSave(ItemImage itemImage, ListingItem listingItem) {
        imagesRepository.save(setItemReference(itemImage, listingItem)).subscribe();
    }

    private Single<ListingItem> saveItem(ListingItem listingItem) {
        return itemsRepository.save(listingItem);
    }

    private CreateItemResponse toResponse(ListingItem createdListingItem) {
        CreateItemResponse response = new CreateItemResponse();
        response.setCreatedItem(mapper.fromModel(createdListingItem));
        return response;
    }

    private ItemImage setItemReference(ItemImage itemImage, ListingItem listingItem) {
        return ImmutableItemImage.copyOf(itemImage)
                .withItemReference(listingItem.reference());
    }
}
