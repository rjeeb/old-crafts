package org.dominokit.craft.repository;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.RedisClient;
import org.dominokit.craft.mapper.ItemMapper;
import org.dominokit.craft.model.ListingItem;
import org.dominokit.craft.repository.entity.ItemEntity;
import org.dominokit.craft.repository.entity.ItemEntity_MapperImpl;

import java.util.Set;

public class RedisItemsRepository implements ItemsRepository {

    private static final String ITEMS_HASH_KEY = "items";
    private final RedisClient redisClient;
    private final ItemMapper<ItemEntity> mapper;

    public RedisItemsRepository(RedisClient redisClient, ItemMapper<ItemEntity> mapper) {
        this.redisClient = redisClient;
        this.mapper = mapper;
    }

    @Override
    public Single<ListingItem> save(ListingItem listingItem) {
        return Single.create(emitter -> {
            ItemEntity entityToSave = mapper.fromModel(listingItem);
            redisClient.hset(ITEMS_HASH_KEY, entityToSave.getReference(), ItemEntity_MapperImpl.INSTANCE.write(entityToSave), event -> {
                if (event.succeeded()) {
                    emitter.onSuccess(listingItem);
                } else {
                    emitter.onError(event.cause());
                }
            });
        });
    }

    @Override
    public Observable<ListingItem> findAll() {
        return Observable.create(emitter -> {
            redisClient.hgetall(ITEMS_HASH_KEY, event -> {
                if (event.succeeded()) {
                    JsonObject result = event.result();
                    Set<String> keys = result.fieldNames();
                    for (String key : keys) {
                        ItemEntity entity = ItemEntity_MapperImpl.INSTANCE.read(result.getString(key));
                        emitter.onNext(mapper.toModel(entity));
                    }
                } else {
                    emitter.onError(event.cause());
                }
            });
        });
    }

    @Override
    public Single<ListingItem> findByReference(String reference) {
        return Single.create(emitter -> {
            redisClient.hget(ITEMS_HASH_KEY, reference, event -> {
                if (event.succeeded()) {
                    ItemEntity entity = ItemEntity_MapperImpl.INSTANCE.read(event.result());
                    emitter.onSuccess(mapper.toModel(entity));
                } else {
                    emitter.onError(event.cause());
                }
            });
        });
    }
}
