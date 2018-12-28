package org.dominokit.craft.items.client.ui.variations.type;

import org.dominokit.craft.items.shared.model.Variation;
import org.dominokit.craft.items.shared.model.VariationValue;
import org.dominokit.domino.ui.chips.Chip;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.SelectOption;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.grid.flex.FlexWrap;
import org.dominokit.domino.ui.style.ColorScheme;

import java.util.ArrayList;
import java.util.List;

public abstract class VariationColorType extends BaseVariationType {

    private FlexLayout valuesContainer;
    private Select<String> colorsSelect;
    private List<String> selectedColors = new ArrayList<>();

    public VariationColorType(String title) {
        super(title);

        colorsSelect = Select.<String>create("Color")
                .appendChild(SelectOption.create("White", "White"))
                .appendChild(SelectOption.create("Yellow", "Yellow"))
                .appendChild(SelectOption.create("Fuchsia", "Fuchsia"))
                .appendChild(SelectOption.create("Red", "Red"))
                .appendChild(SelectOption.create("Silver", "Silver"))
                .appendChild(SelectOption.create("Gray", "Gray"))
                .appendChild(SelectOption.create("Olive", "Olive"))
                .appendChild(SelectOption.create("Purple", "Purple"))
                .appendChild(SelectOption.create("Maroon", "Maroon"))
                .appendChild(SelectOption.create("Aqua", "Aqua"))
                .appendChild(SelectOption.create("Lime", "Lime"))
                .appendChild(SelectOption.create("Teal", "Teal"))
                .appendChild(SelectOption.create("Green", "Green"))
                .appendChild(SelectOption.create("Blue", "Blue"))
                .appendChild(SelectOption.create("Navy", "Navy"))
                .appendChild(SelectOption.create("Black", "Black"))
                .addChangeHandler(value -> {
                    valuesContainer.appendChild(FlexItem.create()
                            .appendChild(Chip.create(value)
                                    .setColorScheme(ColorScheme.BLACK)
                                    .setRemovable(true)
                                    .addRemoveHandler(() -> {
                                        colorsSelect.appendChild(SelectOption.create(value, value));
                                        selectedColors.remove(value);
                                    })
                            )
                    );
                    selectedColors.add(value);
                    SelectOption<String> selectedOption = colorsSelect.getSelectedOption();
                    colorsSelect.removeOption(selectedOption);
                    colorsSelect.clear();
                });

        valuesContainer = FlexLayout.create().setWrap(FlexWrap.WRAP_TOP_TO_BOTTOM);
        rightSideContainer.appendChild(Row.create()
                .appendChild(Column.span12()
                        .appendChild(colorsSelect)
                        .appendChild(valuesContainer)
                )
        );
    }

    @Override
    public Variation getVariation() {
        Variation variation = new Variation(title);
        for (String selectedColor : selectedColors) {
            variation.addVariationValue(new VariationValue(selectedColor));
        }
        return variation;
    }
}
