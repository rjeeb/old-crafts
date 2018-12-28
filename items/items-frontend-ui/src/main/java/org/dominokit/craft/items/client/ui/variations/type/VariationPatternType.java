package org.dominokit.craft.items.client.ui.variations.type;

import org.dominokit.craft.items.shared.model.Variation;

public class VariationPatternType extends BaseVariationType {

    public static final String TYPE = "Pattern";

    public VariationPatternType() {
        super(TYPE);
    }

    @Override
    public Variation getVariation() {
        return null;
    }
}
