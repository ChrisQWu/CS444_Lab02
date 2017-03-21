import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by c on 2/23/2017.
 */
public class CommandLine {
    private static final String Command = "python client.py -ip 64.106.46.58 -p 10032 -b ";
    private static final String id = " -id "+Constants.ID;
    /**
     * Calls pmars and get the warrior's fitness score
     *
     * @return fitness score of warrior.red
     */
    public static boolean client(String encrypted) {
        String send = Command+encrypted+id;
        boolean viable = false;
        try {
            // create a process and execute
            Process p = Runtime.getRuntime().exec(send);
            String s;
//            BufferedReader stdInput = new BufferedReader(new
//                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
//            System.out.println("Here is the standard output of the command:\n");
//            while ((s = stdInput.readLine()) != null) {
//                System.out.println(s);
//            }

            // read any errors from the attempted command
//            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
                if(s.contains("Message decrypted successfully"))
                {
                    System.out.println("Viable!!!: "+encrypted);
                    viable = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return viable;
    }

    public static void main(String[] args)
    {
        String send = Command+"756b55617a724369764d77564a374d6ad9fa" +
                "4774198e690ace071315b810f2cc0ef246103f3" +
                "a2e79faefd72206e794a8f8f236c8d520c6bddd659f30d256" +
                "a5fde758d934b49dcbbfd8da4b916e736efab70a2cbb4f438" +
                "1f53efb0038584dc9ff05dadaac5590a524701e11e1dc2200" +
                "48be18c64d4461168ac2ece6fbca35641f68ba8f0e20419c1" +
                "8be4947fa84371aa030c5b59db937947b70abcc7a6b4a75e0" +
                "f1bfb0c82330e4395951f910c7131ebe"+id;


        String line;
        boolean viable = false;
        try {
            // create a process and execute
            Process p = Runtime.getRuntime().exec(send);
            String s;
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
