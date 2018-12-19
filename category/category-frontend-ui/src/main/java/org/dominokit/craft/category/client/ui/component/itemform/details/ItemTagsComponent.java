package org.dominokit.craft.category.client.ui.component.itemform.details;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLParagraphElement;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.tag.TagsInput;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.p;

public class ItemTagsComponent extends BaseDominoElement<HTMLDivElement, ItemTagsComponent> {

    private TagsInput<String> tags;
    private DominoElement<HTMLParagraphElement> tagsLeftParagraph;
    private FlexLayout flexLayout = FlexLayout.create();

    public ItemTagsComponent(String title, String placeholder) {
        tags = TagsInput.create(title)
                .setPlaceholder(placeholder)
                .setMaxValue(13);
        tagsLeftParagraph = DominoElement.of(p());
        flexLayout
                .styler(style -> style.add("items-tag"))
                .appendChild(FlexItem.create()
                        .setFlexGrow(1)
                        .appendChild(tags
                                .styler(style -> style.add(Styles.vertical_center))
                                .setTagsColor(ColorScheme.BLACK))
                )
                .appendChild(FlexItem.create()
                        .appendChild(tagsLeftParagraph
                                .setTextContent("13 left")
                                .styler(style -> style.add(Styles.vertical_center)
                                        .setMarginLeft("20px"))
                        )
                );

        tags.addChangeHandler(value -> tagsLeftParagraph.setTextContent((13 - value.size()) + " left"));
        init(this);
    }

    public static ItemTagsComponent create(String title, String placeholder) {
        return new ItemTagsComponent(title, placeholder);
    }

    @Override
    public HTMLDivElement asElement() {
        return flexLayout.asElement();
    }
}
