package org.dominokit.craft.repository.mapper;

import org.dominokit.craft.mapper.ItemMapper;
import org.dominokit.craft.model.ImmutableListingItem;
import org.dominokit.craft.model.ListingItem;
import org.dominokit.craft.model.ItemType;
import org.dominokit.craft.model.RenewalOptions;
import org.dominokit.craft.repository.entity.ItemEntity;

import static java.util.Objects.nonNull;

public class ItemEntityMapper implements ItemMapper<ItemEntity> {

    @Override
    public ItemEntity fromModel(ListingItem listingItem) {
        ItemEntity entity = new ItemEntity();
        entity.setReference(listingItem.reference());
        entity.setAmount(listingItem.price());
        entity.setCategory(listingItem.category());
        entity.setDescription(listingItem.description());
        entity.setImageReferences(listingItem.imageReferences());
        entity.setQuantity(listingItem.quantity());
        entity.setRenewalOption(nonNull(listingItem.renewalOption()) ? listingItem.renewalOption().name() : null);
        entity.setSection(listingItem.section());
        entity.setType(nonNull(listingItem.itemType()) ? listingItem.itemType().name() : null);
        entity.setWhatIsIt(listingItem.whatIsIt());
        entity.setWhenWasItMade(listingItem.whenWasItMade());
        entity.setWhoMadeIt(listingItem.whoMadeIt());
        entity.setTitle(listingItem.title());
        return entity;
    }

    @Override
    public ListingItem toModel(ItemEntity entity) {
        return ImmutableListingItem.builder()
                .reference(entity.getReference())
                .price(entity.getAmount())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .imageReferences(entity.getImageReferences())
                .quantity(entity.getQuantity())
                .renewalOption(nonNull(entity.getRenewalOption()) ? RenewalOptions.valueOf(entity.getRenewalOption()) : null)
                .section(entity.getSection())
                .itemType(nonNull(entity.getType()) ? ItemType.valueOf(entity.getType()) : null)
                .whatIsIt(entity.getWhatIsIt())
                .whenWasItMade(entity.getWhenWasItMade())
                .whoMadeIt(entity.getWhoMadeIt())
                .title(entity.getTitle())
                .build();
    }
}
