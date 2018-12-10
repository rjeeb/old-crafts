package org.dominokit.craft.category.shared.response;

import org.dominokit.domino.api.shared.request.ResponseBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

@JSONMapper
public class CategoryResponse implements ResponseBean {

    private String serverMessage;

    public CategoryResponse() {
    }

    public CategoryResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
