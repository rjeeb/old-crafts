package org.dominokit.craft.model;

import org.dominokit.craft.exception.InvalidItemException;
import org.dominokit.craft.items.shared.model.Violation;
import org.immutables.value.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Value.Immutable
@Value.Style(stagedBuilder = true)
public abstract class Item {

    public abstract String reference();

    public abstract String title();

    public abstract String whoMadeIt();

    public abstract String whatIsIt();

    public abstract String whenWasItMade();

    public abstract String category();

    public abstract RenewalOptions renewalOption();

    public abstract ItemType itemType();

    public abstract String description();

    public abstract double price();

    public abstract int quantity();

    public abstract List<String> imageReferences();

    public abstract Optional<String> section();

    public abstract Optional<List<String>> tags();

    public abstract Optional<List<String>> materials();

    public abstract Optional<List<Variation>> variations();

    public abstract Optional<Personalization> personalization();

    @Value.Check
    protected void validate() {
        List<Violation> violations = new ArrayList<>();
        if (title().isEmpty()) {
            violations.add(createInvalidViolation("title"));
        }
        if (whoMadeIt().isEmpty()) {
            violations.add(createInvalidViolation("whoMadeIt"));
        }
        if (whatIsIt().isEmpty()) {
            violations.add(createInvalidViolation("whatIsIt"));
        }
        if (whenWasItMade().isEmpty()) {
            violations.add(createInvalidViolation("whenWasItMade"));
        }
        if (category().isEmpty()) {
            violations.add(createInvalidViolation("category"));
        }
        if (description().isEmpty()) {
            violations.add(createInvalidViolation("description"));
        }
        if (price() < 0) {
            violations.add(createInvalidViolation("price"));
        }
        if (quantity() < 0) {
            violations.add(createInvalidViolation("quantity"));
        }
        if (isNull(imageReferences()) || imageReferences().isEmpty()) {
            violations.add(createInvalidViolation("imageReferences"));
        }
        if (!violations.isEmpty()) {
            throw new InvalidItemException(violations);
        }
    }

    private Violation createInvalidViolation(String propertyName) {
        Violation violation = new Violation();
        violation.setPropertyName(propertyName);
        violation.setErrorMessage(propertyName + ".is.invalid");
        return violation;
    }
}
