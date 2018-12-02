package org.dominokit.craft.layout.client.ui.views;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexJustifyContent;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.div;

public class Menu extends BaseDominoElement<HTMLDivElement, Menu> {

    private DominoElement<HTMLDivElement> element = DominoElement.of(div().css("menu"));
    private FlexLayout flexLayout = FlexLayout.create();

    public Menu() {
        flexLayout.setJustifyContent(FlexJustifyContent.SPACE_BETWEEN)
                .styler(style1 -> style1.setHeight("100%"));
        element.appendChild(flexLayout);
    }

    public Menu addItem(MenuItem menuItem) {
        flexLayout.appendChild(menuItem);
        return this;
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
