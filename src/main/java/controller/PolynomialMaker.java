package controller;

import model.Polynomial;

public class PolynomialMaker {
    private static int sign; //the sign of the factor/power
    private static float factor;
    private static int power;
    private static boolean isFloat;
    private static int floatPower; //how many digits we need to shift the float to the right by
    private static boolean beforeX;
    private static boolean isFirst;
    private static int factorMag; //the magnitude of the factor (used in case the factor is 0)
    private static int powerMag; //the magnitude of the power (used in case the power is 0)
    private static boolean hasPower; //if this is false it means power is 1
    public static Polynomial makePoly(String inputString){
        //turn the string into an actual polynomial
        //make sure to check the string is correct before calling this
        Polynomial poly=new Polynomial();

        inputString=inputString.toLowerCase();
        inputString=inputString.replaceAll("\\s",""); //remove whitespace
        inputString="+"+inputString+"+"; //pad with '+'
        char[] input=inputString.toCharArray();

        //initialize everything
        sign=1;
        power=0;
        factor=1f;
        isFirst=true;
        isFloat=false;
        floatPower=1;
        factorMag=1; //the magnitudes are required in case the user explicitly declares factor or power '0'
        powerMag=1;
        beforeX=true;
        hasPower=false;

        for(int i=1;i< input.length-1;i++){
            //go character by character
            char k=input[i]; //shorthand current char as k
            switch (k){
                case '-':
                    if("x".contains(input[i-1]+""))
                        addMonomial(poly);
                    else if((!"^+-".contains(input[i-1]+"") || !hasPower) && (!isFirst)) //check to make sure this sign is between two monomials
                        addMonomial(poly);
                    sign*=-1;
                    break;
                case '+':
                    if("x".contains(input[i-1]+""))
                        addMonomial(poly);
                    else if((!"^+-".contains(input[i-1]+"") || !hasPower) && (!isFirst)) //check to make sure this sign is between two monomials
                        addMonomial(poly);
                    break;
                case '.':
                    isFloat=true;
                    break;
                case 'x':
                    if(factor!=1) //factor==1 means factor wasn't specified, so it's 1 by default
                        factor-=factorMag;
                    factor*=sign;
                    factor/=floatPower; //shift digits to the right
                    sign=1;
                    beforeX=false;
                    power=1;
                    break;
                case '0','1','2','3','4','5','6','7','8','9':
                    if(isFirst){
                        if(input[i+1]=='+' || input[i+1]=='-'){
                            //if a digit is followed by an addition or subtraction
                            //then it means we're at the end of the first monomial
                            isFirst=false;
                        }
                    }
                    if(beforeX){
                        //we do the factor here
                        if(isFloat){
                            floatPower*=10;
                        }
                        factor=factor*10+(k-'0');
                        factorMag*=10;
                    }else {
                        //we do the power here
                        power=power*10+(k-'0');
                        powerMag*=10;
                    }
                    break;
                case '^':
                    hasPower=true;
                    break;
                default:
                    break;
            }

        }
        addMonomial(poly); //add the last monomial
        return poly;
    }

    private static void addMonomial(Polynomial poly){
        //the previous monomial has ended, and we can add it to the polynomial
        //then reset everything

        if(beforeX){
            //we didn't get to x and reached the end of the monomial
            //so just a constant is being added
            factor-=factorMag;
            factor*=sign;
            factor/=floatPower;
        }else {
            //a regular monomial
            if(power!=1) //power==1 means power wasn't specified, so it's 1 by default
                power-=powerMag;
            power*=sign;
        }
        poly.addMonomial(factor,power);
        //System.out.println(factor + " | " + power);


        sign=1;
        power=0;
        factor=1f;
        isFirst=false;
        isFloat=false;
        floatPower=1;
        beforeX=true;
        factorMag=1;
        powerMag=1;
        hasPower=false;
    }
}
