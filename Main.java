package lab5;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        CommandDispatcher cm = new CommandDispatcher(catalog);

        Scanner s = new Scanner(System.in);
        // read till EOF
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] cArgs = line.split(" ");
            List<String> commandArgs = Arrays.asList(cArgs);
            try {
                cm.dispatch(commandArgs);
            } catch (InvalidArgsException e) {
                e.printStackTrace();
            }
        }

    }
}