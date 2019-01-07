package org.dominokit.craft.repository;

import io.reactivex.Single;
import io.vertx.redis.RedisClient;
import org.dominokit.craft.exception.ImageNotFoundException;
import org.dominokit.craft.mapper.ItemImageMapper;
import org.dominokit.craft.model.ItemImage;
import org.dominokit.craft.repository.entity.ImageEntity;
import org.dominokit.craft.repository.entity.ImageEntity_MapperImpl;

public class RedisImagesRepository implements ImagesRepository {

    private static final String ITEM_IMAGES_HASH_KEY = "item:images";
    private final RedisClient redisClient;
    private final ItemImageMapper<ImageEntity> mapper;

    public RedisImagesRepository(RedisClient redisClient, ItemImageMapper<ImageEntity> mapper) {
        this.redisClient = redisClient;
        this.mapper = mapper;
    }

    @Override
    public Single<ItemImage> findByReference(String reference) {
        return Single.create(emitter -> redisClient.hget(ITEM_IMAGES_HASH_KEY, reference, event -> {
            if (event.succeeded()) {
                ImageEntity entity = ImageEntity_MapperImpl.INSTANCE.read(event.result());
                emitter.onSuccess(mapper.toModel(entity));
            } else {
                emitter.onError(new ImageNotFoundException(reference));
            }
        }));
    }

    @Override
    public Single<ItemImage> save(ItemImage itemImage) {
        return Single.create(emitter -> {
            ImageEntity entity = mapper.fromModel(itemImage);
            redisClient.hset(ITEM_IMAGES_HASH_KEY, entity.getReference(), ImageEntity_MapperImpl.INSTANCE.write(entity), event -> {
                if (event.succeeded()) {
                    emitter.onSuccess(itemImage);
                } else {
                    emitter.onError(new IllegalStateException());
                }
            });
        });
    }

    @Override
    public Single<ItemImage> remove(ItemImage itemImage) {
        return Single.create(emitter -> {
            ImageEntity entity = mapper.fromModel(itemImage);
            redisClient.hdel(ITEM_IMAGES_HASH_KEY, entity.getReference(), event -> {
                if (event.succeeded()) {
                    emitter.onSuccess(itemImage);
                } else {
                    emitter.onError(new IllegalStateException());
                }
            });
        });
    }
}
