package org.dominokit.craft.items.client.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.forms.IntegerBox;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.h;
import static org.jboss.gwt.elemento.core.Elements.p;

public class PersonalizationComponent extends BaseDominoElement<HTMLDivElement, PersonalizationComponent> {

    private DominoElement<HTMLDivElement> element = DominoElement.div();

    public PersonalizationComponent() {
        element
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(5).textContent("Guidelines for buyers"))
                                .appendChild(p().textContent("Use this box to enter the instructions that buyers will see above the text box."))
                                .appendChild(TextArea.create().setPlaceholder("Example: Enter the name you want on the necklace. Max 12 characters, no spaces, no special characters. Thank you!"))
                        )
                )
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(5).textContent("Character limit"))
                                .appendChild(p().textContent("Limit the number of characters a buyer can enter."))
                                .appendChild(IntegerBox.create().setPlaceholder("Character limit"))
                        )
                );

        init(this);
    }

    public static PersonalizationComponent create() {
        return new PersonalizationComponent();
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
