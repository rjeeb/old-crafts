package org.dominokit.craft.items.server.handlers;

import org.dominokit.craft.items.server.UseCaseFactory;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.items.shared.model.Violation;
import org.dominokit.craft.items.shared.model.Violations;
import org.dominokit.craft.items.shared.model.Violations_MapperImpl;
import org.dominokit.craft.exception.InvalidItemException;
import org.dominokit.craft.response.CreateItemResponse;
import org.dominokit.domino.api.server.context.ExecutionContext;
import org.dominokit.domino.api.server.handler.Handler;
import org.dominokit.domino.api.server.handler.RequestHandler;

import java.util.List;

@Handler("items")
public class CreateItemHandler implements RequestHandler<ItemResource, ItemResource> {
    @Override
    public void handleRequest(ExecutionContext<ItemResource, ItemResource> executionContext) {
        try {
            CreateItemResponse response = UseCaseFactory.getInstance()
                    .createItemUseCase()
                    .execute(executionContext.getRequestBean());
            executionContext.statusCode(201).end(response.getCreatedItem());
        } catch (InvalidItemException e) {
            List<Violation> violationsList = e.getViolations();
            Violations violations = new Violations();
            violations.setViolations(violationsList);
            executionContext.statusCode(400).end(Violations_MapperImpl.INSTANCE.write(violations));
        }
    }
}
