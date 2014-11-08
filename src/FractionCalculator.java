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

    public Fraction evaluate(Fraction fraction, String inputString) {

        int num, denom, idx; //  values for return fraction and operation parsing
        Fraction ret;   //  return fraction
        String[] ops;   //  array to hold input operations and fractions

        setValue(fraction);  //  initialise calculator with given value

        // check if the input string contains a space

        if(inputString.contains(" ")) {
            ops = inputString.split(" ");
        } else {    //  invalid input?
            ops = new String[1];
            ops[0] = inputString;
        }

        // loop thought operations and numbers in input string

        for (String op : ops) {

            System.out.println(op);
            System.out.println(op.length());

            // parse input for operations or numbers
            // first check for / as this has multiple meanings

            if (op.contains("/")) { // op is fraction or divide operation

                if (op.length() == 1) { //  is divide operation

                    if (!this.hasOperation) {    //  no current operation present
                        this.setOperation(op.charAt(0));    //  okay to set
                    } else {    //  input error, operation already specified
                        System.out.println("Input error: More than one operation specified consecutively");
                        this.resetOperation();
                    }

                } else {    //  is fraction or input error

                    try {
                        idx = op.indexOf("/");
                        num = Integer.parseInt(op.substring(0, idx)); // first number
                        denom = Integer.parseInt(op.substring(idx+1,op.length())); // second number

                        if (this.hasOperation)
                            ret = performOperation(new Fraction(num, denom));

                    } catch (IndexOutOfBoundsException e) { //  if the / operation is first or last the substring calls with
                                                            //  raise a index exception. It also means the input is invalid
                        System.out.println("Invalid input, / operator in incorrect position");
                    }
                }

            } else if (op.contains("+")) {  //  addition operator

            } else if (op.contains("*")) {  //  multiplication operator

            } else if (op.contains("-")) {  //   subtraction operator

            } else {    //  other operators or integers

                // if op have length 1, then it is either an operation, and integer or invalid

                if (op.length() == 1) { //   has length one

                    if (Character.isDigit(op.charAt(0)))    //  and is a number
                        this.value = new Fraction(Integer.parseInt(op), 1); // store number in calculator value

                }
            }

            System.out.println(this.toString()); // print current state of calculator

        }

        return new ret;
    }

    private Fraction performOperation(Fraction f) {

        switch (this.operation) {

            case '+':
                return this.value.add(f);

            case '-':
                return this.value.subtract(f);

            case '/':
                return this.value.divide(f);

            case '*':
                return this.value.multiply(f);

            default:
                System.out.println("Invalid Operation");
                return new Fraction(0,1);   //  return zero fraction

        }

    }

}


