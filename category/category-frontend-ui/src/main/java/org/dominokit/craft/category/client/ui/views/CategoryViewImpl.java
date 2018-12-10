package org.dominokit.craft.category.client.ui.views;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.dominokit.craft.category.client.presenters.CategoryPresenter;
import org.dominokit.craft.category.client.views.CategoryView;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.view.BaseElementView;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.h;

@UiView(presentable = CategoryPresenter.class)
public class CategoryViewImpl extends BaseElementView<HTMLDivElement> implements CategoryView {

    private DominoElement<HTMLHeadingElement> titleElement;

    @Override
    public void init(HTMLDivElement root) {
        titleElement = DominoElement.of(h(1)).addCss("category-title");

        DominoElement.of(root)
                .addCss("category-content")
                .appendChild(titleElement.asElement())
                // TODO: implementing the category list
                .appendChild(Row.create()
                        .appendChild(Column.span3())
                        .appendChild(Column.span9())
                );
    }

    @Override
    public HTMLDivElement createRoot() {
        return div().asElement();
    }

    @Override
    public void setTitle(String title) {
        titleElement.setTextContent(title);
    }
}