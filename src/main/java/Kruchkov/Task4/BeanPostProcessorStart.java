package Kruchkov.Task4;

import Kruchkov.Task4.Config.Config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;

public class BeanPostProcessorStart implements BeanPostProcessor {
    private DataLog dataLog;
    private LocalDateTime opBegin;
    private String         className;
    private String          fileName;
    private Map<Field, Object> data = new HashMap<>();
    private List<Object>   result;

public DataLog getDataLog() {
    return this.dataLog;
}

    public Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException {

        Class classB = bean.getClass();
        Method[] methods = classB.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(LogTransformation.class)) {


                LogTransformation annotation = method.getAnnotation(LogTransformation.class);
                opBegin = LocalDateTime.now();
                className = classB.getName();
                fileName = annotation.value();
                dataLog = new DataLog(opBegin, className, fileName , data);
                // logFileMy.setFileName(annotation.value());
                System.out.println("File name = "+annotation.value());
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException {
        //     System.out.println("Before Bean '" + beanName + "' created : " + bean.toString());

        Class classB = bean.getClass();
        Method[] methods = classB.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(LogTransformation.class)) {
                try {
                    Field[] fields =  bean
                            .getClass()
                            .getDeclaredFields();

                    for (Field field : fields) {
                        try {
                            field.setAccessible(true);
                            data.put( field, field.get((Object) bean));
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
        }
        return bean;
    }
}



