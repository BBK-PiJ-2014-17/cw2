/**
 * Created by Basil on 06/11/2014.
 * FractionCalculator Tester class
 */
public class FractionCalculatorTest {

    public static void main(String[] args) {

        // create fraction calculator object

        FractionCalculator fc = new FractionCalculator();
        System.out.println();
        System.out.println("*****************************************");
        System.out.println("*****FRACTION CALCULATOR TEST MODULE*****");
        System.out.println("*****************************************");
        System.out.println();
        System.out.println( "Welcome to the Fraction Calculator\n"
                        +   "test module. A few tests will be\n"
                        +   "carried out to validate the operations\n"
                        +   "of the Fraction Calculator. Then a\n"
                        +   "prompt will be offered to enter further\n"
                        +   "sequences of commands.\n");

        System.out.println("Starting tests...");

        // print current state of Fraction Calculator

        System.out.println(fc.toString());

        //

        //fc.evaluate(new Fraction(0,1), "2");
        System.out.println(fc.evaluate(new Fraction(0,1), "2/3 + 4/3").toString());

    }

}
