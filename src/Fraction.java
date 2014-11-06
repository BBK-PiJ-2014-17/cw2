/**
 * Created by keith for the second coursework assignment.
 */
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int num, int denom) {
        if (denom == 0) {
            System.out.println("Invalid fraction with denominator 0"); 
	    // this should use exceptions
            return;
        }
        int gcd = myGcd(num, denom);
        setNumerator(num / gcd);
        setDenominator(denom / gcd);
    }

    @Override
    public String toString() {

        // adapt toString() method to print integers with numerator only
        return (this.getDenominator() == 1) ? "" + getNumerator() : "" + getNumerator() + '/' + getDenominator();
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int num) {
        numerator = num;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int num) {
        denominator = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (getDenominator() != fraction.getDenominator()) return false;
        if (getNumerator() != fraction.getNumerator()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    public Fraction multiply(Fraction other) {

        int num = this.getNumerator() * other.getNumerator();
        int denom = this.getDenominator() * other.getDenominator();
        return new Fraction(num, denom);

    }

    // methods for add, subtract & divide

    public Fraction add(Fraction other) {

        int num = (this.getNumerator() * other.getDenominator()) + (other.getNumerator() * this.getDenominator());
        int denom = this.getDenominator() * other.getDenominator();
        return new Fraction(num, denom);

    }

    public Fraction subtract(Fraction other) {

        int num = (this.getNumerator() * other.getDenominator()) - (other.getNumerator() * this.getDenominator());
        int denom = this.getDenominator() * other.getDenominator();
        return new Fraction(num, denom);

    }

    public Fraction divide(Fraction other) {

        Fraction recip = new Fraction(other.getDenominator(), other.getNumerator()); // reciprocal fraction
        return this.multiply(recip);

    }

    public Fraction absValue() {

        return new Fraction(Math.abs(this.getNumerator()), Math.abs(getDenominator()));
    }

    public Fraction negate() {

        return new Fraction(-1 * this.getNumerator(), this.getDenominator());
    }

    private int myGcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
