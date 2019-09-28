import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String[] args1 = new String[]{"-i","result.txt","123.txt","qwer.txt","asd.txt"};

        Parameters parameters = new Parameters(args1);
        System.out.println(parameters.getTypeOfMerge());
        System.out.println(parameters.getOutputFileName());

        for (String x : parameters.getInputFileNames()){
            System.out.print(x+" ");
        }
        JoinerFiles joinerFiles = new JoinerFiles(parameters.getTypeOfMerge(),parameters.getOutputFileName(),parameters.getInputFileNames());
        try {
            joinerFiles.startMerge();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
