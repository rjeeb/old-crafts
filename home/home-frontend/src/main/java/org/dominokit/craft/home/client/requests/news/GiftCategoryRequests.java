package org.dominokit.craft.home.client.requests.news;

import org.dominokit.craft.home.shared.model.GiftCategories;
import org.dominokit.domino.api.client.annotations.Path;
import org.dominokit.domino.api.client.annotations.RequestFactory;
import org.dominokit.domino.api.client.request.Response;

@RequestFactory
public interface GiftCategoryRequests {
    @Path("gifts/categories")
    Response<GiftCategories> list();
}
