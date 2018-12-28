package org.dominokit.craft.items.client.ui.views;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.items.client.presenters.ItemsPresenter;
import org.dominokit.craft.items.client.ui.ItemForm;
import org.dominokit.craft.items.client.views.ItemsView;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.view.BaseElementView;

import static org.jboss.gwt.elemento.core.Elements.div;

@UiView(presentable = ItemsPresenter.class)
public class ItemsViewImpl extends BaseElementView<HTMLDivElement> implements ItemsView {

    private ItemForm itemForm;
    private ItemsViewUiHandlers uiHandlers;

    @Override
    public void init(HTMLDivElement root) {
        itemForm = new ItemForm();
        DominoElement.of(root)
                .appendChild(DominoElement.div()
                        .styler(style -> style.setMarginTop("40px"))
                        .appendChild(DominoElement.div()
                                .appendChild(Button.createDefault("CREATE")
                                        .addClickListener(evt -> {
                                            uiHandlers.onCreate();
                                        })
                                )
                        )
                        .appendChild(itemForm)
                );
    }

    @Override
    public HTMLDivElement createRoot() {
        return div().asElement();
    }

    @Override
    public ItemResource save() {
        return itemForm.save();
    }

    @Override
    public void setUiHandlers(ItemsViewUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }
}