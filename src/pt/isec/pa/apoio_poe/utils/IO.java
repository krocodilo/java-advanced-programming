package pt.isec.pa.apoio_poe.utils;

import java.util.Scanner;

public class IO {

    private static final Scanner sin = new Scanner( System.in );
    private static final String menuSeparator = "//////////////////////////////////////////////////";
    private static final String stdPrompt = ">  ";

    public static void printMenu(String title, String... options){
        StringBuilder str = new StringBuilder();
        str.append("\n" + menuSeparator + "\n\n");
        str.append("\t\t").append( title ).append("\n\n");

        for(String option : options)
            str.append("\t").append(option).append("\n");

        str.append("\n" + menuSeparator + "\n");
        System.out.println(str);
    }


    //print the prompt to the  screen and read a number between lowest and highest
    public static int readNumber(String prompt, int lowest, int highest){
        // read a number between the lowest and highest possible (inclusive)

        int option;
        if(prompt == null)  prompt = stdPrompt;
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
