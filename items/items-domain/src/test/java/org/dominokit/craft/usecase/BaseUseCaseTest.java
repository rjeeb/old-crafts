package org.dominokit.craft.usecase;

import org.dominokit.craft.exception.RequestCannotBeNullException;
import org.dominokit.craft.items.shared.model.Violation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class BaseUseCaseTest<U extends BaseUseCase> {

    protected U useCase;

    @BeforeEach
    void setUp() {
        useCase = createUseCase();
        initTest();
    }

    protected void initTest() {
        // do nothing
    }

    protected abstract U createUseCase();

    protected Violation createViolation(String propertyName, String errorMessage) {
        return new Violation(propertyName, errorMessage);
    }

    @Test
    void givenNullRequestWhenExecuteThenShouldThrowException() {
        useCase.execute(null)
                .test()
                .assertError(RequestCannotBeNullException.class);
    }
}
