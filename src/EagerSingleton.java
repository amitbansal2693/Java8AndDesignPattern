public class EagerSingleton {

    private static final EagerSingleton instance = new EagerSingleton();  // Created at class load time

    private EagerSingleton() {
        System.out.println("Eager Singleton created.");
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
