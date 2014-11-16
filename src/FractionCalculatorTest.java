/**
 * Created by Basil on 06/11/2014.
 * FractionCalculator Tester class
 */

import java.util.Scanner;

public class FractionCalculatorTest {

    // create fraction calculator object

    static FractionCalculator fc = new FractionCalculator();    // remove static

    public static void main(String[] args) {

        String str;     // input string for testing
        Fraction res;   // expected test result

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

        System.out.println("Initial state of calculator:");
        System.out.println("\t" + fc.toString());
        System.out.println();

        // test addition

        System.out.println("\t***ADDITION***");
        str = "2/3 + 4/3";  //  = 2/1
        res = new Fraction(2,1);

        testOperation(str, res);

        // test subtraction

        System.out.println("\t***SUBTRACTION***");
        str = "2/3 - 4/7";  //  = 2/21
        res = new Fraction(2,21);

        testOperation(str, res);

        // test multiplication

        System.out.println("\t***MULTIPLICATION***");
        str = "2/3 * 1/4";  //  = 1/6
        res = new Fraction(1,6);

        testOperation(str, res);

        // test division

        System.out.println("\t***DIVISION***");
        str = "5/8 / 1/3";  //  = 15/8
        res = new Fraction(15,8);

        testOperation(str, res);

        // test absolute value

        System.out.println("\t***ABSOLUTE***");
        str = "-5/8 a";  //  = 5/8
        res = new Fraction(5,8);

        testOperation(str, res);

        // test absolute value

        System.out.println("\t***NEGATION***");
        str = "5/8 n";  //  = -5/8
        res = new Fraction(-5,8);

        testOperation(str, res);

        // test combinations

        System.out.println("\t***COMBINATION TEST 1***");
        str = "3/4 + 3/4 / 5 * 1/2 n - 1";  //  = -13/10
        res = new Fraction(23,-20);

        testOperation(str, res);

        System.out.println("\t***COMBINATION TEST 2***");
        str = "1/2 - 1 a * 2/1 * 100";  //  = 100
        res = new Fraction(100,1);

        testOperation(str, res);

        // test error handling

        System.out.println("\t***ERROR HANDLING***");
        res = new Fraction(0,1);

        str = "1/2 - - 2/1";        // double operation
        testOperation(str, res);
        str = "1/ + 3/4";           // invalid fraction
        testOperation(str, res);
        str = "1/5 z";              // unknown operation
        testOperation(str, res);
        str = "1/5 / 0";              // divide by zero
        testOperation(str, res);

        // tests for system commands...


        // scanner object for reading command line

        Scanner sc = new Scanner(System.in);
        String input;
        Boolean b = true;

        while (b) {

            input = sc.nextLine();

            if (input.equals("quit") || input.equals("q")) {
                b = false;
                break;
            }

            Fraction ret = fc.evaluate(new Fraction(0,1), input);
            System.out.println();
            System.out.println("\t>> Returned Result:\t" + ret);
            System.out.println();

        }


        System.out.println();
        System.out.println("EXITING PROGRAM");

    }

    // test method to confirm if expected result equals actual output of calculator

    static private void testOperation(String s, Fraction r) {

        System.out.println("Performing Operation:\t\t" + s);
        System.out.println("Expected Result:\t\t\t" + r);

        Fraction ret = fc.evaluate(new Fraction(0,1), s);
        System.out.println();
        System.out.println("\t>> Returned Result:\t" + ret);
        System.out.println("\t>> Equal: \t\t" + r.equals(ret));
        System.out.println();
    }

}
