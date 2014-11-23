
/**
 * Created by keith for the second coursework assignment.
 * Added to by basil to test additional functionality of Fraction class - Nov 14
 */

public class FractionTest {
    public static void main(String[] args) {

        	// test divide by zero - should print an error and exit
        new Fraction(1, 0);
        	// test multiply
		Fraction f = new Fraction(3,10);
		Fraction g = new Fraction(1,2);
		Fraction h = new Fraction(3,5);
		if (!f.equals(g.multiply(h))) System.out.println("Multiply failed");
        	// test equals
		test(new Fraction(1, 2),new Fraction(1, 2),"error test 1");
		test(new Fraction(1, 2),new Fraction(3, 6),"error test 2");
		test(new Fraction(-1, 2),new Fraction(1, -2),"error test 3");
		test(new Fraction(-1, -2),new Fraction(1, 2),"error test 4");
		test(new Fraction(4, -8),new Fraction(1, 2),"error test 5");

        	// extend with extra tests
			// test addition
		Fraction i = new Fraction(19,14);
		Fraction j = new Fraction(2,4);
		Fraction k = new Fraction(6,7);
		System.out.println(j.add(k).toString());
		if (!i.equals(j.add(k))) System.out.println("Addition failed");

			// test subtraction
		Fraction l = new Fraction(1,4);
		Fraction m = new Fraction(1,2);
		Fraction n = new Fraction(1,4);
		System.out.println(m.subtract(n).toString());
		if (!l.equals(m.subtract(n))) System.out.println("Subtraction failed");

		// test division
		Fraction o = new Fraction(2,3);
		Fraction p = new Fraction(1,2);
		Fraction q = new Fraction(3,4);
		System.out.println(p.divide(q).toString());
		if (!o.equals(p.divide(q))) System.out.println("Division failed");

		// test absolute value
		Fraction r = new Fraction(19,17);
		Fraction s = new Fraction(-19,17);
		System.out.println(r.absValue().toString());
		System.out.println(s.absValue().toString());
		if (!r.equals(s.absValue()) || !r.equals(r.absValue())) System.out.println("Absolute Value failed");

		// test negation
		Fraction t = new Fraction(7,3);
		Fraction u = new Fraction(7,-3);
		System.out.println(t.negate().toString());
		System.out.println(u.negate().toString());
		if (!t.equals(u.negate()) || !u.equals(t.negate())) System.out.println("Negate failed");

		// test toString with integers
		Fraction v = new Fraction(8,2);	//	expected "4"
		Fraction w = new Fraction(8,3);	//	expected "8/3"
		System.out.println(v.toString());
		System.out.println(w.toString());

    }

    static void test(Fraction f1, Fraction f2, String msg){
    	if (! f1.equals(f2))
		System.out.println(msg);
    }
}
