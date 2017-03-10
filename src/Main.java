import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thebaker on 3/10/17.
 */
public class Main {
    private List<String> iv = new ArrayList<>();
    private List<String> message = new ArrayList<>();

    public static void main(String[] args) {

    }

    Main() {
        start();
    }

    private void start() {
        //initialize iv array
        //initialize message array
        String encryptedText = Constants.ENCRYPTED_TEXT;
        while (!encryptedText.isEmpty())
        {
            iv.add(encryptedText.substring(0,32));
            message.add(encryptedText.substring(32,64));
            encryptedText.replaceAll("",encryptedText.substring(0,64));
        }

    }

    public int toHex(String iv) {
        return Integer.decode(iv).intValue();
    }

    public String toHexString(int mask) {
        return Integer.toHexString(mask);
    }
}
