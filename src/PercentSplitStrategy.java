import java.util.List;

public class PercentSplitStrategy implements SplitStrategy{
    @Override
    public void validateAndCalculate(List<Split> splits, double totalAmount) {
        // Calculate amounts based on percentages
        for (Split split : splits) {
            PercentSplit percentSplit = (PercentSplit) split;
            double amount = (totalAmount * percentSplit.getPercent()) / 100.0;
            split.setAmount(amount);
        }
    }
}
