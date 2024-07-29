package Kruchkov.Task4.Checkers;

import Kruchkov.Task4.Checker;
import Kruchkov.Task4.Models.AppType;
import Kruchkov.Task4.FileReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AppTypeChecker implements Checker {

    private FileReader fileReader;
    private List<String> stringListL = new ArrayList<>();

    public AppTypeChecker() {
    }

    @Autowired
    public void setDtaRecord(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public boolean checkFileReader(String msgID , FileReader fileReaderP) {
        if(fileReaderP.getAppType() == null) {
            msgID = fileReaderP.getAppType();
        } else if (!(fileReaderP.getAppType().equals(AppType.WEB.getName()) &&
                fileReaderP.getAppType().equals(AppType.MOBILE.getName()))) {
            msgID = "other." + fileReaderP.getAppType();
        }
        for (int i=0; i<4; i++)
            stringListL.add(msgID);

        fileReaderP.setAppType(stringListL);
        return true;
    }
}
