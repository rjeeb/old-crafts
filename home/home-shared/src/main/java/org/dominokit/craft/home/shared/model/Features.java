package org.dominokit.craft.home.shared.model;

import org.dominokit.domino.api.shared.request.ResponseBean;

import java.util.ArrayList;
import java.util.List;

public class Features implements ResponseBean {

    private List<Feature> features = new ArrayList<>();

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
