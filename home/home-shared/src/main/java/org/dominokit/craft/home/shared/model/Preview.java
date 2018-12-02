package org.dominokit.craft.home.shared.model;

import org.dominokit.domino.api.shared.request.ResponseBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

@JSONMapper
public class Preview implements ResponseBean {

    private String title;
    private String linkTitle;
    private String imageUrl;
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
