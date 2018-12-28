package org.dominokit.craft.exception;

import org.dominokit.craft.items.shared.model.Violation;

import java.util.List;

public class InvalidImageException extends RuntimeException {
    private List<Violation> violations;

    public InvalidImageException(List<Violation> violations) {
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        return "InvalidImageException{" +
                "violations=" + violations +
                '}';
    }
}
