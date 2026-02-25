public class EqualSplit extends Split {

    public EqualSplit(User user) {
        super(user);
    }

    @Override
    public SplitType getSplitType() {
        return SplitType.EXACT;
    }
}
