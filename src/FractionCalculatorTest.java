/**
 * Created by Basil on 06/11/2014.
 * FractionCalculator Tester class
 */
public class FractionCalculatorTest {

    // create fraction calculator object

    static FractionCalculator fc = new FractionCalculator();

    public static void main(String[] args) {

        String str; //  input string for testing
        Fraction res;   //  expected test result

        // introduce test module

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

        // test addition

        str = "2/3 + 4/3";  //  = 2/1
        res = new Fraction(2,1);

        //testOperation(str, res);

        // test subtraction

        str = "2/3 - 4/7";  //  = 2/21
        res = new Fraction(2,21);

        //testOperation(str, res);

        // test multiplication

        str = "2/3 * 1/4";  //  = 1/6
        res = new Fraction(1,6);

        //testOperation(str, res);

        // test division

        str = "5/8 / 1/3";  //  = 15/8
        res = new Fraction(15,8);

        //testOperation(str, res);

        // test absolute value

        str = "-5/8 a";  //  = 5/8
        res = new Fraction(5,8);

        //testOperation(str, res);

        // test absolute value

        str = "5/8 n";  //  = -5/8
        res = new Fraction(-5,8);

        testOperation(str, res);

    }

    static private void testOperation(String s, Fraction r) {

        System.out.println("Performing Operation:\t\t" + s);
        System.out.println("Expected Result:\t\t" + r);
        System.out.println();
        System.out.println("Equal: " + r.equals(fc.evaluate(new Fraction(0,1), s)));
    }

}
