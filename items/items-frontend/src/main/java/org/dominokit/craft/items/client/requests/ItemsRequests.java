package org.dominokit.craft.items.client.requests;

import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.client.annotations.Path;
import org.dominokit.domino.api.client.annotations.RequestFactory;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.domino.api.shared.request.VoidResponse;

@RequestFactory
public interface ItemsRequests {
    @Path("items")
    Response<ItemResource> create(ItemResource itemResource);

    @Path("images/delete")
    Response<VoidResponse> removeImage(ImageResource imageResource);
}
