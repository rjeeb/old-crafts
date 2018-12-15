package org.dominokit.craft.category.client.requests;

import org.dominokit.craft.items.shared.model.Item;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.domino.api.shared.request.ArrayResponse;

public class TestCategoryRequests implements CategoryRequests {

    @Override
    public Response<ArrayResponse<Item>> list() {
        Item Item = new Item();
        Item.setImageUrl("https://picsum.photos/250/200/?image=998");
        Item.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        Item.setDescription("MignonandMignon");
        Item.setAmount("USD 14.50");

        Item Item1 = new Item();
        Item1.setImageUrl("https://picsum.photos/250/200/?image=942");
        Item1.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        Item1.setDescription("MignonandMignon");
        Item1.setAmount("USD 14.50");

        Item Item2 = new Item();
        Item2.setImageUrl("https://picsum.photos/250/200/?image=885");
        Item2.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        Item2.setDescription("MignonandMignon");
        Item2.setAmount("USD 14.50");

        Item Item3 = new Item();
        Item3.setImageUrl("https://picsum.photos/250/200/?image=882");
        Item3.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        Item3.setDescription("MignonandMignon");
        Item3.setAmount("USD 14.50");

        return new TestResponse<>(new ArrayResponse<>(new Item[]{Item, Item1, Item2, Item3}));
    }
}
