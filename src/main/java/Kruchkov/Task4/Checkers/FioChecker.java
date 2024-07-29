package Kruchkov.Task4.Checkers;


import Kruchkov.Task4.Checker;
import Kruchkov.Task4.FileReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FioChecker implements Checker {

    private FileReader fileReader;
    static List<String> tmpFio = new ArrayList<>();;


    public FioChecker() {
        tmpFio.add("");
        tmpFio.add("");
    }

    @Autowired
    public void setInput(FileReader fileReader) {
        this.fileReader = fileReader;
    }

   public boolean checkFileReader(String msgID , FileReader fileReader) {
       tmpFio.set(1,"");

       Arrays.stream(fileReader.getFio().split(" ")).forEach(x -> {
           if (!(x.substring(0, 1).toUpperCase() == (x.substring(0, 1)))) {
               x = x.substring(0, 1).toUpperCase() + x.substring(1);
           }
           tmpFio.set(1,tmpFio.get(1) + x + " ");
       });
       tmpFio.set(1,tmpFio.get(1).trim());
       fileReader.setFio(tmpFio);
       return true;
   }
}
