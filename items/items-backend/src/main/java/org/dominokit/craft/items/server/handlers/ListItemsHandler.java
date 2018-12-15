package org.dominokit.craft.items.server.handlers;

import org.dominokit.craft.items.shared.model.Item;
import org.dominokit.domino.api.server.context.ExecutionContext;
import org.dominokit.domino.api.server.handler.Handler;
import org.dominokit.domino.api.server.handler.RequestHandler;
import org.dominokit.domino.api.server.request.MultiMap;
import org.dominokit.domino.api.shared.request.ArrayResponse;
import org.dominokit.domino.api.shared.request.VoidRequest;

@Handler("items")
public class ListItemsHandler implements RequestHandler<VoidRequest, ArrayResponse<Item>> {
    @Override
    public void handleRequest(ExecutionContext<VoidRequest, ArrayResponse<Item>> executionContext) {
        MultiMap<String, String> parameters = executionContext.parameters();
        parameters.forEach(stringStringEntry -> {
            System.out.println(stringStringEntry.getKey() + " " + stringStringEntry.getValue());
        });
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

        ArrayResponse<Item> response = new ArrayResponse<>();
        response.setItems(new Item[]{Item, Item1, Item2, Item3});
        executionContext.end(response);
    }
}
