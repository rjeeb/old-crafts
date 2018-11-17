package org.dominokit.craft.home.client.requests.news;

import org.dominokit.craft.home.client.requests.TestResponse;
import org.dominokit.craft.home.shared.model.Review;
import org.dominokit.craft.home.shared.model.Reviews;
import org.dominokit.domino.api.client.request.Response;

import java.util.Arrays;
import java.util.Date;

public class TestReviewRequests implements ReviewRequests {

    @Override
    public Response<Reviews> list() {
        Review review = new Review();
        review.setUserProfileImageUrl("https://picsum.photos/250/200/?image=1027");
        review.setUsername("Anna");
        review.setCreationDate(new Date());
        review.setRate(5);
        review.setDescription("Awesome addition to my bathroom! I already want another...");
        review.setItemImageUrl("https://picsum.photos/250/200/?image=998");
        review.setItemTitle("Hand Towel Holder - Bath Towel Holder - Paper Towel Holder");

        Review review1 = new Review();
        review1.setUserProfileImageUrl("https://picsum.photos/250/200/?image=1005");
        review1.setUsername("Josh");
        review1.setCreationDate(new Date());
        review1.setRate(5);
        review1.setDescription("Awesome addition to my bathroom! I already want another...");
        review1.setItemImageUrl("https://picsum.photos/250/200/?image=995");
        review1.setItemTitle("Hand Towel Holder - Bath Towel Holder - Paper Towel Holder");

        Review review2 = new Review();
        review2.setUserProfileImageUrl("https://picsum.photos/250/200/?image=996");
        review2.setUsername("Brays");
        review2.setCreationDate(new Date());
        review2.setRate(5);
        review2.setDescription("Awesome addition to my bathroom! I already want another...");
        review2.setItemImageUrl("https://picsum.photos/250/200/?image=947");
        review2.setItemTitle("Hand Towel Holder - Bath Towel Holder - Paper Towel Holder");

        Reviews reviews = new Reviews();
        reviews.getReviews().addAll(Arrays.asList(review, review1, review2));
        return new TestResponse<>(reviews);
    }
}
