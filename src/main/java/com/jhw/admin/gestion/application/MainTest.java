package com.jhw.admin.gestion.application;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class MainTest {

    public static void main(String args[]) throws Exception {
        String str = "asdasd\nasdasd\n235234\n54646546asd";
        System.out.println(str);

        String format = "<html>" + str.replace("\n", "<br>") + "</html>";
        System.out.println(format);
    }

}
