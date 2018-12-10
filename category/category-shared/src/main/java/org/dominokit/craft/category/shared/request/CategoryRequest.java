package org.dominokit.craft.category.shared.request;

import org.dominokit.domino.api.shared.request.RequestBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

@JSONMapper
public class CategoryRequest implements RequestBean {

    private String message;

    public CategoryRequest() {
    }

    public CategoryRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
