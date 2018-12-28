package org.dominokit.craft.items.client.ui.variations.type;

import org.dominokit.craft.items.shared.model.Variation;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.SelectOption;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;

public class VariationSizeType extends BaseVariationType {

    public static final String TYPE = "Size";

    public VariationSizeType() {
        super(TYPE);

        rightSideContainer.appendChild(Row.create()
                .appendChild(Column.span12()
                        .appendChild(Select.create("Scale")
                                .appendChild(SelectOption.create("US", "US women's"))
                                .appendChild(SelectOption.create("EU", "EU women's"))
                                .appendChild(SelectOption.create("UK", "UK women's"))
                                .appendChild(SelectOption.create("JP", "JP women's"))
                        )
                )
        );
    }

    @Override
    public Variation getVariation() {
        return null;
    }
}
