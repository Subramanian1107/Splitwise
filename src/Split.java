public abstract class Split {
    private User user;
    private double amount;

    public Split(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public abstract SplitType getSplitType();

    public double getAmount() {
        return amount;
    }
}
