package org.dominokit.craft.usecase;

import org.dominokit.craft.exception.InvalidImageException;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.mapper.ItemImageResourceMapper;
import org.dominokit.craft.repository.ImagesRepository;
import org.dominokit.craft.usecase.repository.TestImagesRepository;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static java.util.Objects.nonNull;

public class UploadImageUseCaseTest extends BaseUseCaseTest<UploadImageUseCase> {

    private ImagesRepository imagesRepository;

    @Override
    protected UploadImageUseCase createUseCase() {
        imagesRepository = new TestImagesRepository();
        return new UploadImageUseCase(imagesRepository, new ItemImageResourceMapper());
    }

    @Test
    void givenNullImagePathWhenExecuteThenShouldThrowException() {
        useCase.execute(new ImageResource())
                .test()
                .assertError(throwable -> {
                    InvalidImageException invalidImageException = (InvalidImageException) throwable;
                    return invalidImageException.getViolations().contains(createViolation("path", "path.is.null"));
                });
    }

    @Test
    void givenEmptyImagePathWhenExecuteThenShouldThrowException() {
        ImageResource imageResource = new ImageResource();
        imageResource.setImagePath("");
        useCase.execute(imageResource)
                .test()
                .assertError(throwable -> {
                    InvalidImageException invalidImageException = (InvalidImageException) throwable;
                    return invalidImageException.getViolations().contains(createViolation("path", "path.is.empty"));
                });
    }

    @Test
    void givenValidImageResourceWhenExecuteThenImageShouldBeSavedInTheRepositoryWithReference() {
        ImageResource imageResource = new ImageResource();
        imageResource.setImagePath("some path");
        useCase.execute(imageResource)
                .test()
                .assertOf(observer -> {
                    observer.assertValue(response -> nonNull(response.getCreatedImage().getReference()));
                    imagesRepository.findByReference(observer.values().get(0).getCreatedImage().getReference())
                            .test()
                            .assertValue(Objects::nonNull);
                });
    }
}
