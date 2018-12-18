package org.dominokit.craft.category.client.requests;

import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.client.annotations.Path;
import org.dominokit.domino.api.client.annotations.RequestFactory;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.domino.api.shared.request.ArrayResponse;

@RequestFactory
public interface CategoryRequests {
    @Path(value = "items", method = "GET")
    Response<ItemResources> list();
}
