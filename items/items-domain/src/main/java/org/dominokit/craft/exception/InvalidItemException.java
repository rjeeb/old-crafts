package org.dominokit.craft.exception;

import org.dominokit.craft.items.shared.model.Violation;

import java.util.List;

public class InvalidItemException extends RuntimeException {
    private List<Violation> violations;

    public InvalidItemException(List<Violation> violations) {
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        return "InvalidItemException{" +
                "violations=" + violations +
                '}';
    }
}
