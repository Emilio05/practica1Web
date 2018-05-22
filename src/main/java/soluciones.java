import java.io.*;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Scanner;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class soluciones {

    public soluciones(){

        String url = leerURL();
        cargarDocumento(url);
        cantidadLineas(cargarDocumento(url), url);
        cantidadParrafos(cargarDocumento(url), url);
    }

    private String leerURL() {

        Scanner reader = new Scanner(System.in);
        System.out.println("Introducir URL: ");
        String url = reader.nextLine();
        System.out.println("");
        return url;
    }

    private Document cargarDocumento(String url){

        try{
            Document doc = Jsoup.connect(url).get();
            return doc;
           // String title = doc.title();
            //System.out.printf(title); //probando con el titulo de la pagina web
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private void cantidadLineas(Document doc, String url){

        System.out.println("A. El recurso retornado por " + url + " tiene un total de "+ doc.html().split("\n").length + " lineas");
    }

    private void cantidadParrafos(Document doc, String url){

        Elements parrafos = doc.getElementsByTag("p");
        System.out.println("B. La cantidad de parrafos que hay en el recurso retornado por " + url + "son: " + parrafos.size() +
                " parrafos");
    }

    private void imagenesDentroDeParrafos(Document doc, String url){

        int contador = 0;

        Elements parrafos = doc.getElementsByTag("p");

       
    }
}
