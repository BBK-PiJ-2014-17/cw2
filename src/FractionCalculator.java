/**
 * Created by Basil on 06/11/2014.
 * FractionCalculator object
 */

public class FractionCalculator {

    /* class variables */

    private Fraction value;         // the current value of the calculator, initialised to zero.
    private char operation;         // the current operation of the calculator
    private boolean hasOperation;   // false if there is no saved operation. When false, any fraction read from the
                                    // command line will be stored in the current value of the calculator

    /* constructors */

    public FractionCalculator() {
        this.value = new Fraction(0,1); // initialise calculator value to zero
        this.operation = '0';           // zero represents null operation
        this.hasOperation = false;      // start without operation
    }

    /* main */
    /*
    public static void main(String[] args) {



    }
    */

    /* class methods */
    // override object toString method

    @Override
    public String toString() {

        String str = (!this.hasOperation) ? "null" : "" + this.operation;   //  if no operation print null

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
        this.operation = '0';    // null operation
        this.hasOperation = false;
    }

    private void resetValue() {
        setValue(new Fraction(0, 1));
    }

    private void resetFractionCalculator() {
        resetOperation();
        resetValue();
    }

    /* main class methods */
    // store given operation. if an operation is already present in the calculator, the input is invalid.

    private boolean storeOperation(char c) {

        if (!this.hasOperation) {       // no current operation present
            this.setOperation(c);       // okay to set
            return true;
        } else {                        // input error, operation already specified
            System.out.println();
            System.out.println("*** Input error: More than one operation specified consecutively ***");
            System.out.println();
            this.resetOperation();
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

        int num, denom, idx;                //  values for return fraction and operation parsing
        Fraction ret = new Fraction(0,1);   //  return fraction to be determined
        String[] ops;                       //  array to hold input operations and fractions

        setValue(fraction);                 //  initialise calculator with given value

        // split input into tokens
        // check if the input string contains a space

        if(inputString.contains(" ")) {     // split input into operations and fractions
            ops = inputString.split(" ");
        } else {                            // single operation
            ops = new String[1];
            ops[0] = inputString;
        }

        // loop thought operations and fractions and perform necessary operation

        //for (String op : ops) { // array iteration
        int i = 0;
        boolean err = false;
        while (i<ops.length && !err) {
            String op = ops[i];
            // for each token, check if it is an operation of fraction, determined by spacing and '/'
            // if an operation is found, perform unary operations and store binary operations in the calculator
            // if an fraction is found and a stored operation is present, perform that operation on the current
            // value and fraction, else store the fraction

            System.out.println("\t>> Token: " + op);

            // parse token for operations or fractions
            // first check for / as this has multiple meanings and determines fractions

            if (op.contains("/")) {         // op is fraction or divide operation

                if (op.length() == 1) {     // op is divide operation

                    if (storeOperation(op.charAt(0))) {
                        //pass
                    } else {
                        resetFractionCalculator();
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

                    } catch (NumberFormatException e) { // if the '/' operation is first or last the substring calls with
                                                            // raise a index exception. It also means the input is invalid
                                                            // value return will be zero by default
                        System.out.println();
                        System.out.println("*** Invalid input, incorrect usage of '/' operator. ***");
                        System.out.println();
                        err = true;
                        ret = new Fraction(0, 1);
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
                    break;
                }

            } else if (((op.toLowerCase().contains("a")
                        || op.toLowerCase().contains("n")) && op.length() == 1)
                        || ((op.contains("abs")
                        || op.contains("neg")) && op.length() == 3)) {              // unary operations

                // if the operation is a recognised mathematical unary operation in either the single character
                // or 3 character forms, perform this operation on the stored value of the calculator

                if (storeOperation(op.charAt(0))) {
                    //pass
                } else {
                    break;
                }           // store first character of operation, valid for 3 letter forms
                ret = performOperation(getValue());

            }  else if (((op.toLowerCase().contains("c")
                        || op.toLowerCase().contains("q")) && op.length() == 1)
                        || (op.contains("clear") && op.length() == 5)
                        || (op.contains("quit") && op.length() == 4)) {             // program commands

                // system command

                if (storeOperation(op.charAt(0))) {
                    //pass
                } else {
                    break;
                }

            } else {                                                                // integer or invalid

                // if one of the above operations has not been found, the only remaining options are that the input is
                // an integer, or an invalid input.

                // if the input is a number, the value can be parsed into an integer
                // otherwise the operation is invalid. use a try catch block to find number format exceptions

                try {

                    Fraction temp = new Fraction(Integer.parseInt(op), 1);

                    if (this.hasOperation) {
                        ret = performOperation(temp);   // perform operation with integer
                    } else {
                        ret = temp;                     // set current value of calculator to input
                    }

                } catch (NumberFormatException e) {                 // if the input is not a number, it is invalid
                                                                    // return value is zero by default
                    System.out.println();
                    System.out.println("*** Invalid input: " + op + " ***");
                    System.out.println();
                    err = true;
                    ret = new Fraction(0, 1);
                }

            }

            setValue(ret);                                      //  update current value of calculator before next operation
            System.out.println("\t\t>> " + this.toString());     // print current state of calculator
            i++;
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
                return new Fraction(0,1);

            case 'q':
                return new Fraction(0,1);

            default:
                System.out.println();
                System.out.println("*** Invalid Operation ***");
                System.out.println();
                resetFractionCalculator();
                return new Fraction(0,1);   //  return zero fraction

        }

    }

}


