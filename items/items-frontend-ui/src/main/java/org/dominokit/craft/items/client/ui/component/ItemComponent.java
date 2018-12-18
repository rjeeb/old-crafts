package org.dominokit.craft.items.client.ui.component;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.dominokit.craft.items.shared.model.ItemResource;
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

    public ItemComponent(ItemResource recentItemResource) {
        titleParagraph
                .styler(style -> style.add(Styles.ellipsis_text))
                .setTextContent(recentItemResource.getTitle())
                .setTooltip(recentItemResource.getTitle(), PopupPosition.RIGHT);

        descriptionParagraph
                .styler(style -> style.add(Styles.ellipsis_text))
                .setText(recentItemResource.getDescription());

        amountParagraph
                .styler(style -> style.add(Styles.ellipsis_text))
                .setTextContent(String.valueOf(recentItemResource.getAmount()));

        element.styler(style -> style.add("item"))
                .setContent(a().add(img(recentItemResource.getImageUrl())
                        .css(Styles.img_responsive, "item-image")))
                .appendCaptionChild(titleParagraph)
                .appendCaptionChild(descriptionParagraph)
                .appendCaptionChild(amountParagraph);
    }

    public static ItemComponent create(ItemResource recentItemResource) {
        return new ItemComponent(recentItemResource);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
