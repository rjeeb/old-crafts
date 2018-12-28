package org.dominokit.craft.usecase;

import org.dominokit.craft.exception.ImageNotFoundException;
import org.dominokit.craft.exception.InvalidItemException;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.model.ImmutableItemImage;
import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.response.CreateItemResponse;
import org.dominokit.craft.usecase.repository.TestImagesRepository;
import org.dominokit.craft.usecase.repository.TestItemsRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class CreateItemUseCaseTest extends BaseUseCaseTest<CreateItemUseCase> {

    private static final String EXIST_IMAGE_REFERENCE = "image reference";
    private TestItemsRepository itemsRepository;
    private TestImagesRepository imagesRepository;

    @Override
    protected void initTest() {
        validItemResource();
        imagesRepository.save(ImmutableItemImage.builder()
                .reference(EXIST_IMAGE_REFERENCE)
                .path("image path")
                .build());
    }

    @Override
    protected CreateItemUseCase createUseCase() {
        itemsRepository = new TestItemsRepository();
        imagesRepository = new TestImagesRepository();
        return new CreateItemUseCase(itemsRepository, imagesRepository);
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
        InvalidItemException invalidItemException = (InvalidItemException) catchThrowable(() -> useCase.execute(itemResource));
        assertThat(invalidItemException.getViolations()).isNotEmpty();
        assertThat(invalidItemException.getViolations())
                .containsExactly(createViolation(property, property + ".is.null"));
    }

    private void assertInvalidProperty(String property, ItemResource itemResource) {
        InvalidItemException invalidItemException = (InvalidItemException) catchThrowable(() -> useCase.execute(itemResource));
        assertThat(invalidItemException.getViolations()).isNotEmpty();
        assertThat(invalidItemException.getViolations())
                .containsExactly(createViolation(property, property + ".is.invalid"));
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
        InvalidItemException invalidItemException = (InvalidItemException) catchThrowable(() -> useCase.execute(itemResource));
        assertThat(invalidItemException.getViolations()).containsExactly(createViolation("price", "price.is.invalid"));
    }

    @Test
    void givenNegativeQuantityWhenCreateThenShouldThrowExceptionWithViolation() {
        ItemResource itemResource = validItemResource();
        itemResource.setQuantity(-2);
        InvalidItemException invalidItemException = (InvalidItemException) catchThrowable(() -> useCase.execute(itemResource));
        assertThat(invalidItemException.getViolations()).containsExactly(createViolation("quantity", "quantity.is.invalid"));
    }

    @Test
    void givenValidItemWhenCreateThenItemShouldBeExistInTheRepository() {
        ItemResource itemResource = validItemResource();
        useCase.execute(itemResource);
        assertThat(itemsRepository.findByTitle(itemResource.getTitle())).isNotNull();
    }

    @Test
    void givenValidItemWhenCreateThenShouldReturnItemWithId() {
        ItemResource itemResource = validItemResource();
        CreateItemResponse response = useCase.execute(itemResource);
        ItemResource result = response.getCreatedItem();
        assertThat(result).isNotNull();
        assertThat(result.getReference()).isNotEmpty();
    }

    @Test
    void givenItemResourceWithNonExistImageReferenceWhenExecuteThenShouldThrowException() {
        ItemResource itemResource = validItemResource();
        itemResource.setImageReferences(Collections.singletonList("non exist image reference"));
        assertThatThrownBy(() -> useCase.execute(itemResource))
                .isInstanceOf(ImageNotFoundException.class)
                .hasMessage("non exist image reference");
    }

    @Test
    void givenItemResourceWithExistImageReferenceWhenExecuteThenImageShouldLinkImageWithItem() {
        ItemResource itemResource = validItemResource();
        CreateItemResponse response = useCase.execute(itemResource);
        ItemImage itemImage = imagesRepository.findByReference(EXIST_IMAGE_REFERENCE);
        assertThat(itemImage.itemReference().get())
                .isEqualTo(response.getCreatedItem().getReference());
        assertThat(response.getCreatedItem().getImageReferences())
                .containsExactly(EXIST_IMAGE_REFERENCE);
    }
}
