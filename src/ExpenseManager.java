import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    private static ExpenseManager instance;
    private List<Expense> expenses;
    private Map<String, User> users; // userId -> User
    private Map<User, Map<User, Double>> balanceSheet; // who owes whom

    // Private constructor for Singleton
    private ExpenseManager() {
        this.expenses = new ArrayList<>();
        this.users = new HashMap<>();
        this.balanceSheet = new HashMap<>();
    }
    // Singleton Pattern thread safe
    public static ExpenseManager getInstance() {
        if (instance == null) {
            synchronized (ExpenseManager.class) {
                if (instance == null) {
                    instance = new ExpenseManager();
                }
            }
        }
        return instance;
    }

    public void addUser(User user){
        users.put(user.getId(),user);
        balanceSheet.putIfAbsent(user, new HashMap<>());
    }

    public void addExpense(Expense expense){
        SplitStrategy splitStrategy = SplitStrategyFactory.getSplitStrategy(expense.getSplitType());
        splitStrategy.validateAndCalculate(expense.getSplits(), expense.getTotalAmount());

        // Validate total paid amount matches total expense
        double totalPaid = expense.getPaidBy().values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        if (Math.abs(totalPaid - expense.getTotalAmount()) > 0.01) {
            throw new IllegalArgumentException(
                    "Total paid amount (" + totalPaid + ") does not match expense amount (" +
                            expense.getTotalAmount() + ")"
            );
        }
        // Update balance sheet
        updateBalances(expense);

        // Add expense to list
        expenses.add(expense);

        System.out.println("Expense added successfully: " + expense.getDescription());
    }

    private void updateBalances(Expense expense) {
        // For each person in the split, calculate what they owe
        for (Split split : expense.getSplits()) {
            User user = split.getUser();
            double userOwes = split.getAmount();

            // Calculate how much this user paid
            double userPaid = expense.getPaidBy().getOrDefault(user, 0.0);

            // Net amount this user owes (negative if they should receive)
            double netAmount = userOwes - userPaid;

            if (Math.abs(netAmount) < 0.01) {
                continue; // Skip if amount is negligible
            }

            // Distribute the net amount among all payers
            for (Map.Entry<User, Double> paidEntry : expense.getPaidBy().entrySet()) {
                User paidBy = paidEntry.getKey();
                double paidAmount = paidEntry.getValue();

                if (paidBy.equals(user)) {
                    continue; // Skip if same user
                }

                // Calculate proportional amount to be settled
                double proportionalAmount = (paidAmount / expense.getTotalAmount()) * userOwes;

                if (Math.abs(proportionalAmount) < 0.01) {
                    continue;
                }

                // Update balance: user owes paidBy
                updateBalance(user, paidBy, proportionalAmount);
            }
        }    
    }

    private void updateBalance(User user, User paidBy, double proportionalAmount) {
    }
}
