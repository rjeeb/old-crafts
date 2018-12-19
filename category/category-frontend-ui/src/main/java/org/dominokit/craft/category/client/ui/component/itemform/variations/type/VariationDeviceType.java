package org.dominokit.craft.category.client.ui.component.itemform.variations.type;

import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.tag.TagsInput;
import org.dominokit.domino.ui.utils.DominoElement;

public class VariationDeviceType extends BaseVariationType {

    public static final String TYPE = "Device";

    public VariationDeviceType() {
        super(TYPE);

        rightSideContainer.appendChild(DominoElement.div()
                .styler(style -> style.setPaddingTop("20px"))
                .appendChild(TagsInput.create("Add device options")
                        .setTagsColor(ColorScheme.BLACK)
                )
        );
    }
}
