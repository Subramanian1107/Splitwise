public class ExactSplit extends Split{

    public ExactSplit(User user, double amount) {
        super(user);
        setAmount(amount);
    }

    @Override
    public SplitType getSplitType() {
        return SplitType.EXACT;
    }
}
