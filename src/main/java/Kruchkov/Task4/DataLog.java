package Kruchkov.Task4;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;


public class DataLog /* implements  Consumer<DataLog> */ {
    private LocalDateTime   opBegin;
    private String         className;
    private String          fileName;
    private Map<Field, Object> data ;



    public DataLog() {

    }

    @Autowired
    public DataLog(
                   LocalDateTime opBegin,
                   String         className,
                   String          fileName,
                    Map<Field, Object>   data
    )
    {
        this.opBegin = opBegin;
        this.className = className;
        this.fileName = fileName;
    }

    public void setOpBegin(LocalDateTime opBegin) {
        this.opBegin = opBegin;
    }

    public void setData(Map<Field, Object> data) {
        this.data = data;
    }

    public Map<Field, Object> getData() {
        return this.data;
    }

    public String getclassName () {
        return this.className;
    }

    public LocalDateTime getOpBegin () {
        return this.opBegin;
    }

    public void accept()  {
        String str ="";
// Вывод лога в файл

         try {

            FileOutputStream outputStream = new FileOutputStream(fileName);

            str = "Дата операци " + opBegin.toString() + " имя класса " +  className;
            str += '\n';
            byte[] strToBytes = str.getBytes();

            outputStream.write(strToBytes);
            str = " ";
            List<Field> fields = data.keySet().stream().toList();
            for (Field field : fields ) {
                Object object = data.get(field);
                str += field.getName() + " : ";
                if (object != null)
                    str +=  object.toString()+'\n';
                else
                    str += "null  " + '\n';
                strToBytes = str.getBytes();
                outputStream.write(strToBytes);
                str="";
            }
            outputStream.close();
        }
        catch(IOException except) {
             try {
             throw new IOException(except);
             }
             catch (Exception ex) {
                 throw new RuntimeException(ex);
             }
        }
    }
}
