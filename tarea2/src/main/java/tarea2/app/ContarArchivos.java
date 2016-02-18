/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**teto
 *
 * @author juannoguera
 */
public class ContarArchivos {
    
    public int loc = 0;
    public int part = 0;
    public int items = 0;
    public boolean metodoProbale = false;
    static Pattern patComent1 = Pattern.compile("//.*");
    static Pattern patComent2 = Pattern.compile("/\\*.*");
    static Pattern patComent3 = Pattern.compile(".*\\*/");
    static Pattern patConditionalifAbre = Pattern.compile(".*if.*\\(*.*");
    static Pattern patConditionalWhileAbre = Pattern.compile("while.*\\(*.*");
    static Pattern patConditionalForAbre = Pattern.compile("for.*\\(*.*");
    static Pattern patConditionalForDo = Pattern.compile("DO.*\\(*.*");
    static Pattern metodo = Pattern.compile(".*\\(.*");
    static Pattern metodoSi = Pattern.compile(".*}.*");
    static Pattern metodoNo = Pattern.compile(".*;.*");
    static Pattern comentarios = Pattern.compile(".*System.out.println.*");

    public void contar(File file) throws FileNotFoundException, IOException {
        String line;
        BufferedReader in = new BufferedReader(new FileReader(file));
        boolean comentario = false;
        App.totalPARTS ++;
        while ((line = in.readLine()) != null) {
            if (!comentario) {
                if (line.trim().length() > 0) {
                    Matcher mat = patComent1.matcher(line.trim());
                    if (!mat.matches()) {
                        mat = patComent2.matcher(line.trim());
                        if (!mat.matches()) {
                            loc++;
                            App.totalLOCS ++;
                            validarMetodo(line.trim());
                            mat = metodoNo.matcher(line.trim());
                            if (!mat.matches() && metodoProbale) {
                                items++;
                                App.totalITEMS ++;
                                metodoProbale = false;
                            } else {
                                if (mat.matches() && metodoProbale) {
                                    metodoProbale = false;
                                }
                            }
                        } else {
                            comentario = true;
                        }
                    }
                }
            } else {
                Matcher mat2 = patComent3.matcher(line.trim());
                if (mat2.matches()) {
                    comentario = false;
                }
            }
        }
        System.out.println("============================");
        System.out.println("Archivo: " + file);
        System.out.println("Líneas de código: " + loc);
        System.out.println("Items: " + items);
        System.out.println("============================");
    }
    
    public void validarMetodo(String line) {
        Matcher mat = patConditionalifAbre.matcher(line.trim());
        if (!mat.matches()) {
            mat = patConditionalWhileAbre.matcher(line.trim());
            if (!mat.matches()) {
                mat = patConditionalForAbre.matcher(line.trim());
                if (!mat.matches()) {
                    mat = comentarios.matcher(line);
                    if (!mat.matches()) {
                        mat = metodo.matcher(line.trim());
                        if (mat.matches()) {
                            metodoProbale = true;
                        }
                    }
                }
            }
        }
    }
}
