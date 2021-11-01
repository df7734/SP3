public class Sign {
    private static int length = fillRegex().length;

        private static String[] fillRegex(){
            return new String[]{"[(]", "[)]", "=", "-", "\\+", "\\{", "}",
                    "\\[", "]", ",", "\\.", "%", "\""};
        }

        private static String[] fillReplacement(){
            return new String[]{"З(З", "З)З", "З=З", "З-З", "З+З", "З{З", "З}З",
                    "З[З", "З]З", "З,З", "З.З", "З%З", "З\"З"};
        }

        static String check(String checkString){
            for (int i = 0; i <length ; i++) {
                checkString = checkString.replaceAll(fillRegex()[i], fillReplacement()[i]);
            }
            return checkString;
        }
}
