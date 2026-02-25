import java.util.List;

/**
 * Strategy interface for different split calculation methods
 * Implements Strategy Pattern - allows selecting algorithm at runtime
 * Follows ISP (Interface Segregation Principle) - clients depend on specific interface
 */
public interface SplitStrategy {
    /**
     * Validates and calculates split amounts for each user
     * @param splits List of splits to calculate
     * @param totalAmount Total expense amount
     * @throws IllegalArgumentException if validation fails
     */
    void validateAndCalculate(List<Split> splits, double totalAmount);
}
