package org.dominokit.craft.home.client.requests.news;

import org.dominokit.craft.home.client.requests.TestResponse;
import org.dominokit.craft.home.shared.model.GiftCategory;
import org.dominokit.domino.api.client.request.Response;
import org.dominokit.domino.api.shared.request.ArrayResponse;

public class TestGiftCategoryRequests implements GiftCategoryRequests {

    @Override
    public Response<ArrayResponse<GiftCategory>> list() {
        GiftCategory category1 = new GiftCategory();
        category1.setTitle("Anniversary gifts");
        category1.setImageUrl("https://picsum.photos/250/200/?image=815");

        GiftCategory category2 = new GiftCategory();
        category2.setTitle("Gifts for him");
        category2.setImageUrl("https://picsum.photos/250/200/?image=856");

        GiftCategory category3 = new GiftCategory();
        category3.setTitle("Gifts for her");
        category3.setImageUrl("https://picsum.photos/250/200/?image=646");

        GiftCategory category4 = new GiftCategory();
        category4.setTitle("Personalized gift ideas");
        category4.setImageUrl("https://picsum.photos/250/200/?image=686");

        GiftCategory category5 = new GiftCategory();
        category5.setTitle("Wedding gifts");
        category5.setImageUrl("https://picsum.photos/250/200/?image=360");

        GiftCategory category6 = new GiftCategory();
        category6.setTitle("Housewarming gifts");
        category6.setImageUrl("https://picsum.photos/250/200/?image=534");

        return new TestResponse<>(new ArrayResponse<>(new GiftCategory[]{category1, category2, category3, category4, category5, category6}));
    }
}
