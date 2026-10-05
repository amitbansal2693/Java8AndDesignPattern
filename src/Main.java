import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        var var=2;
        System.out.println(var);
        EagerSingleton original = EagerSingleton.getInstance();


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(original);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        EagerSingleton deserialized = (EagerSingleton) ois.readObject();
        ois.close();

        System.out.println("Original and deserialized are same object: " + (original == deserialized));
    }
}