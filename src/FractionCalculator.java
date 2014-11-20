/**
 * Created by Basil on 06/11/2014.
 * FractionCalculator object for cw2
 */

import java.util.Scanner;           // scanner class for command line input

public class FractionCalculator {

    /*** constants ***/

    private static final Fraction ZERO = new Fraction(0,1); // the zero fraction
    private static final char NOP = '0';                    // the null operation

    /*** variables ***/

    private Fraction value;         // the current value of the calculator, initialised to zero.
    private char operation;         // the current operation of the calculator
    private boolean hasOperation;   // false if there is no saved operation. When false, any fraction read from the
                                    // command line will be stored in the current value of the calculator, otherwise
                                    // the stored operation will be performed immediately
    private boolean debugMode;      // test flag to control level of logging to console

    /*** constructors ***/

    public FractionCalculator() {       // default constructor
        this.value = ZERO;              // initialise calculator value to zero
        this.operation = NOP;           // initialise calculator operation to no operation
        this.hasOperation = false;      // start without operation
    }

    public FractionCalculator(boolean debug) {  // debug constructor
        this();                                 // call default constructor
        this.debugMode = debug;                 // set flag to print additional logging
        log("\nStarting in DEBUG mode...\n");
    }

    /*** main ***/

    // at start of execution, initialise Fraction Calculator object and
    // start main program

    public static void main(String[] args) {
        FractionCalculator fc = new FractionCalculator();         // initialise
        //FractionCalculator fc = new FractionCalculator(true);       // debug mode if required
        fc.start();                                                 // start main input loop
    }

    // begin program

    private void start() {

        // start input loop for user
        // exit upon command 'quit' and finish program

        Scanner sc = new Scanner(System.in);        // command line input mechanism
        String input;                               // input string
        boolean isRunning = true;                   // loop control, set false to exit program

        // introduce calculator and start input loop

        System.out.println();
        System.out.println("\t*********************************");
        System.out.println("\t****** FRACTION CALCULATOR ******");
        System.out.println("\t****** By Basil Mason. **********");
        System.out.println("\t*********************************");
        System.out.println();
        System.out.println(" >> Please enter fractions and commands");
        System.out.println(" >> at the prompts provided below.");
        System.out.println();
        System.out.println(" >> COMMAND LIST");
        System.out.println();
        System.out.println(" >>\t+\t\t\t : add");
        System.out.println(" >>\t-\t\t\t : subtract");
        System.out.println(" >>\t*\t\t\t : multiply");
        System.out.println(" >>\t/\t\t\t : divide");
        System.out.println(" >>\ta, A, abs\t : absolute value");
        System.out.println(" >>\tn, N, neg\t : negate");
        System.out.println(" >>\tc, C, clear\t : reset calculator");
        System.out.println(" >>\tq, Q, quit\t : exit program");
        System.out.println();
        System.out.println(" >> 0");

        // input loop

        while(isRunning) {

            System.out.print(" >> ");   // prompt
            input = sc.nextLine();      // read input

            if (input.equals("quit") || input.equals("q")) {        // test for quit command
                isRunning = false;                                  // set false to exit program
                System.out.println("\nExiting Program.");           // display exit message
                System.out.println("Goodbye.");
                break;
            } else {                                                // process input
                setValue(evaluate(this.value, input));              // call core evaluate method
                System.out.println(" >> " + this.value.toString()); // display calculator result
            }
        }

    }

    /*** methods ***/
    // override object toString() method

    @Override
    public String toString() {

        String str = (!this.hasOperation) ? "none" : "" + this.operation;   //  if no operation print none

        // represent FractionCalculator state by printing current value and saved operation
        return "Calculator value: " + this.value.toString()
                + ", operation: " + str;
    }

    /* getter and setters */

    public Fraction getValue() {
        return this.value;
    }

    private void setValue(Fraction f) {
        this.value = f;
    }

    public char getOperation() {
        return this.operation;
    }

    private void setOperation(char op) {
        this.operation = op;
        this.hasOperation = true;
    }

    /* reset methods */

    private void resetOperation() {
        this.operation = NOP;    // null operation
        this.hasOperation = false;
    }

    private void resetValue() {
        setValue(ZERO);
    }

    private void resetFractionCalculator() {
        resetOperation();
        resetValue();
    }

    /* core methods */

    // store given operation. if an operation is already present in the calculator, the input is invalid.

    private boolean storeOperation(char c) {

        if (!this.hasOperation) {       // if no current operation present
            this.setOperation(c);       // set operation
            return true;
        } else {                        // input error, operation already specified
            System.out.println();
            System.out.println("*** Input error: More than one operation specified consecutively ***");
            System.out.println();
            this.resetFractionCalculator(); // reset calculator
            return false;
        }
    }

    // evaluate input string of operations and fractions, based on current value of calculator

    public Fraction evaluate(Fraction fraction, String inputString) {

        /**
         * Method Structure:
         *
         *
         */

        // variables

        int i = 0, num, denom, idx;             //  values for return fraction and operation parsing
        Fraction ret = fraction;            //  return fraction to be determined
        String[] ops;                       //  array to hold input operations and fractions
        boolean err = false;                // flag for error checking, if set to true, parsing will stop

        setValue(fraction);                 //  initialise calculator with given value

        // split input into tokens
        // check if the input string contains a space

        if(inputString.contains(" ")) {     // split input into operations and fractions
            ops = inputString.split(" ");
        } else {                            // single operation
            ops = new String[1];
            ops[0] = inputString;
        }

        // loop through operations and fractions and perform necessary operation

        while (i < ops.length && !err) {

            String op = ops[i];

            // for each token, check if it is an operation or fraction, determined by spacing and '/'
            // if an operation is found, perform unary operations and store binary operations in the calculator
            // if an fraction is found and a stored operation is present, perform that operation on the current
            // value and fraction, else store the fraction

            log("\t>> Token: " + op);

            // parse token for operations or fractions
            // first check for / as this has multiple meanings and determines fractions

            if (op.contains("/")) {         // op is fraction or divide operation

                if (op.length() == 1) {     // op is divide operation

                    if (storeOperation(op.charAt(0))) {
                        //pass, successfully stored operation
                    } else {
                        resetFractionCalculator();  // error storing operation
                        err = true;                 // end parsing loop
                        break;
                    }

                } else {                    // is fraction or input error

                    // catch number format exceptions in case of invalid input
                    // if a '/' has been entered as the first or last character of the input string, it is an invalid
                    // command. using the parsing below, this would cause in index exception when calculating the
                    // num and denom of the fraction, and in turn the parseInt method would fail raising a number
                    // format exception. if no error, fraction formed.

                    try {

                        // find index of '/' in input to determine numerator and denominator values

                        idx = op.indexOf("/");                                      // index of '/' character
                        num = Integer.parseInt(op.substring(0, idx));               // number before '/'
                        denom = Integer.parseInt(op.substring(idx+1,op.length()));  // number after '/'

                        // if there is an operation stored, perform that operation on the current and input values
                        // else setup current value to input

                        if (this.hasOperation) {
                            ret = performOperation(new Fraction(num, denom));   // update return value with value of operation
                        } else {
                            ret = new Fraction(num, denom);                     // set current value of calculator to input
                        }

                    } catch (NumberFormatException e) {     // if the '/' operation is first or last the substring calls with
                                                            // raise a index exception. It also means the input is invalid
                                                            // value return will be zero by default
                        System.out.println();
                        System.out.println("*** Invalid input, incorrect usage of '/' operator. ***");
                        System.out.println();
                        err = true;
                        ret = ZERO;
                    }
                }

            // other operations

            } else if ((op.contains("+") || op.contains("-")
                        || op.contains("*")) && op.length() == 1) {                 // binary operations

                // if the operation is a recognised mathematical binary operation and is only 1 character long
                // then the operation is valid and should be stored in the calculator

                if (storeOperation(op.charAt(0))) {
                    //pass
                } else {
                    err = true;
                    break;
                }

            } else if (((op.toLowerCase().contains("a")
                        || op.toLowerCase().contains("n")) && op.length() == 1)
                        || ((op.contains("abs")
                        || op.contains("neg")) && op.length() == 3)) {              // unary operations

                // if the operation is a recognised mathematical unary operation in either the single character
                // or 3 character forms, perform this operation on the stored value of the calculator
                // store first character of operation, valid for 3 letter forms

                if (storeOperation(op.charAt(0))) {
                    //pass
                } else {
                    err = true;
                    break;
                }

                // perform operation immediately for unary operators

                ret = performOperation(getValue());

            }  else if (((op.toLowerCase().contains("c")
                        || op.toLowerCase().contains("q")) && op.length() == 1)
                        || (op.contains("clear") && op.length() == 5)
                        || (op.contains("quit") && op.length() == 4)) {             // program commands

                // perform program commands. either clear calculator or quit

                if (storeOperation(op.charAt(0))) {
                    ret = performOperation(ZERO); // call perform method to either reset or quit
                } else {
                    err = true;
                    break;
                }

            } else {                                                                // integer or invalid

                // if one of the above operations has not been found, the only remaining options are that the input is
                // an integer, or an invalid input.

                // if the input is a number, the value can be parsed into an integer
                // otherwise the operation is invalid. use a try catch block to find number format exceptions

                try {

                    Fraction integer = new Fraction(Integer.parseInt(op), 1);  // construct Fraction from integer

                    if (this.hasOperation) {
                        ret = performOperation(integer);   // perform operation with integer
                    } else {
                        ret = integer;                     // set current value of calculator to input
                    }

                } catch (NumberFormatException e) {                 // if the input is not a number, it is invalid
                                                                    // return value is zero by default
                    System.out.println();
                    System.out.println("*** Invalid input: " + op + " ***");
                    System.out.println();
                    err = true;
                    ret = ZERO;
                }

            }

            setValue(ret);                                      //  update current value of calculator before next operation
            log("\t\t>> " + this.toString());     // print current state of calculator
            i++;    // increment to iterate through op string array
        }

        return ret; // return result of all operations
    }

    // perform given operation on fraction class

    private Fraction performOperation(Fraction f) {

        switch (getOperation()) {

            case '+':
                resetOperation();
                return getValue().add(f);

            case '-':
                resetOperation();
                return getValue().subtract(f);

            case '/':
                resetOperation();
                return getValue().divide(f);

            case '*':
                resetOperation();
                return getValue().multiply(f);

            case 'a':
                resetOperation();
                return f.absValue();

            case 'n':
                resetOperation();
                return f.negate();

            case 'c':
                resetFractionCalculator();
                return ZERO;

            case 'q':
                return ZERO;

            default:
                System.out.println();
                System.out.println("*** Invalid Operation ***");
                System.out.println();
                resetFractionCalculator();
                return ZERO;   //  return zero fraction

        }

    }

    // logger for debug mode

    private void log(String s) {
        if (debugMode) System.out.println(s);
    }

}


