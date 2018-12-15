package org.dominokit.craft.category.client.requests;

import org.dominokit.domino.api.client.request.*;
import org.dominokit.domino.api.shared.request.ResponseBean;

public class TestResponse<S extends ResponseBean> implements Response<S> {
    private Success<S> success;
    private S response;

    public TestResponse(S response) {
        this.response = response;
    }

    @Override
    public CanFailOrSend onSuccess(Success<S> success) {
        this.success = success;
        return new TestCanFailOrSend(success);
    }

    private class TestCanFailOrSend implements CanFailOrSend {

        private Success<S> success;

        public TestCanFailOrSend(Success<S> success) {
            this.success = success;
        }

        @Override
        public void send() {
            success.onSuccess(response);
        }

        @Override
        public CanSend onFailed(Fail fail) {
            return () -> success.onSuccess(response);
        }
    }
}
