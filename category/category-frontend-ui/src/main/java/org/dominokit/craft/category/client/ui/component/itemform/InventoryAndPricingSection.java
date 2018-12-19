package org.dominokit.craft.category.client.ui.component.itemform;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.category.client.ui.component.itemform.variations.VariationComponent;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.IntegerBox;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.*;

public class InventoryAndPricingSection extends BaseDominoElement<HTMLDivElement, InventoryAndPricingSection> {

    private DominoElement<HTMLDivElement> element = DominoElement.div();

    public InventoryAndPricingSection() {
        element.appendChild(Card.create("Inventory and pricing")
                .setBodyPaddingTop("40px")
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Price *"))
                                .appendChild(p().textContent("Factor in the costs of materials and labor, plus any related business expenses. Consider the total price buyers will pay too—including shipping."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(PriceComponent.create())
                ))
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Quantity *"))
                                .appendChild(p().textContent("For quantities greater than one, this listing will renew automatically until it sells out. You’ll be charged a USD 0.20 USD listing fee each time."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(IntegerBox.create("Quantity").value(1))
                ))
                .appendChild(hr())
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Variations"))
                                .appendChild(p().textContent("Add available options like color or size. Buyers will choose from these during checkout."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(VariationComponent.create())
                ))

        );
        init(this);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
