package org.dominokit.craft.category.client.ui.component.itemform.variations;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.category.client.ui.component.itemform.variations.type.*;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.SelectOption;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.modals.ModalDialog;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.DominoElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.jboss.gwt.elemento.core.Elements.p;

public class VariationDialog extends ModalDialog {

    private static final Map<String, Supplier<VariationType>> VARIATION_TYPES = new HashMap<>();

    static {
        VARIATION_TYPES.put(VariationDeviceType.TYPE, VariationDeviceType::new);
        VARIATION_TYPES.put(VariationDiameterType.TYPE, VariationDiameterType::new);
        VARIATION_TYPES.put(VariationDimensionsType.TYPE, VariationDimensionsType::new);
        VARIATION_TYPES.put(VariationFabricType.TYPE, VariationFabricType::new);
        VARIATION_TYPES.put(VariationFinishType.TYPE, VariationFinishType::new);
        VARIATION_TYPES.put(VariationFlavorType.TYPE, VariationFlavorType::new);
        VARIATION_TYPES.put(VariationHeightType.TYPE, VariationHeightType::new);
        VARIATION_TYPES.put(VariationLengthType.TYPE, VariationLengthType::new);
        VARIATION_TYPES.put(VariationMaterialType.TYPE, VariationMaterialType::new);
        VARIATION_TYPES.put(VariationPatternType.TYPE, VariationPatternType::new);
        VARIATION_TYPES.put(VariationPrimaryColorType.TYPE, VariationPrimaryColorType::new);
        VARIATION_TYPES.put(VariationSecondaryColorType.TYPE, VariationSecondaryColorType::new);
        VARIATION_TYPES.put(VariationScentType.TYPE, VariationScentType::new);
        VARIATION_TYPES.put(VariationSizeType.TYPE, VariationSizeType::new);
        VARIATION_TYPES.put(VariationStyleType.TYPE, VariationStyleType::new);
        VARIATION_TYPES.put(VariationWeightType.TYPE, VariationWeightType::new);
        VARIATION_TYPES.put(VariationWidthType.TYPE, VariationWidthType::new);
    }

    private final DominoElement<HTMLDivElement> variationContainer;
    private final Select<String> addVariationSelect;
    private List<VariationType> selectedVariationTypes = new ArrayList<>();

    public VariationDialog() {
        setTitle("Add variations");

        appendChild(Row.create()
                .appendChild(Column.span12()
                        .appendChild(p()
                                .css(Styles.m_b_30)
                                .textContent("List all the options you offer. Buyers will see them in the order they are here."))
                )
        );

        variationContainer = DominoElement.div()
                .styler(style -> style.setMarginBottom("20px"));
        appendChild(Row.create()
                .appendChild(Column.span12()
                        .appendChild(variationContainer)
                )
        );

        addVariationSelect = Select.create("Add a variation");
        appendChild(Row.create()
                .appendChild(Column.span12()
                        .appendChild(addVariationSelect
                                .appendChild(SelectOption.create(VariationDeviceType.TYPE, "Device"))
                                .appendChild(SelectOption.create(VariationDiameterType.TYPE, "Diameter"))
                                .appendChild(SelectOption.create(VariationDimensionsType.TYPE, "Dimensions"))
                                .appendChild(SelectOption.create(VariationFabricType.TYPE, "Fabric"))
                                .appendChild(SelectOption.create(VariationFinishType.TYPE, "Finish"))
                                .appendChild(SelectOption.create(VariationFlavorType.TYPE, "Flavor"))
                                .appendChild(SelectOption.create(VariationHeightType.TYPE, "Height"))
                                .appendChild(SelectOption.create(VariationLengthType.TYPE, "Length"))
                                .appendChild(SelectOption.create(VariationMaterialType.TYPE, "Material"))
                                .appendChild(SelectOption.create(VariationPatternType.TYPE, "Pattern"))
                                .appendChild(SelectOption.create(VariationPrimaryColorType.TYPE, "Primary color"))
                                .appendChild(SelectOption.create(VariationSecondaryColorType.TYPE, "Secondary color"))
                                .appendChild(SelectOption.create(VariationScentType.TYPE, "Scent"))
                                .appendChild(SelectOption.create(VariationSizeType.TYPE, "Size"))
                                .appendChild(SelectOption.create(VariationStyleType.TYPE, "Style"))
                                .appendChild(SelectOption.create(VariationWeightType.TYPE, "Weight"))
                                .appendChild(SelectOption.create(VariationWidthType.TYPE, "Width"))
                                .addChangeHandler(value -> {
                                    VariationType variationType = VARIATION_TYPES.get(value).get();
                                    selectedVariationTypes.add(variationType);
                                    variationContainer.appendChild(variationType.setRemoveHandler(() -> {
                                        selectedVariationTypes.remove(variationType);
                                        addVariationSelect.expand();
                                    }));
                                    addVariationSelect.clear();
                                    if (selectedVariationTypes.size() == 2) {
                                        addVariationSelect.collapse();
                                    }
                                })
                                .setRightAddon(Icons.ALL.add().clickable())
                        )
                )
        );

        appendFooterChild(Button.create("Save and continue")
                .styler(style -> style.pullRight()
                        .setMargin("5px"))
                .addClickListener(evt -> close())
                .setBackground(Color.THEME));
        appendFooterChild(Button.create("Cancel")
                .addClickListener(evt -> close())
                .styler(style -> style.setMargin("5px"))
                .linkify());

        getFooterElement().styler(style -> style.setProperty("border-top", "1px solid " + Color.GREY_LIGHTEN_2.getHex()));
    }

    public static VariationDialog create() {
        return new VariationDialog();
    }
}
