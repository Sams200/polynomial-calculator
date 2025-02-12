package model;

import java.util.*;

public class Polynomial {
    /*
    A polynomial is a set of monomials
    There is at most one monomial for each power
    */
    private final HashMap<Integer,Monomial> map;
    private int degree;
    //compares monomials by their powers. Used to return the reverse sorted list
    private static final Comparator<Monomial> comparator= (o1, o2) -> {
        if(o1.getPower()<o2.getPower())
            return 1;
        if(o1.getPower()==o2.getPower())
            return 0;
        if(o1.getPower()>o2.getPower())
            return -1;
        return 0;
    };
    public Polynomial(){
        this.map=new HashMap<>(100);
        this.degree=0;
    }
    public Polynomial(HashMap<Integer,Monomial> map,int degree){
        //copy constructor
        this.map=map;
        this.degree=degree;

    }
    public Polynomial(Monomial a){
        //make a polynomial with just a monomial
        this.map=new HashMap<>();
        this.degree=a.getPower();
        this.map.put(a.getPower(),a);
    }
    ///////////////////////////////////////////////

    public int getDegree() {
        return degree;
    }
    public HashMap<Integer, Monomial> getMap() {
        return map;
    }

    public String toString(){
        StringBuilder result= new StringBuilder();
        //get monomials and sort in reverse
        List<Monomial> collection= new ArrayList<>(this.map.values());
        collection.sort(Polynomial.comparator);

        boolean first=true;
        boolean displayedSomething=false;
        for (Monomial monomial:collection) {
            if(monomial.getFactor()==0)
                continue;
            if(!first) {
                if(monomial.getFactor()<0)
                    result.append(" - ");
                else
                    result.append(" + ");
            }
            else {
                if(monomial.getFactor()<0)
                    result.append("-");
                first=false;
            }
            displayedSomething=true;
            result.append(monomial);
        }
        if(!displayedSomething)
            return "0";
        return result.toString();
    }
    public void addMonomial(Monomial x){
        //add a monomial to the polynomial
        //first, look if that power exists in the hashmap
        //if it doesn't, simply put the monomial in the hashmap
        //if it does, then add the two monomials together
        if(x.getPower()>this.degree)
            this.degree=x.getPower();
        Monomial temp=map.get(x.getPower());
        if(temp==null){
            this.map.put(x.getPower(),x);
        }else {
            this.map.remove(x.getPower());
            temp=Monomial.addMonomial(temp,x);
            assert temp != null;
            this.map.put(temp.getPower(),temp);
        }
    }
    public void addMonomial(float factor,int power){
        this.addMonomial(new Monomial(factor,power));
        if(this.degree<power)
            this.degree=power;
    }
    public void subMonomial(Monomial y){
        //sub a monomial from the polynomial
        //first, look if that power exists in the hashmap
        //if it doesn't, simply put the monomial in the hashmap
        //if it does, then add the two monomials together
        Monomial temp=this.map.get(y.getPower());
        if(temp==null){
            y.setFactor(y.getFactor()*(-1));
            this.map.put(y.getPower(),y);
        }else {
            this.map.remove(y.getPower());
            Monomial subRes=Monomial.subMonomial(temp,y);

            assert subRes != null;
            if(subRes.getFactor()!=0) {
                this.map.put(subRes.getPower(),subRes);
            }
            else {
                //recalculate degree
                if(!this.map.isEmpty())
                    this.degree=Collections.max(this.map.keySet());
                else
                    this.degree=0;
            }

        }
    }

    public void addPoly(Polynomial y){
        //add a polynomial to the polynomial
        //just add all the monomials together
        Collection<Monomial> yCol=y.getMap().values();
        for (Monomial m: yCol) {
            this.addMonomial(m);
            //if the factor is 0 remove it from the hashmap
            if(this.map.get(m.getPower()).getFactor()==0)
                this.map.remove(m.getPower());
        }
    }
    public void subPoly(Polynomial y){
        //subtract a polynomial from the polynomial
        //just subtract every monomial
        Collection<Monomial> yCol=y.getMap().values();
        for (Monomial m: yCol) {
            this.subMonomial(m);
        }
    }
    public Polynomial mulPoly(Polynomial y){
        //multiply each X monomial with every other monomial from Y
        //and add everything to the result
        Collection<Monomial> xCol=this.map.values();
        Collection<Monomial> yCol=y.getMap().values();
        Polynomial result=new Polynomial();

        for (Monomial xMon:xCol) {
            for(Monomial yMon:yCol){
                result.addMonomial(Monomial.mulMonomial(xMon,yMon));
            }
        }
        return result;
    }
    public AbstractMap.SimpleEntry<Polynomial,Polynomial> divPoly(Polynomial y) {
        /*
        Use long division
        First, order the two polynomials in descending order by the powers
        Take the highest degree monomial from the second polynomial, well call it q
        Go through the monomials of the first polynomial - p
        Divide p by q, obtaining r. Add r to the result
        Multiply r by q and subtract it from the first polynomial
        Repeat until the degree of the first polynomial is smaller than the degree of the second polynomial
         */

        Polynomial quotient=new Polynomial(new Monomial(0,0));

        if(y.getDegree()>this.degree){
            //can't do the division, return 0
            quotient.addMonomial(new Monomial(0,0));
            return new AbstractMap.SimpleEntry<>(quotient,y);
        }


        List<Monomial> listY= new ArrayList<>(y.getMap().values());
        listY.sort(Polynomial.comparator);
        Monomial q=listY.get(0); //the largest monomial in y

        Polynomial copyX= new Polynomial(this.map,this.degree);
        List<Monomial> listX= new ArrayList<>(copyX.map.values());
        listX.sort(Polynomial.comparator);

        for(Monomial i:listX){
            Monomial p=copyX.map.get(i.getPower());
            if(p==null)
                continue;

            Monomial r=Monomial.divMonomial(p,q);
            quotient.addMonomial(r);
            Polynomial s=y.mulPoly(new Polynomial(r));
            copyX.subPoly(s);
            if(copyX.getDegree()<y.getDegree())
                break;

        }

        //this is just a pair of two objects because there isn't such a thing in the default java sdk
        return new AbstractMap.SimpleEntry<>(quotient,copyX);
    }
    public Polynomial diffPoly(){
        //differentiate every monomial
        Polynomial result=new Polynomial();
        List<Monomial> listX= new ArrayList<>(this.map.values());

        for (Monomial m:listX){
            result.addMonomial(Monomial.diffMonomial(m));
        }
        return result;
    }

    public Polynomial intPoly(){
        //integrate every monomial
        Polynomial result=new Polynomial();
        List<Monomial> listX= new ArrayList<>(this.map.values());

        for (Monomial m:listX){
            result.addMonomial(Monomial.intMonomial(m));
        }
        return result;
    }
}
