package org.dominokit.craft.home.client.ui.components;

import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLImageElement;
import elemental2.dom.HTMLParagraphElement;
import org.dominokit.craft.home.shared.model.Review;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.ui.utils.TextNode;

import static org.jboss.gwt.elemento.core.Elements.*;

public class ReviewComponent extends BaseDominoElement<HTMLDivElement, ReviewComponent> {

    private DominoElement<HTMLDivElement> element = DominoElement.of(div().css("review-component"));
    private DominoElement<HTMLImageElement> profileImage = DominoElement.of(img().css("review-profile-image", Styles.horizontal_center));
    private DominoElement<HTMLParagraphElement> reviewInfo = DominoElement.of(p().css("review-info", Styles.align_center));
    private RatingComponent ratingComponent = RatingComponent.create().styler(style -> style.add(Styles.align_center));
    private DominoElement<HTMLParagraphElement> reviewDescription = DominoElement.of(p().css("review-description", Styles.align_center));
    private DominoElement<HTMLImageElement> itemImage = DominoElement.of(img().css("review-item-image", Styles.horizontal_center));
    private DominoElement<HTMLAnchorElement> itemTitle = DominoElement.of(a().css("review-item-title", Styles.align_center));

    public ReviewComponent(Review review) {

        profileImage.setAttribute("src", review.getUserProfileImageUrl());

        reviewInfo
                .appendChild(span().css(Styles.font_bold).textContent(review.getUsername()))
                .appendChild(TextNode.of(" wrote on " + review.getCreationDate()));

        ratingComponent.setValue(review.getRate());

        reviewDescription.setTextContent(review.getDescription());

        itemImage.setAttribute("src", review.getItemImageUrl());

        itemTitle.setTextContent(review.getItemTitle());
        element
                .appendChild(profileImage)
                .appendChild(reviewInfo)
                .appendChild(ratingComponent)
                .appendChild(reviewDescription)
                .appendChild(div().add(itemImage))
                .appendChild(div().css(Styles.align_center).add(itemTitle));
    }

    public static ReviewComponent create(Review review) {
        return new ReviewComponent(review);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }
}
