package org.dominokit.craft.category.client.ui.component.itemform.variations.type;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.forms.CheckBox;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.h;

public abstract class BaseVariationType implements VariationType {

    protected DominoElement<HTMLDivElement> rightSideContainer;
    protected DominoElement<HTMLDivElement> element = DominoElement.div();
    private RemoveHandler removeHandler;

    public BaseVariationType(String title) {
        rightSideContainer = DominoElement.div();
        element
                .appendChild(Row.create()
                        .appendChild(Column.span6()
                                .appendChild(Row.create()
                                        .appendChild(Column.span12()
                                                .appendChild(FlexLayout.create()
                                                        .appendChild(FlexItem.create()
                                                                .styler(style -> style.setMarginRight("10px"))
                                                                .appendChild(h(5).textContent(title))
                                                        )
                                                        .appendChild(FlexItem.create()
                                                                .appendChild(Icons.ALL.delete().clickable()
                                                                        .setTooltip("Remove")
                                                                        .addClickListener(evt -> {
                                                                            element.remove();
                                                                            removeHandler.onRemove();
                                                                        })
                                                                )
                                                        )
                                                )
                                        )
                                )
                                .appendChild(Row.create()
                                        .appendChild(Column.span12()
                                                .appendChild(CheckBox.create("Prices vary for each " + title.toLowerCase())
                                                        .styler(style -> style.setMarginBottom("0px"))
                                                )
                                                .appendChild(CheckBox.create("Quantities vary for each " + title.toLowerCase()))
                                        )
                                )
                        )
                        .appendChild(Column.span6().appendChild(rightSideContainer))
                );
    }

    @Override
    public VariationType setRemoveHandler(RemoveHandler removeHandler) {
        this.removeHandler = removeHandler;
        return this;
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
