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
    private ArrayList<File> tempFileList;


    public JoinerFiles(String typeOfMerge, String outputFileName, ArrayList<String> InputFileNames) {
        this.compare = new ComparatorForMerge(typeOfMerge);
        this.outputFileName = outputFileName;
        this.inputFileNames = InputFileNames;
    }

    public void startMerge() throws IOException {
        ListIterator<String> iterator = inputFileNames.listIterator();
        if (inputFileNames.size() > 2) {
            tempFileList = new ArrayList<>();
            int i = 0;
            while (inputFileNames.size() > 2) {
                String file1 = iterator.next();
                iterator.remove();
                String file2 = iterator.next();
                iterator.remove();
                String tempNameFile = "temp" + i + ".txt";
                mergeFiles(file1, file2, tempNameFile);
                iterator.add(tempNameFile);
                tempFileList.add(new File(tempNameFile));
                i++;
            }
        }
        if (inputFileNames.size()==2){
            String file1 = inputFileNames.get(1);
            String file2 = inputFileNames.get(0);
            mergeFiles(file1,file2, outputFileName);
        } else {
            throw new NotEnoughFilesException();
        }
        if (tempFileList!=null){
            for (File file : tempFileList){
                file.delete();
            }
        }
    }
    private void mergeFiles(String inputFileName1, String inputFileName2, String outputFileName3) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(inputFileName1));
        BufferedReader reader2 = new BufferedReader(new FileReader(inputFileName2));
        PrintWriter fileWriter = new PrintWriter(outputFileName3);
        /*
        increment 'i' using for don`t compare null;
        buffer 'buff' using for compare current line with line which write early;
         */

        String buff = null;
        int i = 0;
        String file1 = reader1.readLine();
        String file2 = reader2.readLine();


        while (file1 != null | file2 != null) {
            if (file1 == null & file2 != null) {
                if(compare.compareForMerge(file2,buff)){
                    log.info(file2 + " - can`t sort ");
                    file2 = reader2.readLine();
                } else{
                    buff = file2;
                    fileWriter.println(file2);
                    log.info(file2 + " - write in " + outputFileName3);
                    file2 = reader2.readLine();
                }
            } else if (file2 == null & file1 != null) {
                if(compare.compareForMerge(file1,buff)){
                    log.info(file1 + " - can`t sort ");
                    file1 = reader1.readLine();
                } else{
                    buff = file1;
                    fileWriter.println(file1);
                    log.info(file1 + " - write in " + outputFileName3);
                    file1 = reader1.readLine();
                }
            } else if (i > 0) {
                if (compare.compareForMerge(file1, file2) & compare.compareForMerge(file1, buff)) {
                    log.info(file1 + " - can`t sort");
                    file1 = reader1.readLine();
                } else if (compare.compareForMerge(file2, file1) & compare.compareForMerge(file2, buff)) {
                    log.info(file2 + " - can`t sort ");
                    file2 = reader2.readLine();
                } else if (compare.compareForMerge(file1, file2)) {
                    buff = file1;
                    fileWriter.println(file1);
                    log.info(file1 + " - write in " + outputFileName3);
                    file1 = reader1.readLine();
                } else if (compare.compareForMerge(file2, file1)) {
                    buff = file2;
                    fileWriter.println(file2);
                    log.info(file2 + " - write in " + outputFileName3);
                    file2 = reader2.readLine();
                } else {
                    buff = file1;
                    log.info(file1 + " - write in " + outputFileName3);
                    file1 = reader1.readLine();
                }
                    } else {
                    if (compare.compareForMerge(file1, file2)) {
                        buff = file1;
                        fileWriter.println(file1);
                        log.info(file1 + " - write in " + outputFileName3);
                        file1 = reader1.readLine();

                    } else {
                        buff = file2;
                        fileWriter.println(file2);
                        log.info(file2 + " - write in " + outputFileName3);
                        file2 = reader2.readLine();
                    }
                }
            i++;
        }
        reader1.close();
        reader2.close();
        fileWriter.close();
    }
}
