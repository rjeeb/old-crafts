package org.dominokit.craft.items.server.handlers;

import org.dominokit.craft.items.server.UseCaseFactory;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.server.context.ExecutionContext;
import org.dominokit.domino.api.server.handler.Handler;
import org.dominokit.domino.api.server.handler.RequestHandler;
import org.dominokit.domino.api.shared.request.ArrayResponse;
import org.dominokit.domino.api.shared.request.VoidRequest;

import java.util.List;

@Handler("items")
public class ListItemsHandler implements RequestHandler<VoidRequest, ArrayResponse<ItemResource>> {
    @Override
    public void handleRequest(ExecutionContext<VoidRequest, ArrayResponse<ItemResource>> executionContext) {
        List<ItemResource> itemResources = UseCaseFactory.getInstance().findAllItemsUseCase()
                .execute();
        executionContext.end(new ArrayResponse<>(itemResources.toArray(new ItemResource[0])));
    }
}
