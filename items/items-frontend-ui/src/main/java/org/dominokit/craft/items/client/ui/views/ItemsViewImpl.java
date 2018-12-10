package org.dominokit.craft.items.client.ui.views;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.view.BaseElementView;

import org.dominokit.domino.api.client.annotations.UiView;

import org.dominokit.craft.items.client.presenters.ItemsPresenter;
import org.dominokit.craft.items.client.views.ItemsView;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.h;

@UiView(presentable = ItemsPresenter.class)
public class ItemsViewImpl extends BaseElementView<HTMLDivElement> implements ItemsView{

    @Override
    public void init(HTMLDivElement root) {
        root.appendChild(h(1).textContent("Hello World!").asElement());
        DomGlobal.document.body.appendChild(root);
    }

    @Override
    public HTMLDivElement createRoot() {
        return div().asElement();
    }
}