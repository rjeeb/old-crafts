package org.dominokit.craft.layout.client.requests;

import org.dominokit.domino.api.client.annotations.Path;
import org.dominokit.domino.api.client.annotations.RequestFactory;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.craft.layout.shared.response.LayoutResponse;
import org.dominokit.craft.layout.shared.request.LayoutRequest;

@RequestFactory
public interface LayoutRequests {
    @Path("LayoutRequest")
    Response<LayoutResponse> request(LayoutRequest request);
}
