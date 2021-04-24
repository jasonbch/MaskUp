package GameEngine.Observer;

public interface EnemySubject {
    void addObserver(BulletSpawnerObserver spawner);

    void removeObserver(BulletSpawnerObserver spawner);

    void notifyObservers();
}