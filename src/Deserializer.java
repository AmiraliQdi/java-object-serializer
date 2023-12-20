import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserializer {

    private static final Deserializer instance = new Deserializer();

    private Deserializer() {
    }

    public static Deserializer getInstance() {
        return instance;
    }

    public Object deSerialize(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            return objectIn.readObject();

        } catch (Exception e) {
            System.out.println("Error while deserializing.Could be you using instance where you should not");
            return null;
        }
    }
}

