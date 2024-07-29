package Kruchkov.Task4.Checkers;

import Kruchkov.Task4.Checker;
import Kruchkov.Task4.DataLog;
import Kruchkov.Task4.FileReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DateChecker implements Checker {

    private FileReader fileReader;
    private  DataLog dataLog;

    public DateChecker() {
    }

    @Autowired
    public void setfileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public DataLog getDataLog() {
        return this.dataLog;
    }
    private void ErrorLog(Method method) {

         LocalDateTime opBegin;
         String         className;
         String          fileName;
         Map<Field, Object> data = new HashMap<>();
        Class classB = method.getClass();

        opBegin = LocalDateTime.now();
        className = classB.getName();
        fileName = "D:\\work\\Обучение\\2024\\CFT_java\\KruchkovTask4\\src\\main\\resources\\Log\\errorLogfile.log";
        dataLog = new DataLog(opBegin, className, fileName , data);


        try {
            Field[] fields = classB
                    .getDeclaredFields();

            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    data.put( field, field.get((Object) this));
                } catch (IllegalAccessException except) {
                    throw new RuntimeException(except);
                }
            }
        }
        catch (Exception  ex) {
            throw new RuntimeException(ex);
        }
        dataLog.setData(data);
        dataLog.accept();
    }

    public boolean checkFileReader(String msgID , FileReader fileReader) {
            Class[] classes = new Class[2];
        if (fileReader.getDate()== null) {
            classes[0] = String.class;
            classes[1] = FileReader.class;
            try {
                ErrorLog(this.getClass().getDeclaredMethod("checkFileReader", classes));
            }
            catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
            return false;
        }
        return true;
    }
}
