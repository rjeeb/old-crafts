package org.dominokit.craft.home.shared.response;

import org.dominokit.domino.api.shared.request.ResponseBean;

public class HomeResponse implements ResponseBean {

    private String serverMessage;

    public HomeResponse() {
    }

    public HomeResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
