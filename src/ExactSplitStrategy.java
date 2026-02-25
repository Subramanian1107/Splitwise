import java.util.List;

public class ExactSplitStrategy implements SplitStrategy{

    @Override
    public void validateAndCalculate(List<Split> splits, double totalAmount) {
        double sumOfSplits = 0;

        for (Split split : splits) {
            if (split.getAmount() < 0) {
                throw new IllegalArgumentException("Split amount cannot be negative");
            }
            sumOfSplits += split.getAmount();
        }

        // Validate that sum of splits equals total amount (with small tolerance for floating point)
        if (Math.abs(sumOfSplits - totalAmount) > 0.01) {
            throw new IllegalArgumentException(
                    "Sum of exact splits (" + sumOfSplits + ") does not match total amount (" + totalAmount + ")"
            );
        }
    }
}
