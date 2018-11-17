package org.dominokit.craft.home.client.ui.components;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.dominokit.craft.home.shared.model.RecentItem;
import org.dominokit.domino.ui.Typography.Paragraph;
import org.dominokit.domino.ui.popover.PopupPosition;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.thumbnails.Thumbnail;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.*;

public class RecentItemComponent extends BaseDominoElement<HTMLDivElement, RecentItemComponent> {

    private Thumbnail element = Thumbnail.create();
    private DominoElement<HTMLHeadingElement> titleParagraph = DominoElement.of(h(4));
    private Paragraph descriptionParagraph = Paragraph.create();
    private DominoElement<HTMLHeadingElement> amountParagraph = DominoElement.of(h(5));

    public RecentItemComponent(RecentItem recentItem) {
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

        element.styler(style -> style.add("recent-item"))
                .setContent(a().add(img(recentItem.getImageUrl())
                        .css(Styles.img_responsive)))
                .appendCaptionChild(titleParagraph)
                .appendCaptionChild(descriptionParagraph)
                .appendCaptionChild(amountParagraph);
    }

    public static RecentItemComponent create(RecentItem recentItem) {
        return new RecentItemComponent(recentItem);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
