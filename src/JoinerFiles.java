import exceptions.NotEnoughFilesException;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Logger;

public class JoinerFiles {
    private static Logger log = Logger.getLogger(JoinerFiles.class.getName());
    private ArrayList<String> inputFileNames;
    private ComparatorForMerge compare;
    private String outputFileName;


    public JoinerFiles(String typeOfMerge, String outputFileName, ArrayList<String> InputFileNames) {
        this.compare = new ComparatorForMerge(typeOfMerge);
        this.outputFileName = outputFileName;
        this.inputFileNames = InputFileNames;
    }

    public void startMerge() throws IOException {
        ListIterator<String> iterator = inputFileNames.listIterator();
        while (inputFileNames.size()>2){
            int i = 0;
            String file1 = iterator.next();
            iterator.remove();
            String file2 = iterator.next();
            iterator.remove();
            mergeFiles(file1,file2, "temp"+i+".txt");
            iterator.add("temp"+i+".txt");
            i++;
        }
        if (inputFileNames.size()==2){
            String file1 = inputFileNames.get(1);
            String file2 = inputFileNames.get(0);
            mergeFiles(file1,file2, outputFileName);
        } else {
            throw new NotEnoughFilesException();
        }
    }
    private void mergeFiles(String inputFileName1, String inputFileName2, String outputFileName3) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(inputFileName1));
        BufferedReader reader2 = new BufferedReader(new FileReader(inputFileName2));
        PrintWriter fileWriter = new PrintWriter(outputFileName3);
        ComparatorForMerge comparator = new ComparatorForMerge("IntegerRight");

        String file1 = reader1.readLine();
        String file2 = reader2.readLine();


        while (file1 != null | file2 != null) {
            if (file1 == null & file2 != null) {
                log.info(file2+" - write in "+ outputFileName3);
                fileWriter.println(file2);
                file2 = reader2.readLine();
            } else if (file2 == null & file1 != null) {
                log.info(file1+" - write in "+ outputFileName3);
                fileWriter.println(file1);
                file1 = reader1.readLine();
            } else {
                if (comparator.compareForMerge(file1,file2)) {
                    log.info(file1+" - write in "+ outputFileName3);
                    fileWriter.println(file1);
                    file1 = reader1.readLine();

                } else {
                    log.info(file2+" - write in "+ outputFileName3);
                    fileWriter.println(file2);
                    file2 = reader2.readLine();
                }
            }
        }
        reader1.close();
        reader2.close();
        fileWriter.close();
    }
}
