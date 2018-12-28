package org.dominokit.craft.items.client.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.SelectOption;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.modals.ModalDialog;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

public class SectionComponent extends BaseDominoElement<HTMLDivElement, SectionComponent> {

    private DominoElement<HTMLDivElement> element = DominoElement.div();
    private Select<String> sectionSelect;

    public SectionComponent() {
        sectionSelect = Select.<String>create("Section")
                .appendChild(SelectOption.create("NONE", "None"))
                .setRightAddon(Icons.ALL.add()
                        .clickable()
                        .addClickListener(evt -> new SectionDialog().open()));

        element.appendChild(sectionSelect);
        init(this);
    }

    public static SectionComponent create() {
        return new SectionComponent();
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }

    public String getValue() {
        return sectionSelect.getValue();
    }

    private class SectionDialog extends ModalDialog {

        public SectionDialog() {
            setTitle("New Section");
            small();
            TextBox section = TextBox.create("Section");
            appendChild(section);

            Button saveButton = Button.create("Save")
                    .styler(style -> style.pullRight().setMargin("5px").setMinWidth("80px"))
                    .setBackground(Color.THEME)
                    .addClickListener(evt -> {
                        SelectOption<String> option = SelectOption.create(section.getValue(), section.getValue());
                        sectionSelect.appendChild(option);
                        sectionSelect.select(option);
                        close();
                    });

            Button cancelButton = Button.create("Cancel")
                    .linkify()
                    .styler(style -> style.setMargin("5px").setMinWidth("80px"))
                    .addClickListener(evt -> close());

            appendFooterChild(saveButton);
            appendFooterChild(cancelButton);

            getFooterElement().styler(style -> style.setProperty("border-top", "1px solid " + Color.GREY_LIGHTEN_2.getHex()));
        }
    }
}
