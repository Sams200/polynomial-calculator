package model;

import java.text.DecimalFormat;

public class Monomial {
    /*
    A simple monomial
    ex:
    1. 25x^3
    2. x^2
    3. 5x
    4. 7

    There should be only one monomial with power x in a polynomial
     */
    private float factor;
    private final int power;
    private static final DecimalFormat dF=new DecimalFormat("#.##"); //only show two digits after the '.' if they are needed
    ///////////////////////////////////////////////////
    public Monomial(float factor, int power) {
        this.factor = factor;
        this.power = power;
    }
    public Monomial(int power) {
        this.factor = 0;
        this.power = power;
    }
    ///////////////////////////////////////////////////
    public float getFactor() {
        return factor;
    }
    public void setFactor(float factor) {
        this.factor = factor;
    }
    public int getPower() {
        return power;
    }
    ////////////////////////////////////////////

    //the monomial operations are trivial to implement
    public static Monomial addMonomial(Monomial a, Monomial b){
        //add two monomials
        if(a.getPower()!=b.getPower()){
            System.out.println("Can't add monomials of different powers!");
            return null;
        }
        return new Monomial(a.getFactor()+b.getFactor(),a.getPower());
    }
    public static Monomial subMonomial(Monomial a, Monomial b){
        //subtract two monomials
        if(a.getPower()!=b.getPower()){
            System.out.println("Can't add monomials of different powers!");
            return null;
        }
        return new Monomial(a.factor-b.factor,a.getPower());
    }
    public static Monomial mulMonomial(Monomial a, Monomial b){
        //multiply two monomials
        return new Monomial(a.getFactor()*b.getFactor(),a.getPower()+b.getPower());
    }
    public static Monomial divMonomial(Monomial a, Monomial b){
        //divide two monomials
        return new Monomial(a.getFactor()/b.getFactor(),a.getPower()-b.getPower());
    }
    public static Monomial diffMonomial(Monomial a){
        //differentiate a monomial
        if(a.power!=0)
            return new Monomial(a.getFactor()*a.getPower(),a.getPower()-1);
        return new Monomial(0,0);
    }
    public static Monomial intMonomial(Monomial a){
        return new Monomial(a.getFactor()/(a.getPower()+1),a.getPower()+1);
    }
    //////////////////////////////////////////
    public String toString(){

        if(this.factor==0 && this.power==Integer.MIN_VALUE)
            return "C"; //I didn't end up using this in the end
        if(this.factor==0)
            return "0";

        StringBuilder result= new StringBuilder();


        if(Math.abs(this.factor)!=1 || this.power==0) {
            result.append(dF.format(Math.abs(this.factor)));
        }
        if(this.power!=0) {
            result.append("x");
            if (this.power != 1){
                result.append("^");
                result.append(this.power);
            }
        }

        return result.toString();
    }
}
