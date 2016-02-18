package tarea2.app;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static int totalLOCS;
    public static int totalITEMS;
    public static int totalPARTS;
    
    public static void main( String[] args ) throws IOException
    {
         boolean rutaExiste = false;
        
        System.out.println("Empezamos el programa");
        System.out.println("Por favor introduzca el nombre del programa:");
        String nombrePrograma = "";
        String rutaPrograma = "";
        Scanner entradaEscaner = new Scanner(System.in); //Creación de un objeto Scanner
        nombrePrograma = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto 
        System.out.println("Ahora favor introduzca la ruta del programa:");
        rutaPrograma = entradaEscaner.nextLine();
        String sDirectorio = rutaPrograma+"/"+nombrePrograma;
        File f = new File(sDirectorio.trim());
        if (f.exists()){
            System.out.println();
            System.out.println("============================");
            System.out.println("============================");
            LeerArchivos cuenta = new LeerArchivos();
            cuenta.contar(f);
            System.out.println("============================");
            System.out.println("============================");
            System.out.println("En todo el programa hay: ");
            System.out.println("LOCS: " + totalLOCS);
            System.out.println("ITEMS: " + totalITEMS);
            System.out.println("PARTS: " + totalPARTS );
        }else{
            System.out.println("============================");
            System.out.println("============================");
            System.out.println("La ruta "+ sDirectorio + " no existe1");
        }
    }
}
