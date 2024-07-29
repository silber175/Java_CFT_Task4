package Kruchkov.Task4;

//import org.hibernate.dialect.H2Dialect;

import Kruchkov.Task4.Config.Config;
import Kruchkov.Task4.Models.Login;
import Kruchkov.Task4.Models.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;


public class DataProcess implements Runnable {
    private final static  class Checked {
        private int checked = 0;
        public void inc() {
            this.checked++;
        }
        public int getChecked() {
            return this.checked;
        }

        public void setChecked(int checked) {
            this.checked = checked;
        }
    }

    @Autowired
    private FileReader fileReader;
    @Autowired
    private ArrayList<FileReader> fileReaderList;

    private DbInsert dbInsert;

    String msgID;
    private  User user ;
    private Login login;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private List<User> users = new ArrayList<>();

    private List<Login> logins = new ArrayList<>();

    @Autowired
    private List<Checker> checkerList = new ArrayList<>();

    @Autowired
    public DataProcess(ArrayList<FileReader> fileReaderList) {
        this.fileReaderList = fileReaderList;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public List<Login> getLogins() {
        return this.logins;
    }

    @Autowired
   public void setDbInsert(DbInsert dbInsert) {
        this.dbInsert = dbInsert;
    }

    public  DbInsert getDbInsert() {
        return this.dbInsert;
    }

    @LogTransformation("D:\\work\\Обучение\\2024\\CFT_java\\KruchkovTask4\\src\\main\\resources\\Log\\logfile.log")
    public void run() {

    //   fileReaderList = fileReader.getFileReaderList();

        Checked checked = new Checked();

        for (FileReader fileReader : fileReaderList) {
            checked.setChecked(0);
            checkerList.stream()
                    .filter(y -> (y.checkFileReader(msgID, fileReader)))
                    .forEach(y -> { checked.inc();
                    });
            if (checked.getChecked() == checkerList.size()) {     // Если все  check  вернули true
                User user = new User();
                user.setFio(fileReader.getFio());
                user.setUserName(fileReader.getLogin());
                dbInsert.setUser(user);
                User userId = dbInsert.insertUser();
                users.add(user);
                Login login = new Login();
                login.setUserId(userId);
                try {
                    try {
                        login.setAccessDate(LocalDate.parse(fileReader.getDate(), formatter));
                    } catch (Exception except) {
                       throw new RuntimeException(except);
                    }
                } catch (DateTimeParseException e) {
                    throw new RuntimeException(e);
                }
                login.setApplication(fileReader.getAppType());
                logins.add(login);
            }
        }
        if (logins.size() > 0 ) {    // Если есть , что записывать в БД
            dbInsert.setUsers(users);
            dbInsert.setLogins(logins);

            dbInsert.insertLogin();
        }
    }

}

