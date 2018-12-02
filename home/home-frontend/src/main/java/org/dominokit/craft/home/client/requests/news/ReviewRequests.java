package org.dominokit.craft.home.client.requests.news;

import org.dominokit.craft.home.shared.model.Review;
import org.dominokit.domino.api.client.annotations.Path;
import org.dominokit.domino.api.client.annotations.RequestFactory;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.domino.api.shared.request.ArrayResponse;

@RequestFactory
public interface ReviewRequests {
    @Path("reviews")
    Response<ArrayResponse<Review>> list();
}
