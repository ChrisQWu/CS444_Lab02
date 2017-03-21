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

    private int hexStringtoDecimal(String iv) {
        return Integer.decode(iv);
    }

    private String decimaltoHexString(int mask) {
        String temp = Integer.toHexString(mask);
        return temp.length()==1?"0"+temp:temp;
    }

    private void blockByBlock()
    {
        String modIV = "";
        String iv;
        String msg;
//        for (int i=1; i < Constants.ENCRYPTED_TEXT.length;i++) {
        for (int i=1; i < 2;i++) {
            iv = Constants.ENCRYPTED_TEXT[i-1];
            msg = Constants.ENCRYPTED_TEXT[i];
            modIV = byteByByte(iv,msg)+modIV;
            System.out.println("Mod IV : "+modIV);
            System.out.println("IV     : "+iv);
            System.out.println("Message: "+msg);
        }
    }

    private String byteByByte(String iv, String msg)
    {
        String modIV ="";
        for (int i = 32; i>0 ; i-=2) {//byte by byte for-loop starting from the right
            for (int j = 0; j < 256; j++) {//find the proper R value
                String tempIV = iv.substring(0,i-2) + decimaltoHexString(j) + padModify(modIV);
                System.out.println("IV  : " + iv);
                System.out.println("Temp: "+tempIV);
                if(CommandLine.client(tempIV+msg)) {
                    System.out.println("Adding this to modIV: "+decimaltoHexString(j));
                    modIV = decimaltoHexString(j) + modIV;
                    break;
                }
            }
        }
        return modIV;
    }

    /**
     * Takes in the String of my modifications and change them so that they suit the current padding
     * i.e. modIV = 0x4a5d
     * The padding = modIV.length()/2 + 1 = 2 + 1 = 3. For the next byte to be modified
     * The first byte will be 0x5d. This byte was modified by me to obtain a padding of 0x01.
     *   To change this modified 0x5d to obtain a padding of 0x03 = 0x01 ^ (?)
     *   (?) = 0x01 ^ 0x03 = 0x02
     *   Therefore: 0x5d ^ 0x02 = 0x5f
     * The second byte will be 0x4a. This byte was modified by me to obtain a padding of 0x02.
     *   To change this modified 0x4a to obtain a padding of 0x03 = 0x02 ^ (?)
     *   (?) = 0x02 ^ 0x03 = 0x01
     *   Therefore: 0x4a ^ 0x01 = 0x4b
     * To generalize: new byte = old byte ^ its padding ^ target padding
     * @param modIV
     * @return A modIV that is appropiate for the next padding target
     */
    private String padModify(String modIV)
    {
        if(modIV.isEmpty())return "";
        String padIV = "";
        int targetPad = modIV.length()/2 + 1;
        for (int i = modIV.length(), pad = 1; i > 0; i-=2, pad++) {
            int tempByte = hexStringtoDecimal(Constants.HEX+modIV.substring(i-2,i));
            tempByte ^= pad ^ targetPad;
            padIV = decimaltoHexString(tempByte) + padIV;
        }
        return padIV;
    }
}