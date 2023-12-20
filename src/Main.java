import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        String serializedFileName = "serializedObject";

        MyCipher newCipher = new MyCipher("KEY");
        //System.out.println(newCipher.decode("salam"));
        serialize(newCipher, serializedFileName);
        //encodeFile(serializedFileName,serializedFileName);

        //decodeFile(serializedFileName,serializedFileName);
        //parrentMyCipher myCipher = (parrentMyCipher) deSerialize(serializedFileName);
        //childMyCipher child = (childMyCipher) myCipher;
        //System.out.println(myCipher.decode("hello"));
    }

    public static void serialize(Object obj,String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(obj);

        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static Object deSerialize(String fileName){
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            return objectIn.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void encodeFile(String inputFileName, String outputFileName) {
        try (FileInputStream fileIn = new FileInputStream(inputFileName);
             BufferedInputStream bufferedIn = new BufferedInputStream(fileIn)) {

            // Read bytes from the serialized file
            byte[] fileBytes = new byte[(int) new File(inputFileName).length()];
            bufferedIn.read(fileBytes);

            // Encode the bytes to Base64 and write to the output file
            Base64.Encoder encoder = Base64.getEncoder();
            try (FileWriter fileWriter = new FileWriter(outputFileName)) {
                fileWriter.write(encoder.encodeToString(fileBytes));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decodeFile(String inputFileName, String outputFileName) {
        try (FileReader fileReader = new FileReader(inputFileName)) {

            // Read Base64-encoded string from the input file
            StringBuilder encodedString = new StringBuilder();
            int c;
            while ((c = fileReader.read()) != -1) {
                encodedString.append((char) c);
            }

            // Decode the Base64 string and write to the output file
            Base64.Decoder decoder = Base64.getDecoder();
            try (FileWriter fileWriter = new FileWriter(outputFileName)) {
                byte[] decodedBytes = decoder.decode(encodedString.toString());
                fileWriter.write(new String(decodedBytes, StandardCharsets.UTF_8));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}