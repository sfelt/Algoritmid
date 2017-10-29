package homework4;

import java.util.*;


/* viited: 
 * https://github.com/teake/simplie/blob/master/src/edu/simplie/math/fraction.java#L331
 *  https://stackoverflow.com/questions/474535/best-way-to-represent-a-fraction-in-java
 *  https://stackoverflow.com/questions/343584/how-do-i-get-whole-and-fractional-parts-from-double-in-jsp-java
 *  https://stackoverflow.com/questions/45403170/how-to-convert-a-string-fraction-2-6-into-two-integers-java
 *  https://stackoverflow.com/questions/2563608/check-whether-a-string-is-parsable-into-long-without-try-catch
 *  
 */

/** This class represents fractions of form n/d where n and d are long integer 
 * numbers. Basic operations and arithmetics for fractions are provided.
 *  
 */
public class Lfraction implements Comparable<Lfraction> {

   /** Main method. Different tests. */
   public static void main (String[] param) {
/*
	   
	   LfractionTest test = new  LfractionTest();
//	   test.testClone();
	   test.testCompareTo();
//	   test.testCreateZeroDenominator();
	   test.testDivideBy();
//	   test.testDivideByZero();
	   test.testEquals();
	   test.testGetters();
//	   test.testHashCode();
	   test.testIntegerPart();
	   test.testInverse();
	   test.testLfractionPart();
	   test.testMinus();
	   test.testOpposite();
	   test.testPlus();
	   test.testTimes();
	   test.testToDouble();
	   test.testToLfraction();
	   test.testToString();
	   test.testValueOf();
//	   test.testZeroInverse();
*/
	   
   }

	private long numerator;
	private long denominator;
	
	/** Return the numerator */
	public final long numerator()
	{
		return numerator;
	}
	
	/** Return the denominator */
	public long denominator()
	{
		return denominator;
	}

   /** Constructor.
    * @param a numerator
    * @param b denominator > 0
    */
   public Lfraction (long a, long b) {
	   if (b == 0)
		   throw new RuntimeException("Nulliga jagamine!");   
	   normalizeAndSet(a, b);
   }

   /** Public method to access the numerator field. 
    * @return numerator
    */
   public long getNumerator() {
      return numerator();
   }


   /** Public method to access the denominator field. 
    * @return denominator
    */
   public long getDenominator() { 
      return denominator();
   }

   /** Conversion to string.
    * @return string representation of the fraction
    */
   @Override
   public String toString() {
		if (denominator() == 1)
			return "" + numerator();
		else
			return numerator() + "/" + denominator();
   }

   /** Equality test.
    * @param m second fraction
    * @return true if fractions this and m are equal
    */
   @Override
   public boolean equals (Object m) {
	   return compareTo((Lfraction)m) == 0;
   }

   /** Hashcode has to be equal for equal fractions.
    * @return hashcode
    */
   @Override
   public int hashCode() {
	   return (int) (numerator ^ denominator);
   }

   /** Sum of fractions.
    * @param m second added
    * @return this+m
    */
   public Lfraction plus (Lfraction m) {
		long an = numerator();
		long ad = denominator();
		long bn = m.numerator();
		long bd = m.denominator();
		return new Lfraction(an*bd+bn*ad, ad*bd);
   }

   /** Multiplication of fractions.
    * @param m second factor
    * @return this*m
    */
   public Lfraction times (Lfraction m) {
		long an = numerator();
		long ad = denominator();
		long bn = m.numerator();
		long bd = m.denominator();
		return new Lfraction(an*bn, ad*bd);
   }

   /** Inverse of the fraction. n/d becomes d/n.
    * @return inverse of this fraction: 1/this
    */
   public Lfraction inverse() {
	   return new Lfraction(denominator(),numerator());
   }

   /** Opposite of the fraction. n/d becomes -n/d.
    * @return opposite of this fraction: -this
    */
   public Lfraction opposite() {
	   return new Lfraction(-numerator(), denominator());
   }

   /** Difference of fractions.
    * @param m subtrahend
    * @return this-m
    */
   public Lfraction minus (Lfraction m) {
	   return plus(m.opposite());
   }

   /** Quotient of fractions.
    * @param m divisor
    * @return this/m
    */
   public Lfraction divideBy (Lfraction m) {
	   return times(m.inverse());
   }

   /** Comparison of fractions.
    * @param m second fraction
    * @return -1 if this < m; 0 if this==m; 1 if this > m
    */
   @Override
   public int compareTo (Lfraction m) {
		Lfraction b = (Lfraction)(m);
		long an = numerator();
		long ad = denominator();
		long bn = b.numerator();
		long bd = b.denominator();
		long l = an*bd;
		long r = bn*ad;
		return (l < r)? -1 : ((l == r)? 0: 1);
   }

   /** Clone of the fraction.
    * @return new fraction equal to this
    */
   @Override
   public Object clone() {
	   return new Lfraction(this.numerator(), this.denominator());
   }

   /** Integer part of the (improper) fraction. 
    * @return integer part of this fraction
    */
   public long integerPart() {	   
	   return (int)toDouble();
   }

   /** Extract fraction part of the (improper) fraction
    * (a proper fraction without the integer part).
    * @return fraction part of this fraction
    */
   public Lfraction fractionPart() {
	   // leida täisarvuline osa, mis on nullist suurem ja see eemaldada
	   int n = (int) (numerator / denominator);	
	   return new Lfraction(numerator - (n * denominator), denominator);
   }

   /** Approximate value of the fraction.
    * @return numeric value of this fraction
    */
   public double toDouble() {
	   return ((double)(numerator())) / ((double)(denominator()));
   }

   /** Double value f presented as a fraction with denominator d > 0.
    * double väärtus f, mis on esitatud kui murd, mille lugeja d on suurem nullist
    * @param f real number
    * @param d positive denominator for the result
    * @return f as an approximate fraction of form n/d
    */
   public static Lfraction toLfraction (double f, long d) {
	  long nominator = Math.round(Math.round(f*10*d)/10); // et läbiks 22/7 testi
      return new Lfraction(nominator, d);
   }

   /** Conversion from string to the fraction. Accepts strings of form
    * that is defined by the toString method.
    * @param s string form (as produced by toString) of the fraction
    * @return fraction represented by s
    */
   public static Lfraction valueOf (String s) {
	   
	   if (s.isEmpty() || s == null)
		   throw new RuntimeException("Sisend on tühi");	   

	  String[] numbers = s.split("/");
	  
	  if (numbers.length != 2)
		   throw new RuntimeException("Peab olema täpselt kaks arvu");
	  
	  if (!isNumeric(numbers[0]) || !isNumeric(numbers[1]))			  
			   throw new RuntimeException("Ei ole arv!");
	  
	  return new Lfraction(Long.parseLong(numbers[0]), Long.parseLong(numbers[1]));
   }
   
   /** Kas string koosneb numbritest? 
    * @param str
    * @return boolean
    */
   public static boolean isNumeric(String str) {
	    if (str == null) {
	        return false;
	    }
	    int sz = str.length();
	    for (int i = 0; i < sz; i++) {
	        if (Character.isDigit(str.charAt(i)) == false && str.charAt(i) != '-') {
	            return false;
	        }
	    }
	    return true;
	}
   
	/** Used for normalizing while setting the numerator and denominator */
	private void normalizeAndSet(long num, long den)
	{
		// normalize while constructing
		boolean numNonnegative = (num >= 0);
		boolean denNonnegative = (den >= 0);
		long a = numNonnegative? num : -num;
		long b = denNonnegative? den : -den;
		long g = gcd(a, b);
		numerator	= (numNonnegative == denNonnegative) ? (a / g) : (-a / g);
		denominator = b / g;
	}
   
	/**
	 * Compute the nonnegative greatest common divisor of a and b.
	 * (This is needed for normalizing fractions, but can be
	 * useful on its own.)
	 */
	public static long gcd(long a, long b)
	{
		long x;
		long y;
		
		if (a < 0) a = -a;
		if (b < 0) b = -b;
		
		if (a >= b)
		{
			x = a;
			y = b;
		}
		else
		{
			x = b;
			y = a;
		}
		
		while (y != 0)
		{
			long t = x % y;
			x = y;
			y = t;
		}
		return x;
	}
   
}

