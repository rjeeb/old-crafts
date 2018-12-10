package org.dominokit.craft.items.shared.response;

import org.dominokit.domino.api.shared.request.ResponseBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

@JSONMapper
public class ItemsResponse implements ResponseBean {

    private String serverMessage;

    public ItemsResponse() {
    }

    public ItemsResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
