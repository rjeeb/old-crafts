package org.dominokit.craft.layout.server.handlers;

import org.dominokit.domino.api.server.handler.Handler;
import org.dominokit.domino.api.server.handler.RequestHandler;
import org.dominokit.domino.api.server.context.ExecutionContext;
import org.dominokit.craft.layout.shared.response.LayoutResponse;
import org.dominokit.craft.layout.shared.request.LayoutRequest;

import java.util.logging.Logger;

@Handler("LayoutRequest")
public class LayoutHandler implements RequestHandler<LayoutRequest, LayoutResponse> {
    private static final Logger LOGGER= Logger.getLogger(LayoutHandler.class.getName());
    @Override
    public void handleRequest(ExecutionContext<LayoutRequest, LayoutResponse> executionContext) {
        LOGGER.info("message recieved from client : "+executionContext.getRequestBean().getMessage());
        executionContext.end(new LayoutResponse("Server message"));
    }
}
