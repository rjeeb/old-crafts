package org.dominokit.craft.home.shared.request;

import org.dominokit.domino.api.shared.request.RequestBean;

public class HomeRequest implements RequestBean {

    private String message;

    public HomeRequest() {
    }

    public HomeRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
