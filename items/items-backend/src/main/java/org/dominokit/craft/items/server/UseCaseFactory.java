package org.dominokit.craft.items.server;

import org.dominokit.craft.repository.ImagesRepository;
import org.dominokit.craft.repository.InMemoryImagesRepository;
import org.dominokit.craft.repository.InMemoryItemsRepository;
import org.dominokit.craft.repository.ItemsRepository;
import org.dominokit.craft.usecase.CreateItemUseCase;
import org.dominokit.craft.usecase.UploadImageUseCase;

public class UseCaseFactory {
    private ItemsRepository itemsRepository = new InMemoryItemsRepository();
    private ImagesRepository imagesRepository = new InMemoryImagesRepository();

    private static final UseCaseFactory INSTANCE = new UseCaseFactory();

    public static UseCaseFactory getInstance() {
        return INSTANCE;
    }

    public CreateItemUseCase createItemUseCase() {
        return new CreateItemUseCase(itemsRepository, imagesRepository);
    }

    public UploadImageUseCase uploadImageUseCase() {
        return new UploadImageUseCase(imagesRepository);
    }
}
