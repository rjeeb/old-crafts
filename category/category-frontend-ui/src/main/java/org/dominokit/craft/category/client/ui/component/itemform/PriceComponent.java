package org.dominokit.craft.category.client.ui.component.itemform;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.forms.DoubleBox;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.BaseDominoElement;

import static org.jboss.gwt.elemento.core.Elements.p;

public class PriceComponent extends BaseDominoElement<HTMLDivElement, PriceComponent> {

    private FlexLayout element = FlexLayout.create();

    public PriceComponent() {
        element
                .appendChild(FlexItem.create()
                        .appendChild(p().textContent("USD")
                                .css(Styles.vertical_center)
                                .css(Styles.m_r_10))
                )
                .appendChild(FlexItem.create()
                        .setFlexGrow(1)
                        .appendChild(DoubleBox.create("Price")
                                .styler(style -> style.add(Styles.vertical_center)))
                );
        init(this);
    }

    public static PriceComponent create() {
        return new PriceComponent();
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
