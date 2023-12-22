import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class MyCipher implements Serializable {

    private static final String SERIALIZED_FILE_NAME = "serialized";

    private static final MyCipher instance = (MyCipher) Deserializer.getInstance().deSerialize(SERIALIZED_FILE_NAME);
    private final int[] array;
    private final static String initVector = "keymotherfucker!";

    //TODO remove this key value later as we can generate it from int array provided

    MyCipher(int[] array) {
        this.array = array;
    }

    public static MyCipher getInstance() {
        return instance;
    }

    /*
        By not saving the exact value of key in class attr, we can enhance our security,with generating key value everytime it is needed
        this level of security can be reached.
        But the generating logic should not be a complex process,as it every time it can consume too much time
         */
    private String keyGenerator() throws Exception {
        if (array.length < 16) {
            throw new IllegalArgumentException("Array must have at least 16 elements");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            char asciiChar = (char) array[i];
            result.append(asciiChar);
        }
        return result.toString();
    }

    public String generateRandomIV() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);
        String base64Encoded = Base64.getEncoder().encodeToString(iv);
        return base64Encoded.substring(0, 16);
    }

    public String decode(String encryptedData) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(keyGenerator().getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(Base64.getUrlDecoder().decode(encryptedData));
        return new String(original);
    }

    public String encode(String data) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(keyGenerator().getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getUrlEncoder().encodeToString(encrypted).replaceAll("=","");
    }
}
