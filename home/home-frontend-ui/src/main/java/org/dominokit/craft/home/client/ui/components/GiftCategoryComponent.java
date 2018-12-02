package org.dominokit.craft.home.client.ui.components;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.home.shared.model.GiftCategory;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.thumbnails.Thumbnail;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.*;

public class GiftCategoryComponent extends BaseDominoElement<HTMLDivElement, GiftCategoryComponent> {

    private Thumbnail element = Thumbnail.create();

    public GiftCategoryComponent(GiftCategory giftCategory) {
        element.styler(style -> style.add("gift-category-item"))
                .setContent(a().add(img(giftCategory.getImageUrl())
                        .css(Styles.img_responsive, "gift-category-image")))
                .appendCaptionChild(DominoElement.of(h(5))
                        .setTextContent(giftCategory.getTitle())
                        .styler(style1 -> style1.add(Styles.ellipsis_text, Styles.font_bold)));
    }

    public static GiftCategoryComponent create(GiftCategory giftCategory) {
        return new GiftCategoryComponent(giftCategory);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
