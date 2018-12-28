package org.dominokit.craft.items.shared.model;

import java.util.ArrayList;
import java.util.List;

public class Variation {
    private String name;
    private List<VariationValue> variationValues = new ArrayList<>();

    public Variation() {
    }

    public Variation(String name) {
        this.name = name;
    }

    public void addVariationValue(VariationValue variationValue) {
        variationValues.add(variationValue);
    }

    public List<VariationValue> getVariationValues() {
        return new ArrayList<>(variationValues);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVariationValues(List<VariationValue> variationValues) {
        this.variationValues = variationValues;
    }
}
