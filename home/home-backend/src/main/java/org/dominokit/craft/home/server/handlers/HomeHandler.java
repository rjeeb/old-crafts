package org.dominokit.craft.home.server.handlers;

import org.dominokit.domino.api.server.handler.Handler;
import org.dominokit.domino.api.server.handler.RequestHandler;
import org.dominokit.domino.api.server.context.ExecutionContext;
import org.dominokit.craft.home.shared.response.HomeResponse;
import org.dominokit.craft.home.shared.request.HomeRequest;

import java.util.logging.Logger;

@Handler("HomeRequest")
public class HomeHandler implements RequestHandler<HomeRequest, HomeResponse> {
    private static final Logger LOGGER= Logger.getLogger(HomeHandler.class.getName());
    @Override
    public void handleRequest(ExecutionContext<HomeRequest, HomeResponse> executionContext) {
        LOGGER.info("message recieved from client : "+executionContext.getRequestBean().getMessage());
        executionContext.end(new HomeResponse("Server message"));
    }
}
