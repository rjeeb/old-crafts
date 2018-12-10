package org.dominokit.craft.items.server.handlers;

import org.dominokit.domino.api.server.handler.Handler;
import org.dominokit.domino.api.server.handler.RequestHandler;
import org.dominokit.domino.api.server.context.ExecutionContext;
import org.dominokit.craft.items.shared.response.ItemsResponse;
import org.dominokit.craft.items.shared.request.ItemsRequest;

import java.util.logging.Logger;

@Handler("ItemsRequest")
public class ItemsHandler implements RequestHandler<ItemsRequest, ItemsResponse> {
    private static final Logger LOGGER= Logger.getLogger(ItemsHandler.class.getName());
    @Override
    public void handleRequest(ExecutionContext<ItemsRequest, ItemsResponse> executionContext) {
        LOGGER.info("message recieved from client : "+executionContext.getRequestBean().getMessage());
        executionContext.end(new ItemsResponse("Server message"));
    }
}
