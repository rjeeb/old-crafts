package org.dominokit.craft.home.client.requests.news;

import org.dominokit.craft.home.client.requests.TestResponse;
import org.dominokit.craft.home.shared.model.Preview;
import org.dominokit.domino.api.client.request.Response;

public class TestPreviewRequests implements PreviewRequests {

    @Override
    public Response<Preview> get() {
        Preview preview = new Preview();
        preview.setTitle("Just for him: custom, crafted surprises.");
        preview.setLinkTitle("Shop gifts for him");
        preview.setImageUrl("https://picsum.photos/1500/400/?image=1059");
        preview.setLink("http://www.google.com");
        return new TestResponse<>(preview);
    }
}
