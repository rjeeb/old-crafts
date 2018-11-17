package org.dominokit.craft.home.client.requests.news;

import org.dominokit.craft.home.client.requests.TestResponse;
import org.dominokit.craft.home.shared.model.Feature;
import org.dominokit.craft.home.shared.model.Features;
import org.dominokit.domino.api.client.request.Response;

import java.util.Arrays;

public class TestFeatureRequests implements FeatureRequests {

    @Override
    public Response<Features> list() {
        Feature feature1 = new Feature();
        feature1.setTitle("Unique everything");
        feature1.setDescription("We have millions of one-of-a-kind items, so you can find whatever you need (or really, really want).");

        Feature feature2 = new Feature();
        feature2.setTitle("Independent sellers");
        feature2.setDescription("Buy directly from someone who put their heart and soul into making something special.");

        Feature feature3 = new Feature();
        feature3.setTitle("Secure shopping");
        feature3.setDescription("We use best-in-class technology to protect your transactions.");


        Features features = new Features();
        features.getFeatures().addAll(Arrays.asList(feature1, feature2, feature3));
        return new TestResponse<>(features);
    }
}
