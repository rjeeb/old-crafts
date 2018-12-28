package org.dominokit.craft.usecase;

import org.dominokit.craft.exception.InvalidImageException;
import org.dominokit.craft.repository.ImagesRepository;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.response.UploadImageResponse;
import org.dominokit.craft.usecase.repository.TestImagesRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class UploadImageUseCaseTest extends BaseUseCaseTest<UploadImageUseCase> {

    private ImagesRepository imagesRepository;

    @Override
    protected UploadImageUseCase createUseCase() {
        imagesRepository = new TestImagesRepository();
        return new UploadImageUseCase(imagesRepository);
    }

    @Test
    void givenNullImagePathWhenExecuteThenShouldThrowException() {
        InvalidImageException invalidImageException = (InvalidImageException) catchThrowable(() -> useCase.execute(new ImageResource()));
        assertThat(invalidImageException.getViolations()).containsExactly(createViolation("path", "path.is.null"));
    }

    @Test
    void givenEmptyImagePathWhenExecuteThenShouldThrowException() {
        InvalidImageException invalidImageException = (InvalidImageException) catchThrowable(() -> {
            ImageResource imageResource = new ImageResource();
            imageResource.setImagePath("");
            useCase.execute(imageResource);
        });
        assertThat(invalidImageException.getViolations()).containsExactly(createViolation("path", "path.is.empty"));
    }

    @Test
    void givenValidImageResourceWhenExecuteThenImageShouldBeSavedInTheRepositoryWithReference() {
        ImageResource imageResource = new ImageResource();
        imageResource.setImagePath("some path");
        UploadImageResponse response = useCase.execute(imageResource);

        assertThat(response.getCreatedImage().getReference()).isNotEmpty();
        assertThat(imagesRepository.findByReference(response.getCreatedImage().getReference()))
                .isNotNull();
    }
}
