import java.io.ObjectStreamException;
import java.io.Serializable;

public class EagerSingleton implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final EagerSingleton instance = new EagerSingleton();  // Created at class load time

    private EagerSingleton() {
        System.out.println("Eager Singleton created.");
    }

    public static EagerSingleton getInstance() {
        return instance;
    }

    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}
