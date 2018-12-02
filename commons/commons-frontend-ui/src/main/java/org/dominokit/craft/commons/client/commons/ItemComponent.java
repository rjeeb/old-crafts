package org.dominokit.craft.commons.client.commons;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.dominokit.craft.shared.model.Item;
import org.dominokit.domino.ui.Typography.Paragraph;
import org.dominokit.domino.ui.popover.PopupPosition;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.thumbnails.Thumbnail;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.*;

public class ItemComponent extends BaseDominoElement<HTMLDivElement, ItemComponent> {

    private Thumbnail element = Thumbnail.create();
    private DominoElement<HTMLHeadingElement> titleParagraph = DominoElement.of(h(4));
    private Paragraph descriptionParagraph = Paragraph.create();
    private DominoElement<HTMLHeadingElement> amountParagraph = DominoElement.of(h(5));

    public ItemComponent(Item recentItem) {
        titleParagraph
                .styler(style -> style.add(Styles.ellipsis_text))
                .setTextContent(recentItem.getTitle())
                .setTooltip(recentItem.getTitle(), PopupPosition.RIGHT);

        descriptionParagraph
                .styler(style -> style.add(Styles.ellipsis_text))
                .setText(recentItem.getDescription());

        amountParagraph
                .styler(style -> style.add(Styles.ellipsis_text))
                .setTextContent(recentItem.getAmount());

        element.styler(style -> style.add("item"))
                .setContent(a().add(img(recentItem.getImageUrl())
                        .css(Styles.img_responsive, "item-image")))
                .appendCaptionChild(titleParagraph)
                .appendCaptionChild(descriptionParagraph)
                .appendCaptionChild(amountParagraph);
    }

    public static ItemComponent create(Item recentItem) {
        return new ItemComponent(recentItem);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
