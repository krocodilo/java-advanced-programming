package pt.isec.pa.apoio_poe.utils;

import java.util.*;

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
    public static int readOption(String prompt, int lowest, int highest){
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
            System.out.println("\nYou have not entered a valid number!");
        }
        return option;
    }

    //print the prompt to the  screen and read a long number bigger than zero
    public static long readLong(String prompt){

        long num;
        while( true ){
            try{
                System.out.print(prompt + ":  ");
                num = Long.parseLong( sin.nextLine());
                if(num > 0)
                    break;
            } catch (NumberFormatException ignored) { }
            System.out.println("\nYou have not entered a valid number!");
        }
        return num;
    }

    //print the prompt to the  screen and read a long number bigger than zero
    public static double readDouble(String prompt){

        double num;
        while( true ){
            try{
                System.out.print(prompt + ":  ");
                num = Double.parseDouble( sin.nextLine() );
                if(num > 0)
                    break;
            } catch (NumberFormatException ignored) { }
            System.out.println("\nYou have not entered a valid number!");
        }
        return num;
    }

    //displays the text and asks for the user to insert a string with length between min and max
    public static String prompt(String text){
        // Show message to user and wait for a response that has
        // between min and max number of characters.
        // It displays the constraint information after the given string

        String resp;
        do {
            System.out.print(text + ":  ");
            resp = sin.nextLine();
        } while ( resp.isBlank() );
        return resp;
    }

    public static boolean readBoolean(String prompt){

        while( true ){
            String resp = prompt( prompt + " (y/n)" );
            if( resp.equalsIgnoreCase("y") )
                return true;
            else if( resp.equalsIgnoreCase("n") )
                return false;
        }
    }

    /*public static String readCertainString(String prompt, String... possibilities){

        if(Arrays.stream(possibilities).toList().isEmpty())
            return prompt( prompt );

        while( true ){
            String resp = prompt( prompt );
            for(String option : possibilities)
                if( resp.equalsIgnoreCase(option) )
                    return resp;
        }
    }*/

    public static <E> void showList(List<E> list){

        String template = " [%d] - %s\n";

        System.out.println();

        for(int i = 0; i < list.size(); i++)
            System.out.printf( template, i, list.get(i).toString() );
        System.out.println("\nPress ENTER to continue...");
        sin.nextLine();
    }

    public static <E> E selectOneFrom(List<E> list) {
        showList( list );
        return list.get(
                readOption("Select the index from the list: ", 0, list.size())
        );
    }
}
