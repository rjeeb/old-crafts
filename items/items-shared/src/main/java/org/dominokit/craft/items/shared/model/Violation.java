package org.dominokit.craft.items.shared.model;

import java.util.Objects;

public class Violation {

    private String propertyName;
    private String errorMessage;

    public Violation() {
    }

    public Violation(String propertyName, String errorMessage) {
        this.propertyName = propertyName;
        this.errorMessage = errorMessage;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violation violation = (Violation) o;
        return Objects.equals(getPropertyName(), violation.getPropertyName()) &&
                Objects.equals(getErrorMessage(), violation.getErrorMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPropertyName(), getErrorMessage());
    }

    @Override
    public String toString() {
        return "Violation{" +
                "propertyName='" + propertyName + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
