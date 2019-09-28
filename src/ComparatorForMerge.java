public class ComparatorForMerge {
    private String typeOfMerge;

    public ComparatorForMerge(String typeOfMerge) {
        this.typeOfMerge = typeOfMerge;
    }

    public boolean compareForMerge(String file1, String file2){
        boolean compare = true;


        switch (typeOfMerge){
            case("IntegerRight"):{
                compare = Integer.parseInt(file1) <= Integer.parseInt(file2);
            }
            break;
            case ("IntegerLeft"):{
                compare = Integer.parseInt(file1) >= Integer.parseInt(file2);
            }
            break;
            case ("StringRight"):{
                compare = file1.compareTo(file2) <= 0;
            }
            break;
            case ("StringLeft"):{
                compare = file1.compareTo(file2) >= 0;
            }
            break;
        }
        return compare;
    }
}

