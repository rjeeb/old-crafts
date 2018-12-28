package org.dominokit.craft.items.client.ui.component;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.ui.Typography.Paragraph;
import org.dominokit.domino.ui.thumbnails.Thumbnail;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.h;

public class ItemComponent extends BaseDominoElement<HTMLDivElement, ItemComponent> {

    private Thumbnail element = Thumbnail.create();
    private DominoElement<HTMLHeadingElement> titleParagraph = DominoElement.of(h(4));
    private Paragraph descriptionParagraph = Paragraph.create();
    private DominoElement<HTMLHeadingElement> amountParagraph = DominoElement.of(h(5));

    public ItemComponent(ItemResource recentItemResource) {
    }

    public static ItemComponent create(ItemResource recentItemResource) {
        return new ItemComponent(recentItemResource);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
