package org.dominokit.craft.category.client.ui.component.itemform;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.*;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.h;
import static org.jboss.gwt.elemento.core.Elements.p;

public class ItemDetailsSection extends BaseDominoElement<HTMLDivElement, ItemDetailsSection> {

    private DominoElement<HTMLDivElement> element = DominoElement.div();

    public ItemDetailsSection() {
        init(this);

        element.appendChild(Card.create("Item details", "Tell the world all about your item and why they’ll love it.")
                .setBodyPaddingTop("40px")
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Title *"))
                                .appendChild(p().textContent("Include keywords that buyers would use to search for your item."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(TextBox.create("Title"))
                ))
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("About this listing *"))
                                .appendChild(p().textContent("Learn more about what types of items are allowed on Etsy."))
                        )
                )
                .appendChild(Row.create()
                        .appendChild(Column.span4()
                                .appendChild(Select.create("Who made it?"))
                        )
                        .appendChild(Column.span4()
                                .appendChild(Select.create("What is it?"))
                        )
                        .appendChild(Column.span4()
                                .appendChild(Select.create("When was it made?"))
                        )
                )
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Category *"))
                                .appendChild(p().textContent("Type a two- or three-word description of your item to get category suggestions that will help more shoppers find it."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(TextBox.create().setPlaceholder("men's coats, hoop earring, wall hanging, yarn"))
                ))
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Renewal options *"))
                                .appendChild(p().textContent("Each renewal lasts for four months or until the listing sells out. Get more details on auto-renewing here."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(RadioGroup.create("renewal")
                                .appendChild(Radio.create("AUTOMATIC", "Automatic")
                                        .setHelperText("This listing will renew as it expires for USD 0.20 USD each time (recommended).")
                                )
                                .appendChild(Radio.create("MANUAL", "Manual")
                                        .setHelperText("I'll renew expired listings myself."))
                                .horizontal()
                        )
                ))
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Type *"))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(RadioGroup.create("type")
                                .appendChild(Radio.create("PHYSICAL", "Physical")
                                        .setHelperText("A tangible item that you will ship to buyers.")
                                )
                                .appendChild(Radio.create("DIGITAL", "Digital")
                                        .setHelperText("A digital file that buyers will download.")
                                )
                                .horizontal()
                        )
                ))
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Description *"))
                                .appendChild(p().textContent("Start with a brief overview that describes your item's finest features."))
                                .appendChild(p().textContent("List details like dimensions and key features in easy-to-read bullet points."))
                                .appendChild(p().textContent("Tell buyers a bit about your process or the story behind this item."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(TextArea.create()
                                .setRows(6)
                                .setPlaceholder("Description")
                        )
                ))
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Section (Optional)"))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()

                ))
                .appendChild(Row.create().appendChild(Column.span12()))
                .appendChild(Row.create().appendChild(Column.span12()))
        );
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
