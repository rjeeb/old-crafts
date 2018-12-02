package org.dominokit.craft.home.client.requests.news;

import org.dominokit.craft.home.shared.model.GiftCategory;
import org.dominokit.domino.api.client.annotations.Path;
import org.dominokit.domino.api.client.annotations.RequestFactory;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.domino.api.shared.request.ArrayResponse;

@RequestFactory
public interface GiftCategoryRequests {
    @Path("gifts/categories")
    Response<ArrayResponse<GiftCategory>> list();
}
