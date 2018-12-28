package org.dominokit.craft.items.client.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.items.client.ui.details.ItemTagsComponent;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.*;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.h;
import static org.jboss.gwt.elemento.core.Elements.p;

public class ItemDetailsSection extends BaseDominoElement<HTMLDivElement, ItemDetailsSection> {

    private final TextBox title;
    private final Select<String> whoMadeItSelect;
    private final Select<String> whatIsItSelect;
    private final Select<String> whenWasItMadeSelect;
    private final TextBox category;
    private final RadioGroup renewal;
    private final RadioGroup type;
    private final TextArea description;
    private final SectionComponent sectionComponent;
    private final ItemTagsComponent itemTagsComponent;
    private final ItemTagsComponent materialsComponent;
    private DominoElement<HTMLDivElement> element = DominoElement.div();

    public ItemDetailsSection() {
        init(this);

        title = TextBox.create("Title");
        whoMadeItSelect = Select.create("Who made it?");
        whatIsItSelect = Select.create("What is it?");
        whenWasItMadeSelect = Select.create("When was it made?");
        category = TextBox.create();
        renewal = RadioGroup.create("renewal");
        type = RadioGroup.create("type");
        description = TextArea.create();
        sectionComponent = SectionComponent.create();
        itemTagsComponent = ItemTagsComponent.create("Tags", "Shope, color, style, function, etc.");
        materialsComponent = ItemTagsComponent.create("Materials", "Ingredients, components, etc.");
        element.appendChild(Card.create("Item details", "Tell the world all about your item and why they’ll love it.")
                .setBodyPaddingTop("40px")
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Title *"))
                                .appendChild(p().textContent("Include keywords that buyers would use to search for your item."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(title)
                ))
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("About this listing *"))
                                .appendChild(p().textContent("Learn more about what types of items are allowed on Etsy."))
                        )
                )
                .appendChild(Row.create()
                        .appendChild(Column.span4()
                                .appendChild(whoMadeItSelect
                                        .appendChild(SelectOption.create("ME", "I did"))
                                        .appendChild(SelectOption.create("MEMBER", "A member of my shop"))
                                        .appendChild(SelectOption.create("ANOTHER", "Another company or person"))
                                )
                        )
                        .appendChild(Column.span4()
                                .appendChild(whatIsItSelect
                                        .appendChild(SelectOption.create("FINISHED", "A finished product"))
                                        .appendChild(SelectOption.create("SUPPLY_TOOL", "A supply or tool to make things"))
                                )
                        )
                        .appendChild(Column.span4()
                                .appendChild(whenWasItMadeSelect
                                        .appendChild(SelectOption.create("RECENTLY", "Recently"))
                                        .appendChild(SelectOption.create("VINTAGE", "Vintage"))
                                )
                        )
                )
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Category *"))
                                .appendChild(p().textContent("Type a two- or three-word description of your item to get category suggestions that will help more shoppers find it."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(category.setPlaceholder("men's coats, hoop earring, wall hanging, yarn"))
                ))
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Renewal options *"))
                                .appendChild(p().textContent("Each renewal lasts for four months or until the listing sells out. Get more details on auto-renewing here."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(renewal
                                .appendChild(Radio.create("AUTOMATIC", "Automatic")
                                        .withGap()
                                        .setHelperText("This listing will renew as it expires for USD 0.20 USD each time (recommended).")
                                        .check()
                                )
                                .appendChild(Radio.create("MANUAL", "Manual")
                                        .withGap()
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
                        .appendChild(type
                                .appendChild(Radio.create("PHYSICAL", "Physical")
                                        .withGap()
                                        .setHelperText("A tangible item that you will ship to buyers.")
                                        .check()
                                )
                                .appendChild(Radio.create("DIGITAL", "Digital")
                                        .withGap()
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
                        .appendChild(description
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
                        .appendChild(sectionComponent)
                ))
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Tags (Optional)"))
                                .appendChild(p().textContent("What words might someone use to search for your listings? Use all 13 tags to get found. Get ideas for tags."))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(itemTagsComponent)
                ))
                .appendChild(Row.create()
                        .appendChild(Column.span12()
                                .appendChild(h(4).textContent("Materials (Optional)"))
                        )
                )
                .appendChild(Row.create().appendChild(Column.span12()
                        .appendChild(materialsComponent)
                ))
                .appendChild(Row.create().appendChild(Column.span12()))
                .appendChild(Row.create().appendChild(Column.span12()))
        );
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }

    public void save(ItemResource itemResource) {
        itemResource.setTitle(title.getValue());
        itemResource.setWhoMadeIt(whoMadeItSelect.getValue());
        itemResource.setWhatIsIt(whatIsItSelect.getValue());
        itemResource.setWhenWasItMade(whenWasItMadeSelect.getValue());
        itemResource.setCategory(category.getValue());
        itemResource.setRenewalOption(renewal.getValue());
        itemResource.setType(type.getValue());
        itemResource.setDescription(description.getValue());
        itemResource.setSection(sectionComponent.getValue());
        itemResource.setTags(itemTagsComponent.getValue());
        itemResource.setMaterials(materialsComponent.getValue());
    }
}
