package org.dominokit.craft.items.server.handlers;

import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.server.context.ExecutionContext;
import org.dominokit.domino.api.server.handler.Handler;
import org.dominokit.domino.api.server.handler.RequestHandler;
import org.dominokit.domino.api.shared.request.ArrayResponse;
import org.dominokit.domino.api.shared.request.VoidRequest;

@Handler("items/list")
public class ListItemsHandler implements RequestHandler<VoidRequest, ArrayResponse<ItemResource>> {
    @Override
    public void handleRequest(ExecutionContext<VoidRequest, ArrayResponse<ItemResource>> executionContext) {
        executionContext.end(new ArrayResponse<>(new ItemResource[]{}));
    }
}
