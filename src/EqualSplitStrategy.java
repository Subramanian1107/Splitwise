import java.util.List;

public class EqualSplitStrategy implements SplitStrategy{

    @Override
    public void validateAndCalculate(List<Split> splits, double totalAmount) {
        int numPeople = splits.size();
        double equalAmount = totalAmount / numPeople;
        for (Split split :splits)
            split.setAmount(equalAmount);
    }
}
