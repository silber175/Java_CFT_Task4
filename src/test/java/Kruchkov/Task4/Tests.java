package Kruchkov.Task4;

import Kruchkov.Task4.Checkers.AppTypeChecker;
import Kruchkov.Task4.Checkers.DateChecker;
import Kruchkov.Task4.Checkers.FioChecker;
import Kruchkov.Task4.Models.Login;
import Kruchkov.Task4.Models.User;
import org.hibernate.AssertionFailure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Tests {
    @Test
    public void readFile() {    // Чтение файлов
        Class[] classes = null;
        FileReader fileReader;
        try {
            ApplicationContext context =  SpringApplication.run(KruchkovTask4.class);
            FileReader fileReaderB =context.getBean(FileReader.class);
            fileReader = (FileReader) fileReaderB;
            Method method = fileReader.getClass().getMethod("init", classes);
            method.invoke(fileReader,classes);
            List<FileReader> fileReaderList = fileReader.getFileReaderList();
            Assertions.assertNotNull(fileReaderList.get(0));
            Assertions.assertNotNull(fileReaderList.get(1));
            System.out.println(" fileReaderList.get(0).getFio() = "+fileReaderList.get(0).getFio()+" fileReaderList.get(1).getFio() = "+fileReaderList.get(1).getFio() );
            Assertions.assertTrue((fileReaderList.get(0)!=null) &
                     (fileReaderList.get(1) != null),
                    "Неправильно читаются данные из файла");
        }
        catch (Exception ex) {
            System.out.println("Ошибка приложение или теста: Чтение файлов");
            throw  new RuntimeException(ex);
        }
    }

    @Test
    public void dataControlAppType() { // Контроль  данных типа соединения
        Class[] classes = null;
        FileReader fileReader;
        String readerValue;
        String msgID = null;
        try {



                ApplicationContext context =  SpringApplication.run(KruchkovTask4.class);
                FileReader fileReaderB =context.getBean(FileReader.class);
                fileReader = (FileReader) fileReaderB;
                Method method = fileReader.getClass().getMethod("init", classes);
                method.invoke(fileReader,classes);
                List<FileReader> fileReaderList = fileReader.getFileReaderList();

                readerValue = fileReaderList.get(1).getAppType();
                AppTypeChecker appTypeChecker = new AppTypeChecker();
                appTypeChecker.checkFileReader(msgID , fileReaderList.get(1));
                Assertions.assertEquals(fileReaderList.get(1).getAppType(),"other."+readerValue,"Не работает проверка по типи соединения");

        }
        catch (Exception ex) {
            System.out.println("Ошибка приложение или теста: Контроль  данных типа соединения ");
            throw  new RuntimeException(ex);
        }
    }

    @Test
    public void dataControlDate() { // Контроль  данных типа даты
        Class[] classes = null;
        FileReader fileReader;
        String readerValue;
        String msgID = null;
        Boolean result = false;
        List<String> stringListL = new ArrayList<>();
        try {
            ApplicationContext context =  SpringApplication.run(KruchkovTask4.class);
            FileReader fileReaderB =context.getBean(FileReader.class);
            fileReader = (FileReader) fileReaderB;
            Method method = fileReader.getClass().getMethod("init", classes);
            method.invoke(fileReader,classes);
            List<FileReader> fileReaderList = fileReader.getFileReaderList();
            for (int i=0; i<3; i++)
                stringListL.add(null);
            fileReaderList.get(1).setDate(stringListL);
            DateChecker dateChecker = new DateChecker();
            result = dateChecker.checkFileReader(msgID , fileReaderList.get(1));
            Assertions.assertFalse(result,"Не проверяется пустоеое значение даты'");

        }
        catch (Exception ex) {
            System.out.println("Ошибка приложение или теста: Контроль  данных типа даты ");
            throw  new RuntimeException(ex);
        }
    }

    @Test
    public void dataControlFio() { // Контроль  данных фио
        Class[] classes = null;
        FileReader fileReader;
        String readerValue;
        String msgID = null;
        Boolean result = false;
        List<String> stringListL = new ArrayList<>();
        try {
            ApplicationContext context =  SpringApplication.run(KruchkovTask4.class);
            FileReader fileReaderB =context.getBean(FileReader.class);
            fileReader = (FileReader) fileReaderB;
            Method method = fileReader.getClass().getMethod("init", classes);
            method.invoke(fileReader,classes);
            List<FileReader> fileReaderList = fileReader.getFileReaderList();

            readerValue = fileReaderList.get(1).getFio();
            FioChecker fioChecker = new FioChecker();
            fioChecker.checkFileReader(msgID , fileReaderList.get(0));
            fioChecker.checkFileReader(msgID , fileReaderList.get(1));
            Assertions.assertTrue( (fileReaderList
                    .get(0)
                    .getFio()
                    .substring(0, 1)
                    .toUpperCase()
                    .equals(fileReaderList
                            .get(0)
                            .getFio()
                            .substring(0, 1)))
                    && ((fileReaderList
                    .get(1)
                    .getFio()
                    .substring(0, 1)
                    .toUpperCase()
                    .equals(fileReaderList
                            .get(1)
                            .getFio()
                            .substring(0, 1)))),
            "Не прошел checker fio");

        }
        catch (Exception ex) {
            System.out.println("Ошибка приложение или теста: Контроль  данных фио");
            throw  new RuntimeException(ex);
        }
    }

    @Test
    public void dataProcess() { //  обработка данных
        try {

        }
        catch (Exception ex) {
            System.out.println("Ошибка приложение или теста: обработка данных");
            throw  new RuntimeException(ex);
        }
    }

    @Test
    public void dBDataInsertUser() {    // Запись данных в ДБ users + обработка данных
         List<User> users ;
          List<Login> logins;

        try {
            ApplicationContext context =  SpringApplication.run(KruchkovTask4.class);
            DataProcess dataProcessB =context.getBean(DataProcess.class);

            dataProcessB.run();
            DbRead dBRead = new  DbRead();
            DataProcess dataProcess =  dataProcessB;
            dBRead.setUserRepository(dataProcess
                    .getDbInsert()
                    .getUserRepository());
            dBRead.setLoginRepository(dataProcess
                    .getDbInsert()
                    .getLoginRepository());
            dBRead.read();
            users = dBRead.getUsers();
            logins = dBRead.getLogins();
            boolean vFound = false;
            for (int i=0; i < 2; i++) {
                Assertions.assertTrue(users
                        .get(i)
                        .getUserName()
                        .equals("petrov") | users.get(i)
                                            .getUserName()
                                            .equals("fiskin"), "Не записались в БД users");
            }
        }
        catch (Exception ex) {
            System.out.println("Ошибка приложение или теста: Запись данных в ДБ");
            throw  new RuntimeException(ex);
        }
    }

    @Test
    public void dBDataInsertLogin() {    // Запись данных в ДБ logins + обработка данных
        List<User> users ;
        List<Login> logins;
        long userId;
        try {
            ApplicationContext context =  SpringApplication.run(KruchkovTask4.class);
            DataProcess dataProcessB =context.getBean(DataProcess.class);

            dataProcessB.run();
            DbRead dBRead = new  DbRead();
            DataProcess dataProcess =  dataProcessB;
            dBRead.setUserRepository(dataProcess
                    .getDbInsert()
                    .getUserRepository());
            dBRead.setLoginRepository(dataProcess
                    .getDbInsert()
                    .getLoginRepository());
            dBRead.read();
            users = dBRead.getUsers();
            logins = dBRead.getLogins();
            boolean vFound = false;
            for (int i=0; i < 2; i++) {
                userId = users.get(i).getId();
                System.out.println("user = "+users.get(i).toString() +" logins.size() = "+logins.size() );
                for (Login login : logins) {
                    System.out.println("login.getUserId() = "+login.getUserId().toString());
                    if (login.getUserId().equals(users.get(i))) {
                        vFound = true;
                        break;
                    }
                }
                Assertions.assertTrue(vFound,"Не найден user в таблице login");
            }
        }
        catch (Exception ex) {
            System.out.println("Ошибка приложение или теста: Запись данных в ДБ");
            throw  new RuntimeException(ex);
        }
    }

    @Test
    public void commonLogging() { // Проверка аннотации  LogTransformation
        try {
            ApplicationContext context =  SpringApplication.run(KruchkovTask4.class);
            DataProcess dataProcessB =context.getBean(DataProcess.class);

            dataProcessB.run();
            DbRead dBRead = new  DbRead();
            DataProcess dataProcess =  dataProcessB;

            BeanPostProcessorStart  postprocessor =context.getBean(BeanPostProcessorStart.class);
            DataLog dataLog = postprocessor.getDataLog();
            Assertions.assertNotNull(dataLog.getData(),"Нет записис в лог по аннотации LogTransformation ");
        }
        catch (Exception ex) {
            System.out.println("Ошибка приложение или теста: Проверка аннотации  LogTransformation");
            throw  new RuntimeException(ex);
        }
    }
    @Test
    public void dataErrorLogging() {    // Проверка логирования при ошибке даты (пусто)
        Class[] classes = null;
        FileReader fileReader;
        String readerValue;
        String msgID = null;

        List<String> stringListL = new ArrayList<>();
        try {
            ApplicationContext context =  SpringApplication.run(KruchkovTask4.class);
            FileReader fileReaderB =context.getBean(FileReader.class);
            fileReader = (FileReader) fileReaderB;
            Method method = fileReader.getClass().getMethod("init", classes);
            method.invoke(fileReader,classes);
            List<FileReader> fileReaderList = fileReader.getFileReaderList();
            for (int i=0; i<3; i++)
                stringListL.add(null);
            fileReaderList.get(1).setDate(stringListL);
            DateChecker dateChecker = new DateChecker();
            dateChecker.checkFileReader(msgID , fileReaderList.get(1));

            Assertions.assertNotNull(dateChecker
                    .getDataLog()
                    .getData(),"Не проверяется пустоеое значение даты'");
        }
        catch (Exception ex) {
            System.out.println("Ошибка приложение или теста:  Проверка логирования при ошибке даты (пусто)");
            throw  new RuntimeException(ex);
        }
    }
}
