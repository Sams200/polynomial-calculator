package controller;

class PolynomialFormatter {
    //this class is not allowed outside of the package
    static String formatPoly(String input, int polyNumber){
        //check for any errors and return a string
        //which is null for correct input, or contains the error message
        //in case of wrong input


        //just as a heads-up I have no formal training in using regex
        input=input.replaceAll("\\s",""); //remove whitespace
        input=input.toLowerCase();

        String result;
        int errorCode=0;
        //allowed chars are 'x' '+' '-' '^' '*' digits

        //empty input
        if(input.isEmpty() || input.isBlank()){
            errorCode=1;
        } else
        //check allowed chars
        if(input.matches("(.*)[^.x^*\\d+\\-](.*)")){
            errorCode=2;
        } else {

            input = "+" + input; //pad the input with a symbol which doesn't affect it

            //double character
            if (input.matches("(.*)(x{2,}|\\^{2,}|\\*{2,}|\\.{2,})(.*)")) {
                errorCode = 3;
            } else
            // '*' can only be preceded by a digit and succeeded by x
            if (input.matches("(.*)((\\D\\*)|(\\*[^x]))(.*)")) {
                errorCode = 4;
            } else
            // '^' can only be preceded by x and succeeded by digits and + -
            if (input.matches("(.*)(([^x]\\^)|(\\^[^\\d+-])|(\\^\\d+[^+\\-\\d])|(\\^[+-]+[^\\d+-]))(.*)")) {
                errorCode = 5;
            } else
            // can't have '+' '-' '^' '.' at the end of the input
            if (input.matches("(.*)[+*\\-^.]$")) {
                errorCode = 6;
            } else
            // can't have '^' '.' at the beginning of the input
            if (input.matches("^[\\^.](.*)")) {
                errorCode = 6;
            } else
            // x succeeded by a digit or '.'
            if (input.matches("(.*)x[\\d.](.*)")){
                errorCode = 7;
            } else
            // '.' can be followed only by digits
            if (input.matches("(.*)\\.\\D(.*)")){
                errorCode = 8;
            } else
            // no negative powers allowed
            if(input.contains("^-")){
                errorCode = 9;
            }
        }
        if(errorCode==0)
            return null;
        result="Polynomial " + polyNumber + ". Invalid Input - ";
        result += switch (errorCode) {
            case 1 -> ("Empty Input");
            case 2 -> ("Invalid Characters");
            case 3 -> ("Incompatible Successive Symbols");
            case 4 -> ("'*' can only be preceded by a digit and succeeded by x");
            case 5 -> ("Invalid power");
            case 6 -> ("Invalid expression");
            case 7 -> ("Missing '^'");
            case 8 -> ("'.' can be followed only by digits");
            case 9 -> ("No negative powers allowed");
            default -> ("Other");
        };
        return result;

    }
}
