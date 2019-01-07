package org.dominokit.craft.usecase;

import io.reactivex.Single;
import org.dominokit.craft.exception.RequestCannotBeNullException;
import org.dominokit.craft.items.shared.model.Violation;

import static java.util.Objects.isNull;

public abstract class BaseUseCase<R, S> {

    public Single<S> execute(R request) {
        if (isNull(request)) {
            return Single.error(new RequestCannotBeNullException());
        }
        return doExecute(request);
    }

    protected abstract Single<S> doExecute(R request);

    protected Violation createViolation(String propertyName, String errorMessage) {
        return new Violation(propertyName, errorMessage);
    }
}
