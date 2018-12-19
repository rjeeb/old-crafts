package org.dominokit.craft.category.client.ui.component.itemform.variations;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

public class VariationComponent extends BaseDominoElement<HTMLDivElement, VariationComponent> {

    private DominoElement<HTMLDivElement> element = DominoElement.div();

    public VariationComponent() {
        Button addVariations = Button.createDefault("Add variations")
                .addClickListener(evt -> VariationDialog.create().open());

        element.appendChild(addVariations);
        init(this);
    }

    public static VariationComponent create() {
        return new VariationComponent();
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
