package org.dominokit.craft.layout.shared.request;

import org.dominokit.domino.api.shared.request.RequestBean;

public class LayoutRequest implements RequestBean {

    private String message;

    public LayoutRequest() {
    }

    public LayoutRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
