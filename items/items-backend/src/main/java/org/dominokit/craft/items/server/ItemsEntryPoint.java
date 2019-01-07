package org.dominokit.craft.items.server;

import com.google.auto.service.AutoService;
import org.dominokit.domino.api.server.entrypoint.ServerAppEntryPoint;
import org.dominokit.domino.api.server.entrypoint.VertxContext;

@AutoService(ServerAppEntryPoint.class)
public class ItemsEntryPoint implements ServerAppEntryPoint<VertxContext> {
    @Override
    public void onModulesLoaded(VertxContext context) {
        UseCaseFactory.init(context);
    }
}
