package pt.isec.pa.apoio_poe.ui.text;

import java.util.Scanner;

public class Main {

    private static final Scanner sin = new Scanner( System.in );
    private static final String menuSeparator = "//////////////////////////////////////////////////";
    private static final String stdPrompt = ">  ";

    public static void main(String[] args) {




        printMenu("Configuracao",
                "1 - Gestao de Alunos",
                "2 - Gestao de Docentes",
                "\n0 - Voltar");
        switch (readNumber(stdPrompt, 0, 2)){
            case 1: System.out.println(1);
            case 2:
                System.out.println(2);
        }


    }


    private static void printMenu(String title, String... options){
        StringBuilder str = new StringBuilder();
        str.append("\n" + menuSeparator + "\n\n");
        str.append("\t\t").append( title ).append("\n\n");

        for(String option : options)
            str.append("\t").append(option).append("\n");

        str.append("\n" + menuSeparator + "\n");
        System.out.println(str);
    }


    //print the prompt to the  screen and read a number between lowest and highest
    private static int readNumber(String prompt, int lowest, int highest){
        // read a number between the lowest and highest possible (inclusive)

        int option;
        while( true ){
            try{
                System.out.print(prompt);
                option = Integer.parseInt( sin.nextLine() );
                if( option >= lowest && option <= highest )
                    break;  // If option is valid
            } catch (NumberFormatException ignored) { }
            System.out.println("\nYou have not entered a valid option!");
        }
        return option;
    }
}
