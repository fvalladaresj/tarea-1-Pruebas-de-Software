package org.example;
import java.util.Scanner;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args ) {
        String result = "";
        Stack<String> stack = new Stack<String>();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Ingresar cadena de palabras en una linea: ");
        String userInput = myObj.nextLine();  // Read user input
        logger.info("Input ingresado: " + userInput);

        //verificación input
        if (userInput.isEmpty()  || userInput.length() > 200 || !userInput.matches("([a-zA-Z]+[\\s]*\\b[\\s]*){2,}")) {
            logger.error("Entrada no valida");
            System.exit(0);
        }

        //Ingresar palabras a la pila
        String[] arrOfStr = userInput.split(" ", 0);
        for (String word: arrOfStr) {
            if (!word.isEmpty()) stack.push(word);
        }
        Integer size = stack.size();

        logger.info("Stack generado: " + stack);

        System.out.println("Ingresar posición de la primera palabra a comparar: ");
        String firstWordPos = myObj.nextLine();  // Read user input
        // Verificar posición
        if (!firstWordPos.matches("[0-9]+")) {
            logger.error("Entrada no valida");
            System.exit(0);
        } else if (Integer.parseInt(firstWordPos) <= 0 || Integer.parseInt(firstWordPos) > size) {
            logger.error("Posición fuera de rango");
            System.exit(0);
        }

        System.out.println("Ingresar posición de la segunda palabra a comparar: ");
        String secondWordPos = myObj.nextLine();  // Read user input
        // Verificar posición
        if (!secondWordPos.matches("[0-9]+")) {
            logger.error("Entrada no valida");
            System.exit(0);
        } else if ( Integer.parseInt(secondWordPos) <= 0 || Integer.parseInt(secondWordPos) > size) {
            logger.error("Posición fuera de rango");
            System.exit(0);
        } else if (firstWordPos.equals(secondWordPos)) {
            logger.error("Posición igual a la primera posición");
            System.exit(0);
        }

        if (Integer.parseInt(secondWordPos) < Integer.parseInt(firstWordPos)) {
            for(int i = size; i > Integer.parseInt(firstWordPos); i--) {
                stack.pop();
            }
            String firstWord = stack.pop();

            size = stack.size();
            for(int i = size; i > Integer.parseInt(secondWordPos); i--) {
                stack.pop();
            }
            String secondWord = stack.pop();
            result = firstWord.equals(secondWord) ? "VERDADERO" : "FALSO";
            logger.info("Resultado comparar " + firstWord + " y " + secondWord + ": " + result);
        } else {
            for(int i = size; i > Integer.parseInt(secondWordPos); i--) {
                stack.pop();
            }
            String secondWord = stack.pop();

            size = stack.size();
            for(int i = size; i > Integer.parseInt(firstWordPos); i--) {
                stack.pop();
            }
            String firstWord = stack.pop();
            result = firstWord.equals(secondWord) ? "VERDADERO" : "FALSO";
            logger.info("Resultado comparar " + firstWord + " y " + secondWord + ": " + result);
        }
    }
}
