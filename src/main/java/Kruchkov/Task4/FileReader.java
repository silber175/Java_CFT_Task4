package Kruchkov.Task4;


import Kruchkov.Task4.Config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//@ConfigurationProperties(prefix = "filereader")
public class FileReader {
    private String login;
    private String fio;
    private String date;
    private String appType;

    private String dirName;

    @Autowired
    private ArrayList<FileReader> fileReaderList ;

    private List<String> stringList = new ArrayList<>();

    public FileReader() {

    }


    public List<String> getStringArray() {
        return this.stringList;
    }

    public List<FileReader> getFileReaderList() {
        return this.fileReaderList;
    }


    public String getDirName() {
        return this.dirName;
    }

    public String getDate() {
        return date;
    }

    public String getAppType() {
        return appType;
    }

    public String getFio() {
        return fio;
    }

    public String getLogin() {
        return login;
    }

    public void setAppType(List<String> stringList) {
        this.appType = stringList.get(3).trim();
    }

    public void setDate(List<String> stringList) {
        if (stringList.get(2) == null)
            this.date = null;
        else
            this.date = stringList.get(2).trim();
    }

    public void setFio(List<String> stringList) {
        this.fio = stringList.get(1).trim();
    }

    public void setLogin(List<String> stringList) {
        this.login = stringList.get(0).trim();
    }

    public void init() {
        if (this.dirName == null) {
            Scanner scanner;
            Config config = new Config();
            if (this.dirName == null) {
                this.dirName = "D:\\work\\Обучение\\2024\\CFT_java\\KruchkovTask4\\src\\main\\resources\\Data\\";
            }

                        try {
                File f = new File(dirName);
                for (File file : f.listFiles()) {
                    try {
                        scanner = new Scanner(file, "UTF-8");
                        scanner.useDelimiter(System.getProperty("line.separator"));
                    } catch (Exception ex) {
                        try {
                            throw new FileNotFoundException(dirName);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    while (scanner.hasNext()) {

                        this.stringList = Arrays.stream(scanner.next().split(";")).toList();
                        try {
                            FileReader fileReader = new FileReader();
                            fileReader.setLogin(this.stringList);
                            fileReader.setDate(this.stringList);
                            fileReader.setAppType(this.stringList);
                            fileReader.setFio(this.stringList);
                            fileReaderList.add(fileReader);

                        } catch (Exception except) {
                            throw new RuntimeException(except);
                        }
//                    fileReaderList.add(fileReader);
                    }
                    scanner.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
