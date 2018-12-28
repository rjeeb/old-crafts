package org.dominokit.craft.items.client.ui.variations.type;

import org.dominokit.craft.items.shared.model.Variation;
import org.dominokit.craft.items.shared.model.VariationValue;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.tag.TagsInput;
import org.dominokit.domino.ui.utils.DominoElement;

public class VariationDeviceType extends BaseVariationType {

    public static final String TYPE = "Device";
    private final TagsInput<String> deviceOptions;

    public VariationDeviceType() {
        super(TYPE);

        deviceOptions = TagsInput.create("Add device options");
        rightSideContainer.appendChild(DominoElement.div()
                .styler(style -> style.setPaddingTop("20px"))
                .appendChild(deviceOptions
                        .setTagsColor(ColorScheme.BLACK)
                )
        );
    }

    @Override
    public Variation getVariation() {
        Variation variation = new Variation(TYPE);
        for (String value : deviceOptions.getValue()) {
            variation.addVariationValue(new VariationValue(value));
        }
        return variation;
    }
}
