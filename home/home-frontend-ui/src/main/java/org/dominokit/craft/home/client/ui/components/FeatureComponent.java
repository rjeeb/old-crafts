package org.dominokit.craft.home.client.ui.components;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLParagraphElement;
import org.dominokit.craft.home.shared.model.Feature;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.*;

public class FeatureComponent extends BaseDominoElement<HTMLDivElement, FeatureComponent> {

    private DominoElement<HTMLDivElement> element = DominoElement.of(div().css("feature-component"));
    private DominoElement<HTMLDivElement> headerElement = DominoElement.of(div().css("feature-header"));
    private DominoElement<HTMLParagraphElement> descriptionElement = DominoElement.of(p());

    public FeatureComponent(Feature feature) {
        headerElement
                .appendChild(Icons.ALL.done()
                        .setColor(Color.GREEN)
                        .styler(style -> style.add("feature-icon", Styles.vertical_center)))
                .appendChild(span().textContent(feature.getTitle()));

        descriptionElement.setTextContent(feature.getDescription());

        element
                .appendChild(headerElement)
                .appendChild(descriptionElement);
    }

    public static FeatureComponent create(Feature feature) {
        return new FeatureComponent(feature);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
