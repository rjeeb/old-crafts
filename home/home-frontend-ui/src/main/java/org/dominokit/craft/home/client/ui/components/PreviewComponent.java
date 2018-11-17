package org.dominokit.craft.home.client.ui.components;

import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLImageElement;
import org.dominokit.craft.home.shared.model.Preview;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import static org.jboss.gwt.elemento.core.Elements.*;

public class PreviewComponent extends BaseDominoElement<HTMLAnchorElement, PreviewComponent> {

    private DominoElement<HTMLAnchorElement> root = DominoElement.of(a());
    private DominoElement<HTMLDivElement> imageContainer = DominoElement.of(div().css("preview-image-container"));
    private DominoElement<HTMLImageElement> image = DominoElement.of(img().css("preview-image"));
    private DominoElement<HTMLDivElement> title = DominoElement.of(div().css("preview-title"));
    private ImageLoadedHandler imageLoadedHandler;

    public PreviewComponent(Preview preview) {
        imageContainer.appendChild(image
                .setAttribute("src", preview.getImageUrl())
                .addEventListener("load", evt -> imageLoadedHandler.onImageLoaded()))
                .appendChild(title
                        .appendChild(h(1)
                                .textContent(preview.getTitle()))
                        .appendChild(p()
                                .textContent(preview.getLinkTitle() + " >"))
                );

        root.setAttribute("href", preview.getLink())
                .setAttribute("target", "_blank")
                .appendChild(imageContainer);
    }

    public PreviewComponent setImageLoadedHandler(ImageLoadedHandler imageLoadedHandler) {
        this.imageLoadedHandler = imageLoadedHandler;
        return this;
    }

    @Override
    public HTMLAnchorElement asElement() {
        return root.asElement();
    }

    @FunctionalInterface
    public interface ImageLoadedHandler {
        void onImageLoaded();
    }
}
