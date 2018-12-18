package org.dominokit.craft.category.client.requests;

import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.domino.api.shared.request.ArrayResponse;

public class TestCategoryRequests implements CategoryRequests {

    @Override
    public Response<ItemResources> list() {
        ItemResource ItemResource = new ItemResource();
        ItemResource.setImageUrl("https://picsum.photos/250/200/?image=998");
        ItemResource.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        ItemResource.setDescription("MignonandMignon");
        ItemResource.setAmount(14.50);

        ItemResource itemResource1 = new ItemResource();
        itemResource1.setImageUrl("https://picsum.photos/250/200/?image=942");
        itemResource1.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        itemResource1.setDescription("MignonandMignon");
        itemResource1.setAmount(14.50);

        ItemResource itemResource2 = new ItemResource();
        itemResource2.setImageUrl("https://picsum.photos/250/200/?image=885");
        itemResource2.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        itemResource2.setDescription("MignonandMignon");
        itemResource2.setAmount(14.50);

        ItemResource itemResource3 = new ItemResource();
        itemResource3.setImageUrl("https://picsum.photos/250/200/?image=882");
        itemResource3.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        itemResource3.setDescription("MignonandMignon");
        itemResource3.setAmount(14.50);

        ItemResources itemResources = new ItemResources();
        itemResources.getItems().add(itemResource1);
        itemResources.getItems().add(itemResource2);
        itemResources.getItems().add(itemResource3);
        return new TestResponse<>(itemResources);
    }
}
