package org.dominokit.craft.category.client.ui.component.itemform;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

public class ItemForm extends BaseDominoElement<HTMLDivElement, ItemForm> {

    private DominoElement<HTMLDivElement> element = DominoElement.div();
    private ItemPhotosSection itemPhotosSection = new ItemPhotosSection();
    private ItemDetailsSection itemDetailsSection = new ItemDetailsSection();
    private InventoryAndPricingSection inventoryAndPricingSection = new InventoryAndPricingSection();

    public ItemForm() {
        element.appendChild(itemPhotosSection)
                .appendChild(itemDetailsSection)
                .appendChild(inventoryAndPricingSection);
        init(this);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
