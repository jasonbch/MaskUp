package GameEngine.Observer;

public interface GameSubject {
    void attachGameObserver(GameObserver object);

    void detachGameObserver(GameObserver object);

    void notifyGameObserver(String args);
}
