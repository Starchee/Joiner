import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getGlobal();

    public static void main(String[] args) {
        Parameters parameters = new Parameters(args);
        JoinerFiles joinerFiles = new JoinerFiles(parameters.getTypeOfMerge(),parameters.getOutputFileName(),parameters.getInputFileNames());
        try {
            joinerFiles.startMerge();
        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.INFO,"Exception: ",e);
        }
    }
}
