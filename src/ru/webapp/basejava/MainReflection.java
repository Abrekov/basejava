package ru.webapp.basejava;

import ru.webapp.basejava.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume resume = new Resume("uuid1");
        Class<? extends Resume> klass = resume.getClass();
        Method method = klass.getMethod("toString");
        System.out.println(method.invoke(resume));
    }
}
