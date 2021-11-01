class Number {


    private static String[] fillRegex(){
        String[] regex = new String[10];
        for(int i = 0; i<10; i++){
            regex[i] = Integer.toString(i);
        }
        return regex;
    }

    private static String[] fillReplacement(){
        String[] replacement = new String[10];
        for(int i = 0; i<10; i++){
            replacement[i] = "Ч" + i + "Ч";
        }
        return replacement;
    }

    static String check(String checkString){
        for (int i = 0; i <10 ; i++) {
            checkString = checkString.replaceAll(fillRegex()[i], fillReplacement()[i]);
        }
        return checkString;
    }

}
