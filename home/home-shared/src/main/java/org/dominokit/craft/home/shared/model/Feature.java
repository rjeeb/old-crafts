package org.dominokit.craft.home.shared.model;

import org.dominokit.domino.api.shared.request.ResponseBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

@JSONMapper
public class Feature implements ResponseBean {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
