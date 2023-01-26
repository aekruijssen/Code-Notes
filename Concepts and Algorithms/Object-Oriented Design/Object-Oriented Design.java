// Object-Oriented Design

/*
    Approach:
        Handle ambiguity
        Define core objects
        Analyze relationships
        Investigate actions
*/


// Design Patterns

/*
    Singleton Class
    - class has only one instance
    - ensures access to instance through application

    - useful for cases with global object with 1 instance
    - sometimes considered "anti-pattern", can interfere w/ unit testing
*/
public class Restaurant {
    private static Restaurant _instance = null;
    protected Restaurant() { ... }
    public static Restaurant getInstance() {
        if (_instance == null) {
            _instance = new Restaurant();
        }
        return _instance;
    }
}

/*
    Factory Method
    - interface for creating instance of a class
    - subclasses decide which class to instantiate

    - creator class could be abstract or concrete
*/
public class CardGame {
    public static CardGame createCardGame(GameType type) {
        if (type == GameType.Poker) {
            return new PokerGame();
        } else if (type == GameType.BlackJack) {
            return new BlackJackGame();
        }
        return null;
    }
}