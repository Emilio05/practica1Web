
import java.io.*;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.validator.UrlValidator;


public class soluciones {

    public soluciones(){

        String url = leerURL();
        cargarDocumento(url);
        cantidadLineas(cargarDocumento(url), url);
        cantidadParrafos(cargarDocumento(url), url);
        imagenesDentroDeParrafos(cargarDocumento(url), url);
        cantidadDeFormularios(cargarDocumento(url));
        mostrarInputs(cargarDocumento(url));
        peticionAlServidor(cargarDocumento(url), url);
    }

    private String leerURL() {

        UrlValidator urlValidator = new UrlValidator();

        String url = null;

        Scanner reader = new Scanner(System.in);
        System.out.println("Introducir URL: ");
        url = reader.nextLine();
        System.out.println("");

        if((urlValidator.isValid(url)))
        return url;
        else {
            System.out.println("URL NO VALIDA! El formato de la URL debe ser de la siguiente manera: https://[direccion], ej. https://facebook.com");
            return null;
        }





    }

    private Document cargarDocumento(String url){

        try{
            Document doc = Jsoup.connect(url).timeout(20000).get();
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
        System.out.println("B. La cantidad de parrafos que hay en el recurso retornado por " + url + " son: " + parrafos.size() +
                " parrafos");


    }

    private void imagenesDentroDeParrafos(Document doc, String url){

        int contador = 0;

        for (Element parrafo: doc.getElementsByTag("p")) {
            for (Element imagen: parrafo.getElementsByTag("img")) {

                contador++;
            }
        }
        System.out.println("C. La cantidad de imagenes dentro de parrafos que hay en el recurso retornado por " + url + " son: " + contador +
                " imagenes");

    }

    private void cantidadDeFormularios(Document doc){

        int contadorPost = 0;
        int contadorGet = 0;

        for(Element formulario: doc.getElementsByTag("form")){
            for(Element metodoGet: formulario.getElementsByAttributeValue("method", "get")){

                contadorGet++;
            }
        }

        for(Element formulario: doc.getElementsByTag("form")){
            for(Element metodoGet: formulario.getElementsByAttributeValue("method", "post")){

                contadorPost++;
            }
        }

        System.out.println("D. La cantidad de formularios implementados con el metodo get son: " + contadorGet + ", y por " +
                "el metodo post son: " + contadorPost + " formularios");
    }

    private void mostrarInputs(Document doc){

        System.out.println("E. ");

        for(Element input: doc.getElementsByTag("input")){
            System.out.println(input);
        }
    }

    private void peticionAlServidor(Document doc, String url){

            try {
                for(Element formulario: doc.getElementsByTag("form"))
                {
                    if(formulario.attr("method").equals("post")){
                    String abs = formulario.absUrl("action");
                    doc = Jsoup.connect(abs)
                            .data("asignatura", "practica1")
                            .header("Matricula", "2012-0994")
                            .post();
                    System.out.println("F.");
                    System.out.println(doc.body().toString());
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
