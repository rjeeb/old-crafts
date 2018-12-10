package org.dominokit.craft.items.shared.request;

import org.dominokit.domino.api.shared.request.RequestBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

@JSONMapper
public class ItemsRequest implements RequestBean {

    private String message;

    public ItemsRequest() {
    }

    public ItemsRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
