package org.dominokit.craft.usecase;

import org.dominokit.craft.exception.ImageNotFoundException;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.mapper.ItemImageResourceMapper;
import org.dominokit.craft.model.ImmutableItemImage;
import org.dominokit.craft.usecase.repository.TestImagesRepository;
import org.junit.jupiter.api.Test;

import static java.util.Objects.nonNull;

public class DeleteImageUseCaseTest extends BaseUseCaseTest<DeleteImageUseCase> {

    private static final String SOME_PATH = "some path";
    private TestImagesRepository imagesRepository;

    @Override
    protected DeleteImageUseCase createUseCase() {
        imagesRepository = new TestImagesRepository();
        return new DeleteImageUseCase(imagesRepository, new ItemImageResourceMapper());
    }

    @Test
    void givenNullImageReferenceWhenExecuteThenShouldThrowException() {
        useCase.execute(new ImageResource())
                .test()
                .assertError(ImageNotFoundException.class);
    }

    @Test
    void givenEmptyImageReferenceWhenExecuteThenShouldThrowException() {
        ImageResource imageResource = new ImageResource();
        imageResource.setReference("");
        useCase.execute(imageResource)
                .test()
                .assertError(ImageNotFoundException.class);
    }

    @Test
    void givenValidImageResourceWhenExecuteThenImageShouldBeSavedInTheRepositoryWithReference() {
        imagesRepository.save(ImmutableItemImage.builder()
                .path(SOME_PATH)
                .reference("some reference")
                .build())
                .subscribe();

        ImageResource imageResource = new ImageResource();
        imageResource.setReference("some reference");
        imageResource.setImagePath(SOME_PATH);

        useCase.execute(imageResource)
                .test()
                .assertOf(observer -> {
                    observer.assertValue(response -> nonNull(response.getDeletedImage()));
                    imagesRepository.findByReference(observer.values().get(0).getDeletedImage().getReference())
                            .test()
                            .assertError(ImageNotFoundException.class)
                            .assertErrorMessage("some reference");
                });
    }
}
