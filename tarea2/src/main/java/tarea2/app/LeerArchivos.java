/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2.app;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author juannoguera
 */
public class LeerArchivos {
    static Pattern isJava = Pattern.compile(".*java");
    public void contar(File file) throws IOException {
        File[] ficheros = file.listFiles();
        for (int x = 0; x < ficheros.length; x++) {
            if (ficheros[x].isDirectory()) {
                contar(ficheros[x]);
            } else {
                Matcher mat = isJava.matcher(ficheros[x].getName().trim());
                if(mat.matches()){
                    ContarArchivos contarArchivos = new ContarArchivos();
                    contarArchivos.contar(ficheros[x]);
                }
            }
        }
    }
}
