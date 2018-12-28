package org.dominokit.craft.usecase;

import org.dominokit.craft.exception.RequestCannotBeNullException;
import org.dominokit.craft.items.shared.model.Violation;

import static java.util.Objects.isNull;

public abstract class BaseUseCase<R, S> {

    public S execute(R request) {
        if (isNull(request)) {
            throw new RequestCannotBeNullException();
        }
        return doExecute(request);
    }

    protected abstract S doExecute(R request);

    protected Violation createViolation(String propertyName, String errorMessage) {
        return new Violation(propertyName, errorMessage);
    }
}
