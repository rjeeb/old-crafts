package org.dominokit.craft.items.client.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.items.client.views.ItemsView;
import org.dominokit.craft.items.shared.model.ItemResource;
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

    public ItemResource save() {
        ItemResource itemResource = new ItemResource();
        itemPhotosSection.save(itemResource);
        itemDetailsSection.save(itemResource);
        inventoryAndPricingSection.save(itemResource);
        return itemResource;
    }

    public void setUiHandlers(ItemsView.ItemsViewUiHandlers uiHandlers) {
        itemPhotosSection.setUiHandlers(uiHandlers);
    }
}
