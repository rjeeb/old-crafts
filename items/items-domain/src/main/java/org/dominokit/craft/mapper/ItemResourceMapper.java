package org.dominokit.craft.mapper;

import org.dominokit.craft.exception.InvalidItemException;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.items.shared.model.Violation;
import org.dominokit.craft.model.ImmutableListingItem;
import org.dominokit.craft.model.ListingItem;
import org.dominokit.craft.model.ItemType;
import org.dominokit.craft.model.RenewalOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

public class ItemResourceMapper implements ItemMapper<ItemResource> {

    private List<String> getImageReferences(ItemResource itemResource) {
        return isNull(itemResource.getImageReferences()) ? new ArrayList<>() : itemResource.getImageReferences();
    }

    private RenewalOptions getRenewalOption(ItemResource itemResource) {
        if (isNull(itemResource.getRenewalOption())) {
            return null;
        }
        try {
            return RenewalOptions.valueOf(itemResource.getRenewalOption());
        } catch (IllegalArgumentException e) {
            throw new InvalidItemException(Collections.singletonList(new Violation("renewalOption", "renewalOption.is.invalid")));
        }
    }

    private ItemType getItemType(ItemResource itemResource) {
        if (isNull(itemResource.getType())) {
            return null;
        }
        try {
            return ItemType.valueOf(itemResource.getType());
        } catch (IllegalArgumentException e) {
            throw new InvalidItemException(Collections.singletonList(new Violation("itemType", "itemType.is.invalid")));
        }
    }

    @Override
    public ListingItem toModel(ItemResource itemResource) {
        return ImmutableListingItem.builder()
                .reference(itemResource.getReference())
                .title(itemResource.getTitle())
                .whoMadeIt(itemResource.getWhoMadeIt())
                .whatIsIt(itemResource.getWhatIsIt())
                .whenWasItMade(itemResource.getWhenWasItMade())
                .category(itemResource.getCategory())
                .renewalOption(getRenewalOption(itemResource))
                .itemType(getItemType(itemResource))
                .description(itemResource.getDescription())
                .price(itemResource.getAmount())
                .quantity(itemResource.getQuantity())
                .addAllImageReferences(getImageReferences(itemResource))
                .build();
    }

    @Override
    public ItemResource fromModel(ListingItem listingItem) {
        ItemResource itemResource = new ItemResource();
        itemResource.setReference(listingItem.reference());
        itemResource.setTitle(listingItem.title());
        itemResource.setWhoMadeIt(listingItem.whoMadeIt());
        itemResource.setWhatIsIt(listingItem.whatIsIt());
        itemResource.setWhenWasItMade(listingItem.whenWasItMade());
        itemResource.setCategory(listingItem.category());
        itemResource.setRenewalOption(listingItem.renewalOption().name());
        itemResource.setType(listingItem.itemType().name());
        itemResource.setDescription(listingItem.description());
        itemResource.setAmount(listingItem.price());
        itemResource.setQuantity(listingItem.quantity());
        itemResource.setImageReferences(listingItem.imageReferences());
        return itemResource;
    }
}
