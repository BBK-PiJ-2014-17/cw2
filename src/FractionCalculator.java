/**
 * Created by Basil on 06/11/2014.
 * FractionCalculator object
 */
public class FractionCalculator {
    private Fraction value;
    private char operation;

    public FractionCalculator() {
        this.value = new Fraction(0,1); //  initialise calculator value to zero
        this.operation = '0';   // nil operation
    }

    @Override
    public String toString() {

        String str = (this.operation == '0') ? "nil" : "" + this.operation;   //  print nil operation

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
    }

}
