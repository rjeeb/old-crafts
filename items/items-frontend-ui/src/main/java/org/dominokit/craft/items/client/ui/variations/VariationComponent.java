package org.dominokit.craft.items.client.ui.variations;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.items.shared.model.Variation;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import java.util.List;

import static org.dominokit.domino.ui.utils.DominoElement.div;

public class VariationComponent extends BaseDominoElement<HTMLDivElement, VariationComponent> {

    private VariationDialog variationDialog = VariationDialog.create();
    private DominoElement<HTMLDivElement> listVariationsContainer;
    private DominoElement<HTMLDivElement> element = div();
    private Button addVariations;
    private Button editVariations;

    public VariationComponent() {
        variationDialog.setSaveHandler(variations -> {
            listVariationsContainer.clearElement();
            for (Variation variation : variations) {
                VariationListGroup variationListGroup = new VariationListGroup(variation);
                listVariationsContainer.appendChild(variationListGroup);
            }
            addVariations.toggleDisplay(variations.isEmpty());
            editVariations.toggleDisplay(!variations.isEmpty());
        });

        addVariations = Button.createDefault("Add variations")
                .addClickListener(evt -> variationDialog.open());

        editVariations = Button.createDefault("Edit variations")
                .addClickListener(evt -> variationDialog

                        .open());

        listVariationsContainer = div();
        Column buttonsContainer = Column.span12();
        element.appendChild(Row.create()
                .appendChild(buttonsContainer
                        .appendChild(addVariations)
                        .appendChild(editVariations)
                )
                .appendChild(Column.span12()
                        .appendChild(listVariationsContainer)
                )
        );
        init(this);
        editVariations.collapse();
    }

    public static VariationComponent create() {
        return new VariationComponent();
    }

    public List<Variation> save() {
        return variationDialog.save();
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
