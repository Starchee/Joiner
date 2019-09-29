import java.util.ArrayList;

public class Parameters {
    private String typeOfMerge;
    private String typeOfSort = "Right";
    private String outputFileName;
    private String typeOfDate;
    private ArrayList<String> args = new ArrayList<>();
    private ArrayList<String> inputFileNames = new ArrayList<>();

    public Parameters(String[] args) {
        for (String str : args) {
            this.args.add(str);
        }
        parseParameters();
    }

    public void parseParameters() {
        if (args.contains("-a") | args.contains("-d")) {
            for (int i = 0; i < 2; i++){
                if (args.get(i).equals("-i")){
                    typeOfDate = "Integer";
                } else if (args.get(i).equals("-s")){
                    typeOfDate = "String";
                } else if (args.get(i).equals("-d")){
                    typeOfSort = "Left";
                }
            }
            outputFileName = args.get(2);
            for (int i = 3; i < args.size(); i++){
                inputFileNames.add(args.get(i));
            }

        } else {
            if (args.get(0).equals("-i")){
                typeOfDate = "Integer";
            } else if (args.get(0).equals("-s")){
                typeOfDate = "String";
            }
            outputFileName = args.get(1);
            for (int i = 2; i < args.size(); i++){
                inputFileNames.add(args.get(i));
            }
        }
        getParameters();
    }


    private void getParameters() {
        if (typeOfDate == null){
            throw new IncorrectParametersException();
        }

        if (typeOfDate.equals("Integer") & (typeOfSort.equals("Right"))) {
            typeOfMerge = "IntegerRight";
        } else if (typeOfDate.equals("Integer")) {
            typeOfMerge = "IntegerLeft";
        } else if (typeOfSort.equals("Right")) {
            typeOfMerge = "StringRight";
        } else {
            typeOfMerge = "StringLeft";
        }
    }

    public String getTypeOfMerge() {
        return typeOfMerge;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public ArrayList<String> getInputFileNames() {
        return inputFileNames;
    }
}

