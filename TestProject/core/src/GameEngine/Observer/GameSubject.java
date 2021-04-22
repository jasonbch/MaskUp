package GameEngine.Observer;

public interface GameSubject {
    void Attach(GameObserver o);
    void Dettach(GameObserver o);
    void Notify(String args);
}
