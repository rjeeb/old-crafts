package org.dominokit.craft.mapper;

import org.dominokit.craft.exception.InvalidItemException;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.items.shared.model.Violation;
import org.dominokit.craft.model.ImmutableItem;
import org.dominokit.craft.model.Item;
import org.dominokit.craft.model.ItemType;
import org.dominokit.craft.model.RenewalOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

public class ItemsMapper {

    public static ItemsMapper create() {
        return new ItemsMapper();
    }

    public ItemResource toResource(Item item) {
        ItemResource itemResource = new ItemResource();
        itemResource.setReference(item.reference());
        itemResource.setTitle(item.title());
        itemResource.setWhoMadeIt(item.whoMadeIt());
        itemResource.setWhatIsIt(item.whatIsIt());
        itemResource.setWhenWasItMade(item.whenWasItMade());
        itemResource.setCategory(item.category());
        itemResource.setRenewalOption(item.renewalOption().name());
        itemResource.setType(item.itemType().name());
        itemResource.setDescription(item.description());
        itemResource.setAmount(item.price());
        itemResource.setQuantity(item.quantity());
        itemResource.setImageReferences(item.imageReferences());
        return itemResource;
    }

    public Item fromResource(ItemResource itemResource) {
        return ImmutableItem.builder()
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
}
