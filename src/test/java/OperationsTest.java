import controller.PolynomialMaker;
import model.Polynomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;


public class OperationsTest {
    @ParameterizedTest
    @MethodSource("inputAdd")
    public void addTest(String firstS, String secondS, String expected){
        Polynomial first=PolynomialMaker.makePoly(firstS);
        Polynomial second=PolynomialMaker.makePoly(secondS);
        first.addPoly(second);
        String result=first.toString();

        result=result.replaceAll("\\s",""); //remove whitespace
        result=result.toLowerCase();
        expected=expected.replaceAll("\\s",""); //remove whitespace
        expected=expected.toLowerCase();

        Assertions.assertEquals(expected, result);
    }

    public static List<Arguments> inputAdd(){
        List<Arguments> arguments=new ArrayList<>();

        String a1=("2X^3 - 5X^2 + 3X - 7");
        String b1=("3X^4 - X^3 + 2X^2 - 5X + 1");
        String r1=("3x^4 + x^3 - 3x^2 - 2x - 6");


        String a2=("x^4 + 2x^3 - x^2 - 5x + 6");
        String b2=("4x^3 - 2x^2 + 5x - 3");
        String r2=("x^4 + 6x^3 - 3x^2 + 3");

        String a3=("-2x^2+1");
        String b3=("2x^2-1");
        String r3=("0");

        String a4=("2x^2 - 3x + 1");
        String b4=("-2x^2 + 3x - 1");
        String r4=("0");

        String a5=("x^3 - 2x^2 + x - 5");
        String b5=("-x^3 + 2x^2 - x + 5");
        String r5=("0");

        arguments.add(Arguments.of(a1,b1,r1));
        arguments.add(Arguments.of(a2,b2,r2));
        arguments.add(Arguments.of(a3,b3,r3));
        arguments.add(Arguments.of(a4,b4,r4));
        arguments.add(Arguments.of(a5,b5,r5));
        return arguments;
    }
    ////////////////////////////////////////////////////////////////////////////
    @ParameterizedTest
    @MethodSource("inputSub")
    public void subTest(String firstS, String secondS, String expected){
        Polynomial first=PolynomialMaker.makePoly(firstS);
        Polynomial second=PolynomialMaker.makePoly(secondS);
        first.subPoly(second);
        String result=first.toString();

        result=result.replaceAll("\\s",""); //remove whitespace
        result=result.toLowerCase();
        expected=expected.replaceAll("\\s",""); //remove whitespace
        expected=expected.toLowerCase();

        Assertions.assertEquals(expected, result);
    }

    public static List<Arguments> inputSub(){
        List<Arguments> arguments=new ArrayList<>();

        String a1=("2X^3 - 5X^2 + 3X - 7");
        String b1=("3X^4 - X^3 + 2X^2 - 5X + 1");
        String r1=("-3x^4 + 3x^3 - 7x^2 + 8x - 8");


        String a2=("X^4 + 2X^3 - X^2 - 5X + 6");
        String b2=("X^3 - 2X^2 + 5X - 3");
        String r2=("x^4 + x^3 + x^2 - 10x + 9");

        String a3=("3X^2 + 4X + 1");
        String b3=("X^5 - 3X^4 + 2X^3 - X^2 + 6X - 4");
        String r3=("-x^5 + 3x^4 - 2x^3 + 4x^2 - 2x + 5");

        String a4=("2X^2 - 3X + 1");
        String b4=("2X^2 - 3X + 1");
        String r4=("0");

        String a5=("X^3 - 2X^2 + X - 5");
        String b5=("X^3 + 2X^2 - X + 5");
        String r5=("-4x^2 + 2x - 10");

        arguments.add(Arguments.of(a1,b1,r1));
        arguments.add(Arguments.of(a2,b2,r2));
        arguments.add(Arguments.of(a3,b3,r3));
        arguments.add(Arguments.of(a4,b4,r4));
        arguments.add(Arguments.of(a5,b5,r5));
        return arguments;
    }
    ////////////////////////////////////////////////////////////////////////////
    @ParameterizedTest
    @MethodSource("inputMul")
    public void mulTest(String firstS, String secondS, String expected){
        Polynomial first=PolynomialMaker.makePoly(firstS);
        Polynomial second=PolynomialMaker.makePoly(secondS);
        Polynomial resultP=first.mulPoly(second);
        String result=resultP.toString();

        result=result.replaceAll("\\s",""); //remove whitespace
        result=result.toLowerCase();
        expected=expected.replaceAll("\\s",""); //remove whitespace
        expected=expected.toLowerCase();

        Assertions.assertEquals(expected, result);
    }

    public static List<Arguments> inputMul(){
        List<Arguments> arguments=new ArrayList<>();

        String a1=("6x");
        String b1=("2x+3");
        String r1=("12x^2+18x");


        String a2=("7");
        String b2=("-5x-8");
        String r2=("-35x-56");

        String a3=("4x-1");
        String b3=("4x-1");
        String r3=("16x^2-8x+1");

        String a4=("x^2-7x-6");
        String b4=("7x^2-3x-7");
        String r4=("7x^4-52x^3-28x^2+67x+42");

        String a5=("x^2+6x-4");
        String b5=("0");
        String r5=("0");

        arguments.add(Arguments.of(a1,b1,r1));
        arguments.add(Arguments.of(a2,b2,r2));
        arguments.add(Arguments.of(a3,b3,r3));
        arguments.add(Arguments.of(a4,b4,r4));
        arguments.add(Arguments.of(a5,b5,r5));
        return arguments;
    }
    ////////////////////////////////////////////////////////////////////////////
    @ParameterizedTest
    @MethodSource("inputDiv")
    public void divTest(String firstS, String secondS, String expected){
        Polynomial first=PolynomialMaker.makePoly(firstS);
        Polynomial second=PolynomialMaker.makePoly(secondS);
        Polynomial resultP=first.divPoly(second).getKey();
        String result=resultP.toString();

        result=result.replaceAll("\\s",""); //remove whitespace
        result=result.toLowerCase();
        expected=expected.replaceAll("\\s",""); //remove whitespace
        expected=expected.toLowerCase();

        Assertions.assertEquals(expected, result);
    }

    public static List<Arguments> inputDiv(){
        List<Arguments> arguments=new ArrayList<>();

        String a1=("12x^2+18x");
        String b1=("2x+3");
        String r1=("6x");


        String a2=("-35x-56");
        String b2=("-5x-8");
        String r2=("7");

        String a3=("16x^2-8x+1");
        String b3=("4x-1");
        String r3=("4x-1");

        String a4=("7x^4-52x^3-28x^2+67x+42"); //
        String b4=("7x^2-3x-7");
        String r4=("x^2-7x-6");

        String a5=("0");
        String b5=("x^2+6x-4");
        String r5=("0");

        arguments.add(Arguments.of(a1,b1,r1));
        arguments.add(Arguments.of(a2,b2,r2));
        arguments.add(Arguments.of(a3,b3,r3));
        arguments.add(Arguments.of(a4,b4,r4));
        arguments.add(Arguments.of(a5,b5,r5));
        return arguments;
    }
    ////////////////////////////////////////////////////////////////////////////
    @ParameterizedTest
    @MethodSource("inputDiff")
    public void diffTest(String firstS, String expected){
        Polynomial first=PolynomialMaker.makePoly(firstS);
        Polynomial resultP=first.diffPoly();
        String result=resultP.toString();

        result=result.replaceAll("\\s",""); //remove whitespace
        result=result.toLowerCase();
        expected=expected.replaceAll("\\s",""); //remove whitespace
        expected=expected.toLowerCase();

        Assertions.assertEquals(expected, result);
    }

    public static List<Arguments> inputDiff(){
        List<Arguments> arguments=new ArrayList<>();

        String a1=("12x^2+18x");
        String r1=("24x+18");

        String a2=("-35");
        String r2=("0");

        String a3=("16x^3+1");
        String r3=("48x^2");

        String a4=("5x3+5x^2+5x+5"); //
        String r4=("15x^2+10x+5");

        String a5=("0");
        String r5=("0");

        arguments.add(Arguments.of(a1,r1));
        arguments.add(Arguments.of(a2,r2));
        arguments.add(Arguments.of(a3,r3));
        arguments.add(Arguments.of(a4,r4));
        arguments.add(Arguments.of(a5,r5));
        return arguments;
    }
    ////////////////////////////////////////////////////////////////////////////
    @ParameterizedTest
    @MethodSource("inputInt")
    public void intTest(String firstS, String expected){
        Polynomial first=PolynomialMaker.makePoly(firstS);
        Polynomial resultP=first.intPoly();
        String result=resultP.toString();

        result=result.replaceAll("\\s",""); //remove whitespace
        result=result.toLowerCase();
        expected=expected.replaceAll("\\s",""); //remove whitespace
        expected=expected.toLowerCase();

        Assertions.assertEquals(expected, result);
    }

    public static List<Arguments> inputInt(){
        List<Arguments> arguments=new ArrayList<>();

        String a1=("24x+18"); //
        String r1=("12x^2+18x");

        String a2=("0");
        String r2=("0");

        String a3=("48x^2");
        String r3=("16x^3");

        String a4=("15x^2+10x+5"); //
        String r4=("5x^3+5x^2+5x");

        String a5=("-5x^4+3x^2-1");
        String r5=("-x^5+x^3-x");

        arguments.add(Arguments.of(a1,r1));
        arguments.add(Arguments.of(a2,r2));
        arguments.add(Arguments.of(a3,r3));
        arguments.add(Arguments.of(a4,r4));
        arguments.add(Arguments.of(a5,r5));
        return arguments;
    }
    ////////////////////////////////////////////////////////////////////////////
}
