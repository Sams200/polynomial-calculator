package controller;

import model.Polynomial;
import view.PageView;

import java.awt.*;
import java.io.File;
import java.util.AbstractMap;

public class PageController {
    private PageView view;
    private Operation operation;

    public PageController(){
        operation=Operation.ADD;
    }

    public void setView(PageView view){
        this.view=view;
    }

    public void openDocumentation(){
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("./DOCUMENTATION.docx"));
            }
        } catch (Exception e) {
            //either file doesn't exist or we can't open for some reason
            //lets pretend nothing happened
            System.out.println("Error");
        }
    }

    public void pressedSwitch(){
        //switch the two polynomials between each other
        //System.out.println("Pressed Switch!");
        String pol1=view.getFieldFirstPoly();
        String pol2=view.getFieldThirdPoly();

        //work out the foreground color after the switch
        if (pol1.equals("First Polynomial")) {
            pol1="Second Polynomial";
            view.setThirdForeground(Color.GRAY);
        }
        else
            view.setThirdForeground(Color.WHITE);
        if (pol2.equals("Second Polynomial")){
            pol2="First Polynomial";
            view.setFirstForeground(Color.GRAY);
        }
        else
            view.setFirstForeground(Color.WHITE);


        view.setFieldFirstPoly(pol2);
        view.setFieldThirdPoly(pol1);
    }

    public void changedOperation(){
        int number=view.getCombo();
        this.operation=Operation.getInstance(number);
    }

    public void pressedCompute(){

        //first we validate the two inputs
        String pol1Txt= view.getFieldFirstPoly();
        String pol2Txt= view.getFieldThirdPoly();

        if(pol1Txt.equals("First Polynomial"))
            pol1Txt="";
        if(pol2Txt.equals("Second Polynomial"))
            pol2Txt="";

        String result1=PolynomialFormatter.formatPoly(pol1Txt,1);
        String result2=PolynomialFormatter.formatPoly(pol2Txt,2);

        boolean ok1=true;
        boolean ok2=true;

        //if the first input is good, then we make the polynomial
        Polynomial poly1=null;
        if(result1==null) {
            poly1=PolynomialMaker.makePoly(pol1Txt);
        } else {
            ok1=false;
        }

        //if the second input is good, then we make the polynomial
        Polynomial poly2=null;
        if(result2==null){
            poly2=PolynomialMaker.makePoly(pol2Txt);
        } else {
            ok2=false;
        }

        operation=Operation.getInstance(view.getCombo());

        //for add, subtract, multiply, and divide we need to know that both inputs are correct
        //for integrate and differentiate, only the first input has to be correct
        switch (operation){
            case ADD -> {
                if(!ok1){
                    view.setFieldResult(result1);
                    break;
                }
                if(!ok2){
                    view.setFieldResult(result2);
                    break;
                }
                poly1.addPoly(poly2);
                view.setFieldResult(poly1.toString());
            }
            case SUBTRACT -> {
                if(!ok1){
                    view.setFieldResult(result1);
                    break;
                }
                if(!ok2){
                    view.setFieldResult(result2);
                    break;
                }
                poly1.subPoly(poly2);
                view.setFieldResult(poly1.toString());
            }
            case MULTIPLY -> {
                if(!ok1){
                    view.setFieldResult(result1);
                    break;
                }
                if(!ok2){
                    view.setFieldResult(result2);
                    break;
                }
                poly1=poly1.mulPoly(poly2);
                view.setFieldResult(poly1.toString());
            }
            case DIVIDE -> {
                if(!ok1){
                    view.setFieldResult(result1);
                    break;
                }
                if(!ok2){
                    view.setFieldResult(result2);
                    break;
                }
                AbstractMap.SimpleEntry<Polynomial,Polynomial> result=poly1.divPoly(poly2);
                Polynomial quotient=result.getKey();
                Polynomial remainder=result.getValue();
                view.setFieldResult("Quotient: "+quotient.toString()+" || Remainder: "+remainder);
            }
            case INTEGRATE -> {
                if(!ok1){
                    view.setFieldResult(result1);
                    break;
                }
                poly2=poly1.intPoly();
                view.setFieldResult(poly2.toString()+" + C");
            }
            case DIFFERENTIATE -> {
                if(!ok1){
                    view.setFieldResult(result1);
                    break;
                }
                poly2=poly1.diffPoly();
                view.setFieldResult(poly2.toString());
            }

        }
    }

}
