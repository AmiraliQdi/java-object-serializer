import java.io.Serializable;

public class MyCipher implements Serializable {

    private static final String SERIALIZED_FILE_NAME = "serialized";

    private static final MyCipher instance = (MyCipher) Deserializer.getInstance().deSerialize(SERIALIZED_FILE_NAME);
    private final int[] array;

    //TODO remove this key value later as we can generate it from int array provided
    private final String key;

    MyCipher(int[] array,String key) {
        this.array = array;
        this.key = key;
    }

    public static MyCipher getInstance() {
        return instance;
    }

    /*
        By not saving the exact value of key in class attr, we can enhance our security,with generating key value everytime it is needed
        this level of security can be reached.
        But the generating logic should not be a complex process,as it every time it can consume too much time
         */
    private String keyGenerator() {
        //TODO key generation logic here
        return this.key;
    }

    public String decode(String s) {
        //TODO decode logic come here
        return "decoded{" + s + "}[" + keyGenerator() + "]";
    }

    public String encode(String s) {
        //TODO encode logic come here
        return "encoded{" + s + "}[" + keyGenerator() + "]";
    }
}
