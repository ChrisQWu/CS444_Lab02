import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by c on 2/23/2017.
 */
public class CommandLine {
    private static final String Command = "python client.py -ip localhost -p 10032 -b ";
    private static final String id = " -id "+Constants.ID;
    /**
     * Calls pmars and get the warrior's fitness score
     *
     * @return fitness score of warrior.red
     */
    public static boolean client(String encrypted) {
        String send = Command+"'"+encrypted+"' "+id;
        String line;
        boolean viable = false;
        try {
            // create a process and execute
            Process p = Runtime.getRuntime().exec(send, null, new File("./"));
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = r.readLine()) != null) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return viable;
    }

}
