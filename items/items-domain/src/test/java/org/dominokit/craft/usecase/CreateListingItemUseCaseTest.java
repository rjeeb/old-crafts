package org.dominokit.craft.usecase;

import io.reactivex.Single;
import org.dominokit.craft.exception.ImageNotFoundException;
import org.dominokit.craft.exception.InvalidItemException;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.mapper.ItemResourceMapper;
import org.dominokit.craft.model.ImmutableItemImage;
import org.dominokit.craft.model.ListingItem;
import org.dominokit.craft.response.CreateItemResponse;
import org.dominokit.craft.usecase.repository.TestImagesRepository;
import org.dominokit.craft.usecase.repository.TestItemsRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class CreateListingItemUseCaseTest extends BaseUseCaseTest<CreateItemUseCase> {

    private static final String EXIST_IMAGE_REFERENCE = "image reference";
    private TestItemsRepository itemsRepository;
    private TestImagesRepository imagesRepository;

    @Override
    protected void initTest() {
        validItemResource();
        imagesRepository.save(ImmutableItemImage.builder()
                .reference(EXIST_IMAGE_REFERENCE)
                .path("image path")
                .build()).subscribe();
    }

    @Override
    protected CreateItemUseCase createUseCase() {
        itemsRepository = new TestItemsRepository();
        imagesRepository = new TestImagesRepository();
        return new CreateItemUseCase(itemsRepository, imagesRepository, new ItemResourceMapper());
    }

    private ItemResource validItemResource() {
        ItemResource validItem = new ItemResource();
        validItem.setTitle("title");
        validItem.setWhoMadeIt("i did");
        validItem.setWhatIsIt("product");
        validItem.setWhenWasItMade("vintage");
        validItem.setCategory("category");
        validItem.setRenewalOption("AUTOMATIC");
        validItem.setType("PHYSICAL");
        validItem.setDescription("description");
        validItem.setAmount(5.2);
        validItem.setQuantity(2);
        validItem.setImageReferences(Collections.singletonList(EXIST_IMAGE_REFERENCE));
        return validItem;
    }

    private void assertNullProperty(String property, ItemResource itemResource) {
        useCase.execute(itemResource)
                .test()
                .assertError(throwable -> {
                    InvalidItemException invalidItemException = (InvalidItemException) throwable;
                    return !invalidItemException.getViolations().isEmpty() && invalidItemException.getViolations()
                            .contains(createViolation(property, property + ".is.null"));
                });
    }

    private void assertInvalidProperty(String property, ItemResource itemResource) {
        useCase.execute(itemResource)
                .test()
                .assertError(throwable -> {
                    InvalidItemException invalidItemException = (InvalidItemException) throwable;
                    return !invalidItemException.getViolations().isEmpty() && invalidItemException.getViolations()
                            .contains(createViolation(property, property + ".is.invalid"));
                });
    }

    @Test
    void givenItemWithNullRequiredPropertyWhenCreateThenShouldThrowExceptionWithViolation() {
        ItemResource nullTitle = validItemResource();
        nullTitle.setTitle(null);
        assertNullProperty("title", nullTitle);

        ItemResource nullWhoMadeIt = validItemResource();
        nullWhoMadeIt.setWhoMadeIt(null);
        assertNullProperty("whoMadeIt", nullWhoMadeIt);

        ItemResource nullWhenMade = validItemResource();
        nullWhenMade.setWhatIsIt(null);
        assertNullProperty("whatIsIt", nullWhenMade);

        ItemResource nullWhenWasItMade = validItemResource();
        nullWhenWasItMade.setWhenWasItMade(null);
        assertNullProperty("whenWasItMade", nullWhenWasItMade);

        ItemResource nullCategory = validItemResource();
        nullCategory.setCategory(null);
        assertNullProperty("category", nullCategory);

        ItemResource nullRenewalOption = validItemResource();
        nullRenewalOption.setRenewalOption(null);
        assertNullProperty("renewalOption", nullRenewalOption);

        ItemResource nullType = validItemResource();
        nullType.setType(null);
        assertNullProperty("itemType", nullType);

        ItemResource nullDescription = validItemResource();
        nullDescription.setDescription(null);
        assertNullProperty("description", nullDescription);
    }

    @Test
    void givenItemWithInvalidPropertyWhenCreateThenShouldThrowExceptionWithViolation() {
        ItemResource emptyTitle = validItemResource();
        emptyTitle.setTitle("");
        assertInvalidProperty("title", emptyTitle);

        ItemResource emptyWhoMadeIt = validItemResource();
        emptyWhoMadeIt.setWhoMadeIt("");
        assertInvalidProperty("whoMadeIt", emptyWhoMadeIt);

        ItemResource emptyWhenMade = validItemResource();
        emptyWhenMade.setWhatIsIt("");
        assertInvalidProperty("whatIsIt", emptyWhenMade);

        ItemResource emptyWhenWasItMade = validItemResource();
        emptyWhenWasItMade.setWhenWasItMade("");
        assertInvalidProperty("whenWasItMade", emptyWhenWasItMade);

        ItemResource emptyCategory = validItemResource();
        emptyCategory.setCategory("");
        assertInvalidProperty("category", emptyCategory);

        ItemResource invalidRenewalOption = validItemResource();
        invalidRenewalOption.setRenewalOption("invalid");
        assertInvalidProperty("renewalOption", invalidRenewalOption);

        ItemResource invalidType = validItemResource();
        invalidType.setType("invalid");
        assertInvalidProperty("itemType", invalidType);

        ItemResource emptyDescription = validItemResource();
        emptyDescription.setDescription("");
        assertInvalidProperty("description", emptyDescription);

        ItemResource emptyImagesReferences = validItemResource();
        emptyImagesReferences.setImageReferences(new ArrayList<>());
        assertInvalidProperty("imageReferences", emptyImagesReferences);
    }

    @Test
    void givenNegativePriceWhenCreateThenShouldThrowExceptionWithViolation() {
        ItemResource itemResource = validItemResource();
        itemResource.setAmount(-2.5);
        useCase.execute(itemResource)
                .test()
                .assertError(throwable -> {
                    InvalidItemException invalidItemException = (InvalidItemException) throwable;
                    return invalidItemException.getViolations().contains(createViolation("price", "price.is.invalid"));
                });
    }

    @Test
    void givenNegativeQuantityWhenCreateThenShouldThrowExceptionWithViolation() {
        ItemResource itemResource = validItemResource();
        itemResource.setQuantity(-2);
        useCase.execute(itemResource)
                .test()
                .assertError(throwable -> {
                    InvalidItemException invalidItemException = (InvalidItemException) throwable;
                    return invalidItemException.getViolations().contains(createViolation("quantity", "quantity.is.invalid"));
                });
    }

    @Test
    void givenValidItemWhenCreateThenItemShouldBeExistInTheRepository() {
        ItemResource itemResource = validItemResource();
        useCase.execute(itemResource).test()
                .assertOf(observer -> {
                    CreateItemResponse response = observer.values().get(0);
                    Single<ListingItem> itemSingle = itemsRepository.findByReference(response.getCreatedItem().getReference());
                    itemSingle.test().assertValue(Objects::nonNull);
                });
    }

    @Test
    void givenValidItemWhenCreateThenShouldReturnItemWithId() {
        ItemResource itemResource = validItemResource();
        useCase.execute(itemResource)
                .test()
                .assertValue(response -> {
                    ItemResource createdItem = response.getCreatedItem();
                    return nonNull(createdItem) && nonNull(createdItem.getReference());
                });
    }

    @Test
    void givenItemResourceWithNonExistImageReferenceWhenExecuteThenShouldThrowException() {
        ItemResource itemResource = validItemResource();
        itemResource.setImageReferences(Collections.singletonList("non exist image reference"));
        useCase.execute(itemResource)
                .test()
                .assertError(ImageNotFoundException.class)
                .assertErrorMessage("non exist image reference");
    }

    @Test
    void givenItemResourceWithExistImageReferenceWhenExecuteThenImageShouldLinkImageWithItem() {
        ItemResource itemResource = validItemResource();
        CreateItemResponse response = useCase.execute(itemResource)
                .test()
                .values()
                .get(0);

        imagesRepository.findByReference(EXIST_IMAGE_REFERENCE)
                .test()
                .assertValue(itemImage -> {
                    boolean equalsReference = itemImage.itemReference().equals(response.getCreatedItem().getReference());
                    boolean containsReference = response.getCreatedItem().getImageReferences().contains(EXIST_IMAGE_REFERENCE);
                    return equalsReference && containsReference;
                });
    }
}
