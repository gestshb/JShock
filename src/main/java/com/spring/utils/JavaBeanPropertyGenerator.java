package com.spring.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaBeanPropertyGenerator {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String clazzName = scanner.nextLine();
        String currentBean = scanner.nextLine();

        List<String> list = generate(Class.forName(clazzName), currentBean);
        System.out.println("**********************************************************************************************************");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("**********************************************************************************************************");
    }

    private static List<String> generate(Class<?> clazz, String currentBean) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().equals(String.class)) {
                list.add("private JavaBeanStringProperty " + fields[i].getName() + "Property;");
            } else if (fields[i].getType().equals(Double.class)) {
                list.add("private JavaBeanDoubleProperty " + fields[i].getName() + "Property;");
            } else if (fields[i].getType().equals(Boolean.class)) {
                list.add("private JavaBeanBooleanProperty " + fields[i].getName() + "Property;");
            } else {
                list.add("private JavaBeanObjectProperty<" + fields[i].getType().getName() + "> " + fields[i].getName() + "Property;");
            }


        }

        list.add(" public void binding() {");
        list.add("     try {");


        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().equals(String.class)) {
                list.add(fields[i].getName() +
                        "Property = JavaBeanStringPropertyBuilder.create().bean(" + currentBean + ").name(\"" + fields[i].getName() + "\").build();");
            } else if (fields[i].getType().equals(Double.class)) {
                list.add(fields[i].getName() +
                        "Property = JavaBeanDoublePropertyBuilder.create().bean(" + currentBean + ").name(\"" + fields[i].getName() + "\").build();");

            } else if (fields[i].getType().equals(Boolean.class)) {
                list.add(fields[i].getName() +
                        "Property = JavaBeanBooleanPropertyBuilder.create().bean(" + currentBean + ").name(\"" + fields[i].getName() + "\").build();");

            } else {

                list.add(fields[i].getName() +
                        "Property = JavaBeanObjectPropertyBuilder.<" + fields[i].getType().getName() + ">create().bean(" + currentBean + ").name(\"" + fields[i].getName() + "\").build();");
            }


        }
        list.add(" } catch (Exception e) {\n" +
                "            LOGGER.error(\"Binding Error\", e);\n" +
                "        }\n" +
                "    }");
        return list;

    }
}
