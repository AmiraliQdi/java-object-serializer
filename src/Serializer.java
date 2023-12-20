import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    private static final Serializer instance = new Serializer();

    private Serializer() {
    }

    public static Serializer getInstance() {
        return instance;
    }

    public void serialize(Object o, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(o);

        } catch (Exception e) {
            System.out.println("Error while tried to serialize!");
        }
    }
}

