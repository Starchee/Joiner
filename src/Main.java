import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getGlobal();

    public static void main(String[] args) {
        String[] args1 = new String[]{"-i","result.txt","123.txt","asd.txt"};
        Parameters parameters = new Parameters(args1);
        JoinerFiles joinerFiles = new JoinerFiles(parameters.getTypeOfMerge(),parameters.getOutputFileName(),parameters.getInputFileNames());
        try {
            joinerFiles.startMerge();
        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.INFO,"Exception: ",e);
        }
    }
}
