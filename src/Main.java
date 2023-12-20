import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        //String serializedFileName = "serialized";
        //MyCipher myCipher = new MyCipher(new int[]{1, 2, 3},"KEY");
        //Serializer.getInstance().serialize(myCipher,serializedFileName);

        System.out.println(MyCipher.getInstance().decode("adawda"));
    }
}