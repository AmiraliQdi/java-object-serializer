import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws Exception {

        String serializedFileName = "serialized";
        MyCipher myCipher = new MyCipher(new int[]{110, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 ,14, 15, 16});
        Serializer.getInstance().serialize(myCipher,serializedFileName);
        System.out.println(myCipher.encode("a"));

        //System.out.println(MyCipher.getInstance().decode("ESwbOCLzkhppSN89NTLdbw"));
    }
}