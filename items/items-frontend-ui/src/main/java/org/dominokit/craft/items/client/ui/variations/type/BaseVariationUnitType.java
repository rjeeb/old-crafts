package org.dominokit.craft.items.client.ui.variations.type;

import elemental2.dom.EventListener;
import org.dominokit.craft.items.shared.model.Variation;
import org.dominokit.craft.items.shared.model.VariationValue;
import org.dominokit.domino.ui.chips.Chip;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.SelectOption;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.grid.flex.FlexWrap;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.keyboard.KeyboardEvents;
import org.dominokit.domino.ui.style.ColorScheme;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseVariationUnitType extends BaseVariationType {

    private static final String OTHER = "other";
    private static final String CENTIMETERS = "centimeters";
    private static final String INCHES = "inches";
    private FlexLayout valuesContainer;
    private TextBox valueBox;
    private Select<String> unitSelect;
    private List<Chip> values = new ArrayList<>();

    public BaseVariationUnitType(String title) {
        super(title);
        EventListener addValueEventListener = evt -> {
            String chipValue = valueBox.getStringValue() + " ";
            if (!OTHER.equals(unitSelect.getValue())) {
                chipValue += unitSelect.getValue();
            }
            Chip valueChip = Chip.create(chipValue)
                    .setRemovable(true)
                    .setColorScheme(ColorScheme.BLACK);

            valueChip.addRemoveHandler(() -> {
                values.remove(valueChip);
            });

            valuesContainer.appendChild(FlexItem.create()
                    .appendChild(valueChip)
            );
            values.add(valueChip);
            clearValueBox();
        };

        valueBox = TextBox.create()
                .setRightAddon(Icons.ALL.add().clickable()
                        .addClickListener(addValueEventListener)
                )
                .collapse();

        KeyboardEvents.listenOn(valueBox)
                .onEnter(addValueEventListener);

        EventListener selectTextEventListener = evt -> {
            valueBox.getInputElement().asElement().select();
        };
        valueBox.addClickListener(selectTextEventListener);
        valueBox.addEventListener("focus", selectTextEventListener);

        unitSelect = Select.<String>create("Unit")
                .appendChild(SelectOption.create(INCHES, "Inches"))
                .appendChild(SelectOption.create(CENTIMETERS, "Centimeters"))
                .appendChild(SelectOption.create(OTHER, "Other"))
                .addChangeHandler(value -> {
                    valuesContainer.clearElement();
                    clearValueBox();
                    valueBox.expand();
                });

        valuesContainer = FlexLayout.create()
                .styler(style -> style.setMarginBottom("20px"))
                .setWrap(FlexWrap.WRAP_TOP_TO_BOTTOM);
        rightSideContainer.appendChild(Row.create()
                .appendChild(Column.span12()
                        .appendChild(unitSelect)
                        .appendChild(valueBox)
                        .appendChild(valuesContainer)
                )
        );
    }

    private void clearValueBox() {
        valueBox.clear();
        valueBox.getInputElement().asElement().select();
    }

    @Override
    public Variation getVariation() {
        Variation variation = new Variation(title);
        for (Chip value : values) {
            variation.addVariationValue(new VariationValue(value.getValue()));
        }
        return variation;
    }
}
