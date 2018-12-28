package org.dominokit.craft.items.client.ui.variations;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.items.shared.model.Variation;
import org.dominokit.craft.items.shared.model.VariationValue;
import org.dominokit.domino.ui.forms.SwitchButton;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.lists.ListItem;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.h;

public class VariationListGroup extends BaseDominoElement<HTMLDivElement, VariationListGroup> {

    private DominoElement<HTMLDivElement> element = DominoElement.div();
    private ListGroup<String> listGroup;
    private Variation variation;

    public VariationListGroup(Variation variation) {
        this.variation = variation;

        listGroup = ListGroup.<String>create().setSelectable(false);

        listGroup
                .appendChild(ListItem.create("")
                        .appendChild(
                                FlexLayout.create()
                                        .styler(style1 -> style1.setMarginRight("20px"))
                                        .appendChild(FlexItem.create()
                                                .setFlexGrow(1)
                                                .appendChild(h(5).textContent("Options"))
                                        )
                                        .appendChild(FlexItem.create()
                                                .appendChild(h(5).textContent("Visible")
                                                )
                                        ))
                );

        addVariation(variation);
        element
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent(variation.getName()))
                                .appendChild(listGroup)
                        ));

    }

    private SwitchButton newVisibleSwitchButton() {
        return SwitchButton.create()
                .styler(style1 -> style1.setMarginBottom("0px"));
    }

    public VariationListGroup addVariation(Variation variation) {
        for (VariationValue variationValue : variation.getVariationValues()) {
            listGroup.appendChild(ListItem.create("")
                    .appendChild(
                            FlexLayout.create()
                                    .appendChild(FlexItem.create()
                                            .setFlexGrow(1)
                                            .appendChild(h(5).textContent(variationValue.getValue()))
                                    )
                                    .appendChild(FlexItem.create()
                                            .appendChild(newVisibleSwitchButton())
                                    ))
            );
        }

        return this;
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
