/**
 * Created by Basil on 06/11/2014.
 * FractionCalculator object
 */
public class FractionCalculator {
    private Fraction value;
    private char operation;
    private boolean hasOperation;

    public FractionCalculator() {
        this.value = new Fraction(0,1); //  initialise calculator value to zero
        this.operation = '0';   // zero represents nil operation
        this.hasOperation = false;   // start without operation
    }

    @Override
    public String toString() {

        String str = (!this.hasOperation) ? "nil" : "" + this.operation;   //  if no operation print nil

        // represent FractionCalculator state by printing current value and saved operation
        return "Calculator value: " + this.value.toString()
                + ", operation: " + str;
    }

    // getter and setters

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

    // reset methods

    private void resetOperation() {
        this.operation = '0';    // nil operation
        this.hasOperation = false;
    }

    private void resetValue() {
        setValue(new Fraction(0, 1));
    }

    private void resetFractionCalculator() {
        resetOperation();
        resetValue();
    }

    // main class methods

    public Fraction evaluate(Fraction fraction, String inputString) {   //  if fraction not set, use current value of calculator

        /**
         * Method Structure:
         *
         *
         */

        // variables

        int num, denom, idx; //  values for return fraction and operation parsing
        Fraction ret = new Fraction(0,1);   //  return fraction to be determined
        String[] ops;   //  array to hold input operations and fractions

        setValue(fraction);  //  initialise calculator with given value

        // check if the input string contains a space
        // split into tokens

        if(inputString.contains(" ")) {
            ops = inputString.split(" ");
        } else {    //  invalid input?
            ops = new String[1];
            ops[0] = inputString;
        }

        // loop thought operations and numbers in input string

        for (String op : ops) {

            // for each token, check if it is an operation of fraction
            // if an operation is found, store this in the calculator
            // if an fraction is found, perform the stored operation on the current value and read value

            System.out.println("Operation: " + op);

            // parse input for operations or numbers
            // first check for / as this has multiple meanings

            if (op.contains("/")) { // op is fraction or divide operation

                if (op.length() == 1) { //  is divide operation

                    System.out.println("Storing operation /");

                    storeOperation(op.charAt(0));

                } else {    //  is fraction or input error

                    // catch index out of bounds exceptions in case of invalid input

                    try {

                        // find index of / in input and determine numerator and denominator

                        idx = op.indexOf("/");
                        num = Integer.parseInt(op.substring(0, idx)); // number before /
                        denom = Integer.parseInt(op.substring(idx+1,op.length())); // number after /

                        // if there is an operation stored, perform that operation on the current and read values
                        // else setup current value to input

                        if (this.hasOperation) {
                            ret = performOperation(new Fraction(num, denom));   //  update return value with value of operation
                        } else {
                            ret = new Fraction(num, denom); //  set current value of calculator to input
                        }

                    } catch (IndexOutOfBoundsException e) { //  if the / operation is first or last the substring calls with
                                                            //  raise a index exception. It also means the input is invalid
                        System.out.println("Invalid input, / operator in incorrect position");
                    }
                }

            } else if ((op.contains("+") || op.contains("-")
                        || op.contains("*")) && op.length() == 1) {  // binary operations

                storeOperation(op.charAt(0));

            } else if ((op.toLowerCase().contains("a")
                        || op.toLowerCase().contains("n")) && op.length() == 1) {  //  unary operations

                storeOperation(op.charAt(0));
                ret = performOperation(getValue());

            }  else if (( op.toLowerCase().contains("c")
                        || op.toLowerCase().contains("q")) && op.length() == 1) {  //  program commands

                storeOperation(op.charAt(0));

            } else {    //  other operators or integers

                // if op have length 1, then it is either an operation, and integer or invalid

                if (op.length() == 1) { //   has length one

                    if (Character.isDigit(op.charAt(0)))    //  and is a number
                        this.value = new Fraction(Integer.parseInt(op), 1); // store number in calculator value

                }
            }

            setValue(ret);  //  update current value of calculator
            System.out.println(this.toString()); // print current state of calculator

        }

        return ret;
    }

    private void storeOperation(char c) {

        if (!this.hasOperation) {    //  no current operation present
            this.setOperation(c);    //  okay to set
        } else {    //  input error, operation already specified
            System.out.println("Input error: More than one operation specified consecutively");
            this.resetOperation();
        }
    }

    private Fraction performOperation(Fraction f) {

        switch (this.operation) {

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
                System.out.println("Invalid Operation");
                return new Fraction(0,1);   //  return zero fraction

        }

    }

}


