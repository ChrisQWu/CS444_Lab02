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
        String iv = encryptedText.substring(encryptedText.length()-32,encryptedText.length());
        String msg;
        for (int i = encryptedText.length()-32; i > 32; i-=32) {
            msg = iv;
            iv = encryptedText.substring(i-32,i);
            modIV = byteByByte(iv,msg)+modIV;
            System.out.println("IV     : "+iv);
            System.out.println("Message: "+msg);
        }
    }

    public String byteByByte(String iv, String msg)
    {
        String modIV ="";
        for (int i = 32, pad = 1; i>0 ; i-=2, pad++) {//byte by byte for-loop starting from the right
            String ivByte = iv.substring(i-2, i);
            String msgByte = msg.substring(i-2, i);
            int ivVal = hexStringtoDecimal(Constants.HEX+ivByte);
            int msgVal = hexStringtoDecimal(Constants.HEX+msgByte);
            for (int j = 0; j < 256; j++) {
                if(j!=ivVal && (ivVal^j)==pad) {
                    System.out.println("Pad: "+pad+" "+ivVal + " xor " + j + " " + (ivVal ^ j));
                }
            }
            modIV = ivVal+modIV;
//            System.out.println("String: "+ivByte+" "+ivVal);
        }
        return modIV;
    }
}