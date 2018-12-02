package org.dominokit.craft.layout.client.ui.views;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLLIElement;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.a;
import static org.jboss.gwt.elemento.core.Elements.li;

public class MenuItem extends BaseDominoElement<HTMLDivElement, MenuItem> {

    private FlexItem element = FlexItem.create();
    private DominoElement<HTMLLIElement> li = DominoElement.of(li().css("menu-item"));

    public MenuItem(String content) {
        element.appendChild(li
                .appendChild(a().css(Styles.vertical_center).textContent(content)));
    }


    public static MenuItem create(String content) {
        return new MenuItem(content);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
