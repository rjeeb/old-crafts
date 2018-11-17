package org.dominokit.craft.home.shared.model;

import org.dominokit.domino.api.shared.request.ResponseBean;

import java.util.ArrayList;
import java.util.List;

public class Reviews implements ResponseBean {

    private List<Review> reviews = new ArrayList<>();

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
