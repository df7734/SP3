public class Keyword {

        private static int length = fillRegex().length;

        private static String[] fillRegex(){
            return new String[]{"class", "public", "static", "void", "double", "String"};
        }

        private static String[] fillReplacement(){
            return new String[]{"ЙclassЙ", "ЙpublicЙ", "ЙstaticЙ", "ЙvoidЙ", "ЙdoubleЙ", "ЙStringЙ"};
        }

        static String check(String checkString){
            for (int i = 0; i <length ; i++) {
                checkString = checkString.replaceAll(fillRegex()[i], fillReplacement()[i]);
            }
            return checkString;
        }
}

