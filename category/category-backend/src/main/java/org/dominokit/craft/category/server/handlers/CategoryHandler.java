package org.dominokit.craft.category.server.handlers;

import org.dominokit.domino.api.server.handler.Handler;
import org.dominokit.domino.api.server.handler.RequestHandler;
import org.dominokit.domino.api.server.context.ExecutionContext;
import org.dominokit.craft.category.shared.response.CategoryResponse;
import org.dominokit.craft.category.shared.request.CategoryRequest;

import java.util.logging.Logger;

@Handler("CategoryRequest")
public class CategoryHandler implements RequestHandler<CategoryRequest, CategoryResponse> {
    private static final Logger LOGGER= Logger.getLogger(CategoryHandler.class.getName());
    @Override
    public void handleRequest(ExecutionContext<CategoryRequest, CategoryResponse> executionContext) {
        LOGGER.info("message recieved from client : "+executionContext.getRequestBean().getMessage());
        executionContext.end(new CategoryResponse("Server message"));
    }
}
