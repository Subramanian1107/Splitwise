import java.util.List;
import java.util.Map;

public class Expense {
    private String expenseId;
    private String description;
    private double totalAmount;
    private Map<User, Double> paidBy; // Users who paid and their amounts
    private List<Split> splits;
    private SplitType splitType;

    private Expense(ExpenseBuilder builder){
        this.expenseId = builder.expenseId;
        this.description = builder.description;
        this.totalAmount = builder.totalAmount;
        this.paidBy = builder.paidBy;
        this.splits = builder.splits;
        this.splitType = builder.splitType;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getDescription() {
        return description;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Map<User, Double> getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public static class ExpenseBuilder{
        private String expenseId;
        private String description;
        private double totalAmount;
        private Map<User, Double> paidBy; // Users who paid and their amounts
        private List<Split> splits;
        private SplitType splitType;


        public ExpenseBuilder setExpenseId(String expenseId) {
            this.expenseId = expenseId;
            return this;
        }

        public ExpenseBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ExpenseBuilder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public ExpenseBuilder setPaidBy(Map<User, Double> paidBy) {
            this.paidBy = paidBy;
            return this;
        }

        public ExpenseBuilder setSplits(List<Split> splits) {
            this.splits = splits;
            return this;
        }

        public ExpenseBuilder setSplitType(SplitType splitType) {
            this.splitType = splitType;
            return this;
        }
        public Expense build(){
            return new Expense(this);
        }
    }

}
