
import java.io.*;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static boolean isNumeric(char strNum) {
        try {
            double d = Double.parseDouble(String.valueOf(strNum));
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private static int getNumOfStrings(String fileName) {
        int result = 0;
        try
                (FileReader input = new FileReader(fileName);
                 LineNumberReader count = new LineNumberReader(input);) {
            while (count.skip(Long.MAX_VALUE) > 0) {
            }
            result = count.getLineNumber() + 1;                                    // +1 because line index starts at 0
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String[] getStrings(String fileName, int numOfStrings) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        String[] getStrings = new String[numOfStrings];

        for (int i = 0; i < numOfStrings; i++) {
            getStrings[i] = scanner.nextLine();
        }
        scanner.close();
        return getStrings;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int numOfStrings = getNumOfStrings("test");
        String[] stringsOfInput = getStrings("test", numOfStrings);
        boolean longComment =false;

        // определение лексем
        for(int i=0; i<numOfStrings; i++){
            stringsOfInput[i] = Sign.check(stringsOfInput[i]);
            stringsOfInput[i] = Keyword.check(stringsOfInput[i]);
            stringsOfInput[i] = Number.check(stringsOfInput[i]);
        }


        // определение коментариев
        for(int i=0; i<numOfStrings; i++) {
            if(longComment){
                 stringsOfInput[i] = 'Ф' + stringsOfInput[i];
            }
            for(int j=0;j<stringsOfInput[i].length() - 1;j++){
                if(stringsOfInput[i].charAt(j)=='/' && (stringsOfInput[i].charAt(j+1)=='*'))
                {
                    longComment =true;
                    stringsOfInput[i] =
                    stringsOfInput[i].substring(0, j-1) + 'Ф' +
                    stringsOfInput[i].substring(j, stringsOfInput[i].length() - 1 );
                }
                if(stringsOfInput[i].charAt(j)=='/' && (stringsOfInput[i].charAt(j+1)=='/'))
                {
                    stringsOfInput[i] =
                            stringsOfInput[i].substring(0, j-1) + 'Ф' +
                                    stringsOfInput[i].substring(j);
                }

                if(stringsOfInput[i].charAt(j)=='*' && (stringsOfInput[i].charAt(j+1)=='/'))
                {
                    longComment =false;
                    if(stringsOfInput[i].charAt(stringsOfInput[i].length() - 1)!='/'){
                        stringsOfInput[i] =
                                stringsOfInput[i].substring(0, j+2) + 'Ф'
                                        + stringsOfInput[i].substring(j+3);
                    }
                    else stringsOfInput[i] =
                            stringsOfInput[i].substring(0, j+2) + 'Ф';
                }
            }
        }

        // вывод
        for (String oneString : stringsOfInput) {
        for(int i=0; i<oneString.length(); i++){
            if(oneString.charAt(i)!='Й' && oneString.charAt(i)!='З' && oneString.charAt(i)!='Ч'
                    && oneString.charAt(i)!='Ф'){
                System.out.print(oneString.charAt(i));
            }
            if(oneString.charAt(i)=='Й'){
                i++;
                    while (i<=oneString.length() - 1 && oneString.charAt(i)!='Й' )
                    {
                            System.out.print(ANSI_PURPLE + oneString.charAt(i) + ANSI_RESET);
                        i++;
                    }
            }
            if(oneString.charAt(i)=='З'){
                i++;
                while (i<=oneString.length() - 1 && oneString.charAt(i)!='З' )
                {
                    System.out.print(ANSI_YELLOW + oneString.charAt(i) + ANSI_RESET);
                    i++;
                }
            }
            if(oneString.charAt(i)=='Ч'){
                i++;

                while (i<=oneString.length() - 1 && oneString.charAt(i)!='Ч' )
                {
                    System.out.print(ANSI_RED + oneString.charAt(i) + ANSI_RESET);
                    i++;
                }
            }

            if(oneString.charAt(i)=='Ф'){

                i++;
                while ((i<=oneString.length() - 1 && oneString.charAt(i)!='Ф'))
                {
                        if(oneString.charAt(i)!='Ч' && oneString.charAt(i)!='Й' && oneString.charAt(i)!='З' ){
                            System.out.print(ANSI_BLUE + oneString.charAt(i) + ANSI_RESET);
                        }
                        i++;

                }
            }
        }
            System.out.print("\n");

        }
    }
}
