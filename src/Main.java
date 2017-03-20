import java.util.ArrayList;
import java.util.List;

/**
 * Created by thebaker on 3/10/17.
 */
public class Main {

    public static void main(String[] args) {
        new Main();
//        Main m = new Main();
//        System.out.println(m.hexStringtoDecimal("0x10"));
//        System.out.println("64: "+m.decimaltoHexString(64));
    }

    Main() {
        start();
    }

    private void start() {
        //initialize iv array
        //initialize message array
        blockByBlock();

    }

    public int hexStringtoDecimal(String iv) {
        return Integer.decode(iv);
    }

    public String decimaltoHexString(int mask) {
        return Integer.toHexString(mask);
    }

    public void blockByBlock()
    {
        String encryptedText = Constants.ENCRYPTED_TEXT;
        String modIV = "";
        for (int i = encryptedText.length(); i > 32; i-=64) {
            String iv = encryptedText.substring(i-32,i-16);
            String msg = encryptedText.substring(i-16,i);
            modIV = byteByByte(iv,msg)+modIV;
            System.out.println("IV     : "+iv);
            System.out.println("Message: "+msg);
        }
    }

    public String byteByByte(String iv, String msg)
    {
        String modIV ="";
        for (int i = 16, pad = 0; i>0 ; i-=2, pad++) {//byte by byte for-loop starting from the right
            String currentByte = iv.substring(i-2, i);
            int byteVal = hexStringtoDecimal(Constants.HEX+currentByte);
            modIV = currentByte+modIV;
            System.out.println(modIV);
        }
        return modIV;
    }
}