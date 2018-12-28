package org.dominokit.craft.items.client.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.items.client.ui.variations.VariationComponent;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.IntegerBox;
import org.dominokit.domino.ui.forms.SwitchButton;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.*;

public class InventoryAndPricingSection extends BaseDominoElement<HTMLDivElement, InventoryAndPricingSection> {

    private final PersonalizationComponent personalizationComponent;
    private DominoElement<HTMLDivElement> element = DominoElement.div();

    public InventoryAndPricingSection() {
        personalizationComponent = PersonalizationComponent.create();
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
                .appendChild(hr())
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(
                                        FlexLayout.create()
                                                .appendChild(FlexItem.create()
                                                        .setFlexGrow(1)
                                                        .appendChild(h(4).textContent("Personalization"))
                                                        .appendChild(p().textContent("Collect personalized information for this listing. Buyers must enter their personalization before proceeding to checkout."))
                                                )
                                                .appendChild(FlexItem.create()
                                                        .appendChild(SwitchButton.create()
                                                                .addChangeHandler(personalizationComponent::toggleDisplay))
                                                )
                                )
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(personalizationComponent.collapse())

                ))

        );
        init(this);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }

    public void save(ItemResource itemResource) {

    }
}
