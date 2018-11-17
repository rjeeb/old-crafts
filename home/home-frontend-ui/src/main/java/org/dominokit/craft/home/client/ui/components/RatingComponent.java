package org.dominokit.craft.home.client.ui.components;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import java.util.HashMap;
import java.util.Map;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.span;

public class RatingComponent extends BaseDominoElement<HTMLDivElement, RatingComponent> {

    private DominoElement<HTMLDivElement> element = DominoElement.of(div());
    private DominoElement<HTMLElement> star_1 = DominoElement.of(span());
    private DominoElement<HTMLElement> star_2 = DominoElement.of(span());
    private DominoElement<HTMLElement> star_3 = DominoElement.of(span());
    private DominoElement<HTMLElement> star_4 = DominoElement.of(span());
    private DominoElement<HTMLElement> star_5 = DominoElement.of(span());
    private Map<Integer, DominoElement<HTMLElement>> stars = new HashMap<>();

    public RatingComponent() {
        stars.put(1, DominoElement.of(span()));
        stars.put(2, DominoElement.of(span()));
        stars.put(3, DominoElement.of(span()));
        stars.put(4, DominoElement.of(span()));
        stars.put(5, DominoElement.of(span()));

        stars.values()
                .forEach(starElement -> element.appendChild(starElement));
        init(this);
    }

    public static RatingComponent create() {
        return new RatingComponent();
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }

    public void setValue(int rate) {
        if (rate > 0 && rate <= 5) {
            for (int i = 1; i <= 5; i++) {
                if (i <= rate) {
                    fillStarAt(i);
                } else {
                    outlineStarAt(i);
                }
            }
        }
    }

    private void fillStarAt(int index) {
        stars.get(index)
                .clearElement()
                .appendChild(Icons.ALL.star());
    }

    private void outlineStarAt(int index) {
        stars.get(index)
                .clearElement()
                .appendChild(Icons.ALL.star_border());
    }
}
