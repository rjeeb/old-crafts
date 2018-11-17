package org.dominokit.craft.layout.shared.response;

import org.dominokit.domino.api.shared.request.ResponseBean;

public class LayoutResponse implements ResponseBean {

    private String serverMessage;

    public LayoutResponse() {
    }

    public LayoutResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
