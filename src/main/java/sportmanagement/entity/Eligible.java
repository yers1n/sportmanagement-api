package sportmanagement.entity;

public interface Eligible {
    boolean isEligible();

    default String eligibilityLabel() {
        return isEligible() ? "Eligible" : "Not eligible";
    }
}