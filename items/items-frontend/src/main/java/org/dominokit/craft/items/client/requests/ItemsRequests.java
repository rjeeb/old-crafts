package org.dominokit.craft.items.client.requests;

import org.dominokit.domino.api.client.annotations.Path;
import org.dominokit.domino.api.client.annotations.RequestFactory;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.craft.items.shared.response.ItemsResponse;
import org.dominokit.craft.items.shared.request.ItemsRequest;

@RequestFactory
public interface ItemsRequests {
    @Path("ItemsRequest")
    Response<ItemsResponse> request(ItemsRequest request);
}
