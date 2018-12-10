package org.dominokit.craft.category.client.requests;

import org.dominokit.domino.api.client.annotations.Path;
import org.dominokit.domino.api.client.annotations.RequestFactory;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.craft.category.shared.response.CategoryResponse;
import org.dominokit.craft.category.shared.request.CategoryRequest;

@RequestFactory
public interface CategoryRequests {
    @Path("CategoryRequest")
    Response<CategoryResponse> request(CategoryRequest request);
}
