package org.dominokit.craft.items.server.handlers;

import org.dominokit.craft.items.server.UseCaseFactory;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.domino.api.server.context.ExecutionContext;
import org.dominokit.domino.api.server.handler.Handler;
import org.dominokit.domino.api.server.handler.RequestHandler;
import org.dominokit.domino.api.shared.request.VoidResponse;

@Handler("images/delete")
public class DeleteImageHandler implements RequestHandler<ImageResource, VoidResponse> {
    @Override
    public void handleRequest(ExecutionContext<ImageResource, VoidResponse> executionContext) {
        UseCaseFactory.create()
                .deleteImageUseCase()
                .execute(executionContext.getRequestBean())
                .doOnSubscribe(disposable -> executionContext.endHandler(disposable::dispose))
                .doOnError(throwable -> executionContext.statusCode(400).end(throwable.getMessage()))
                .doOnSuccess(response -> executionContext.statusCode(200).end())
                .subscribe();
    }
}
