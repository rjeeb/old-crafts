package org.dominokit.craft.items.server;

import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
import org.dominokit.craft.mapper.ItemImageResourceMapper;
import org.dominokit.craft.mapper.ItemResourceMapper;
import org.dominokit.craft.repository.ImagesRepository;
import org.dominokit.craft.repository.ItemsRepository;
import org.dominokit.craft.repository.RedisImagesRepository;
import org.dominokit.craft.repository.RedisItemsRepository;
import org.dominokit.craft.repository.mapper.ItemEntityMapper;
import org.dominokit.craft.repository.mapper.ItemImageEntityMapper;
import org.dominokit.craft.usecase.CreateItemUseCase;
import org.dominokit.craft.usecase.DeleteImageUseCase;
import org.dominokit.craft.usecase.UploadImageUseCase;
import org.dominokit.domino.api.server.entrypoint.VertxContext;

public class UseCaseFactory {
    private static ItemsRepository itemsRepository;
    private static ImagesRepository imagesRepository;

    private static final UseCaseFactory INSTANCE = new UseCaseFactory();
    private static ItemResourceMapper itemResourceMapper;
    private static ItemImageResourceMapper itemImageResourceMapper;

    public static UseCaseFactory create() {
        return INSTANCE;
    }

    public static void init(VertxContext vertxContext) {
        itemResourceMapper = new ItemResourceMapper();
        itemImageResourceMapper = new ItemImageResourceMapper();

        RedisOptions redisOptions = new RedisOptions()
                .setAuth("n9ep2rwc2JJD6900+hpPET+27IAlwp4eagrcuAUn1M7z0wZy2YWKN8cjF2zqwZljchA72hMdoGd7twmp");
        RedisClient redisClient = RedisClient.create(vertxContext.vertx(), redisOptions);
        imagesRepository = new RedisImagesRepository(redisClient, new ItemImageEntityMapper());
        itemsRepository = new RedisItemsRepository(redisClient, new ItemEntityMapper());
    }

    public CreateItemUseCase createItemUseCase() {
        return new CreateItemUseCase(itemsRepository, imagesRepository, itemResourceMapper);
    }

    public UploadImageUseCase uploadImageUseCase() {
        return new UploadImageUseCase(imagesRepository, itemImageResourceMapper);
    }

    public DeleteImageUseCase deleteImageUseCase() {
        return new DeleteImageUseCase(imagesRepository, itemImageResourceMapper);
    }
}
