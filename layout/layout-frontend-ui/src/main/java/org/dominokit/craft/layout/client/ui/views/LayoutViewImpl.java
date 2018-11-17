package org.dominokit.craft.layout.client.ui.views;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLUListElement;
import jsinterop.base.Js;
import org.dominokit.craft.layout.client.presenters.LayoutPresenter;
import org.dominokit.craft.layout.client.views.LayoutView;
import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.api.shared.extension.Content;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.Row_12;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.DelayedTextInput;
import org.dominokit.domino.ui.utils.DominoElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.jboss.gwt.elemento.core.Elements.*;

@UiView(presentable = LayoutPresenter.class)
public class LayoutViewImpl implements LayoutView {

    private final Row_12 footerRow;
    private Layout layout = Layout.create("Crafts");
    private TextBox searchTextBox;
    private Consumer<String> searchValueConsumer;
    private Map<String, HTMLUListElement> footerCategories = new HashMap<>();

    public LayoutViewImpl() {
        DominoElement.of(DomGlobal.document.body)
                .styler(style -> style.add(Color.WHITE.getBackground()));
        searchTextBox = TextBox.create()
                .setPlaceholder("Search for items or shops")
                .setLeftAddon(Icons.ALL.search())
                .setFocusColor(Color.WHITE)
                .styler(style -> style.add("search-element", Styles.pull_right, Styles.vertical_center));
        DelayedTextInput.create(searchTextBox.getInputElement(), 200)
                .setDelayedAction(() -> searchValueConsumer.accept(searchTextBox.getValue()));
        layout.disableLeftPanel();
        layout.getContentPanel().styler(style -> style.setMarginBottom("100px"));
        layout.getNavigationBar().getNavigationBar().appendChild(div()
                .css("search-container")
                .css(Styles.pull_right)
                .add(searchTextBox));
        layout.showFooter();
        footerRow = Row.create().styler(style -> style.setPadding("20px 200px"));
        layout.getFooter().appendChild(footerRow);
        layout.getFooter().appendChild(Row.create()
                .styler(style -> style.setMarginTop("20px"))
                .appendChild(Column.span12()
                        .centerContent()
                        .appendChild(span().textContent("© 2018 Crafts, Inc."))
                )
        );
    }

    private void addActionItem(IsElement element, ActionHandler actionHandler) {
        HTMLElement action = layout.addActionItem(element);
        DominoElement.of(action)
                .styler(style -> style.add("navigation-item"))
                .addClickListener(evt -> actionHandler.onActionExecuted());
    }

    @Override
    public void show() {
        layout.show(ColorScheme.BLACK);
    }

    @Override
    public void addPrimaryAction(String actionTitle, ActionHandler actionHandler) {
        addActionItem(p().textContent(actionTitle).css("primary-action-item"), actionHandler);
    }

    @Override
    public void addSecondaryAction(String actionTitle, ActionHandler actionHandler) {
        addActionItem(p().textContent(actionTitle), actionHandler);
    }

    @Override
    public void setSearchHandler(Consumer<String> searchValueConsumer) {
        this.searchValueConsumer = searchValueConsumer;
    }

    @Override
    public void setContent(Content content) {
        layout.getContentPanel().clearElement();
        layout.getContentPanel().appendChild(Js.<HTMLElement>uncheckedCast(content.get()));
    }

    @Override
    public void addFooterCategory(String category) {
        HtmlContentBuilder<HTMLUListElement> ul = ul()
                .css("footer-list-category", Color.WHITE.getStyle());
        ul.add(h(5).textContent(category));
        footerRow.appendChild(Column.span3()
                .appendChild(ul));
        footerCategories.put(category, ul.asElement());
    }

    @Override
    public void addFooterCategoryItem(String category, String itemTitle, String url) {
        footerCategories.get(category)
                .appendChild(a().attr("href", url)
                        .style("color: white;")
                        .add(li().textContent(itemTitle)).asElement());
    }
}