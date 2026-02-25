/**
 * Factory class to create appropriate SplitStrategy based on SplitType
 * Implements Factory Pattern - encapsulates object creation logic
 * Follows SRP (Single Responsibility Principle) - only responsible for creating strategies
 */
public class SplitStrategyFactory {
    public static SplitStrategy getSplitStrategy(SplitType splitType){
        switch (splitType) {
            case EQUAL:
                return new EqualSplitStrategy();
            case EXACT:
                return new ExactSplitStrategy();
            default:
                throw new IllegalArgumentException("Invalid split type: " + splitType);
        }
    }
}
