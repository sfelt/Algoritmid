
/** Quaternions. Basic operations. */
public class Quaternion {
	private static final double TAPSUS = 0.000001;
	private double[] quat;

	// TODO!!! Your fields here!

	/**
	 * Constructor from four double values.
	 * 
	 * @param a
	 *            real part
	 * @param b
	 *            imaginary part i
	 * @param c
	 *            imaginary part j
	 * @param d
	 *            imaginary part k
	 */
	public Quaternion(double a, double b, double c, double d) {
		quat = new double[] { a, b, c, d };

	}

	/**
	 * Real part of the quaternion.
	 * 
	 * @return real part
	 */
	public double getRpart() {
		return quat[0]; // TODO!!!
	}

	/**
	 * Imaginary part i of the quaternion.
	 * 
	 * @return imaginary part i
	 */
	public double getIpart() {
		return quat[1]; // TODO!!!
	}

	/**
	 * Imaginary part j of the quaternion.
	 * 
	 * @return imaginary part j
	 */
	public double getJpart() {
		return quat[2];// TODO!!!
	}

	/**
	 * Imaginary part k of the quaternion.
	 * 
	 * @return imaginary part k
	 */
	public double getKpart() {
		return quat[3]; // TODO!!!
	}

	/**
	 * Conversion of the quaternion to the string.
	 * 
	 * @return a string form of this quaternion: "a+bi+cj+dk" (without any
	 *         brackets)
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getRpart());
		if (getIpart() >= 0)
			sb.append("+");
		sb.append(getIpart());
		if (getJpart() >= 0)
			sb.append("i+");
		else
			sb.append("i");
		sb.append(getJpart());
		if (getKpart() >= 0)
			sb.append("j+");
		else
			sb.append("j");
		sb.append(getKpart());
		sb.append("k");
		return sb.toString();
	}

	/**
	 * Conversion from the string to the quaternion. Reverse to
	 * <code>toString</code> method.
	 * 
	 * @throws IllegalArgumentException
	 *             if string s does not represent a quaternion (defined by the
	 *             <code>toString</code> method)
	 * @param s
	 *            string of form produced by the <code>toString</code> method
	 * @return a quaternion represented by string s
	 */
	public static Quaternion valueOf(String s) {
		return null; // TODO!!!
	}

	/**
	 * Clone of the quaternion.
	 * 
	 * @return independent clone of <code>this</code>
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Quaternion(getRpart(), getIpart(), getJpart(), getKpart());
	}

	/**
	 * Test whether the quaternion is zero.
	 * 
	 * @return true, if the real part and all the imaginary parts are (close to)
	 *         zero
	 */
	public boolean isZero() {
		return equals(new Quaternion(0., 0., 0., 0.));// TODO!!!
	}

	/**
	 * Conjugate of the quaternion. Expressed by the formula
	 * conjugate(a+bi+cj+dk) = a-bi-cj-dk
	 * 
	 * @return conjugate of <code>this</code>
	 */
	public Quaternion conjugate() {
		return new Quaternion(getRpart(), -getIpart(), -getJpart(), -getKpart()); // TODO!!!
	}

	/**
	 * Opposite of the quaternion. Expressed by the formula opposite(a+bi+cj+dk)
	 * = -a-bi-cj-dk
	 * 
	 * @return quaternion <code>-this</code>
	 */
	public Quaternion opposite() {
		return new Quaternion(getRpart() * -1., getIpart() * -1., getJpart() * -1., getKpart() * -1.); // TODO!!!
	}

	/**
	 * Sum of quaternions. Expressed by the formula (a1+b1i+c1j+d1k) +
	 * (a2+b2i+c2j+d2k) = (a1+a2) + (b1+b2)i + (c1+c2)j + (d1+d2)k
	 * 
	 * @param q
	 *            addend
	 * @return quaternion <code>this+q</code>
	 */
	public Quaternion plus(Quaternion q) {
		return new Quaternion(getRpart() + q.getRpart(), getIpart() + q.getIpart(), getJpart() + q.getJpart(),
				getKpart() + q.getKpart());// TODO!!!
	}

	/**
	 * Product of quaternions. Expressed by the formula (a1+b1i+c1j+d1k) *
	 * (a2+b2i+c2j+d2k) = (a1a2-b1b2-c1c2-d1d2) + (a1b2+b1a2+c1d2-d1c2)i +
	 * (a1c2-b1d2+c1a2+d1b2)j + (a1d2+b1c2-c1b2+d1a2)k
	 * 
	 * @param q
	 *            factor
	 * @return quaternion <code>this*q</code>
	 */
	public Quaternion times(Quaternion q) {
		return new Quaternion(
				getRpart() * q.getRpart() - getIpart() * q.getIpart() - getJpart() * q.getJpart()
						- getKpart() * q.getKpart(),
				getRpart() * q.getIpart() + q.getRpart() * getIpart() + getJpart() * q.getKpart()
						- getKpart() * q.getJpart(),
				getRpart() * q.getJpart() - getIpart() * q.getKpart() + getJpart() * q.getRpart()
						+ getKpart() * q.getIpart(),
				getRpart() * q.getKpart() + getIpart() * q.getJpart() - getJpart() * q.getIpart()
						+ getKpart() * q.getRpart()); // TODO!!!
	}

	/**
	 * Multiplication by a coefficient.
	 * 
	 * @param r
	 *            coefficient
	 * @return quaternion <code>this*r</code>
	 */
	public Quaternion times(double r) {
		return new Quaternion(getRpart() * r, getIpart() * r, getJpart() * r, getKpart() * r); // TODO!!!
	}

	/**
	 * Inverse of the quaternion. Expressed by the formula 1/(a+bi+cj+dk) =
	 * a/(a*a+b*b+c*c+d*d) + ((-b)/(a*a+b*b+c*c+d*d))i +
	 * ((-c)/(a*a+b*b+c*c+d*d))j + ((-d)/(a*a+b*b+c*c+d*d))k
	 * 
	 * @return quaternion <code>1/this</code>
	 */
	public Quaternion inverse() {
		if (isZero())
			throw new RuntimeException("Kui on tühi, siis ei saa ...");
		else {
			double a = getRpart() * getRpart() + getIpart() * getIpart() + getJpart() * getJpart()
					+ getKpart() * getKpart();
			return new Quaternion(getRpart() / a, (-getIpart()) / a, (-getJpart()) / a, (- getKpart()) / a); // TODO!!!
		}
	}

	/**
	 * Difference of quaternions. Expressed as addition to the opposite.
	 * 
	 * @param q
	 *            subtrahend
	 * @return quaternion <code>this-q</code>
	 */
	public Quaternion minus(Quaternion q) {
		return new Quaternion(getRpart() - q.getRpart(), getIpart() - q.getIpart(), getJpart() - q.getJpart(),
				getKpart() - q.getKpart()); // TODO!!!
	}

	/**
	 * Right quotient of quaternions. Expressed as multiplication to the
	 * inverse.
	 * 
	 * @param q
	 *            (right) divisor
	 * @return quaternion <code>this*inverse(q)</code>
	 */
	public Quaternion divideByRight(Quaternion q) {
		return new Quaternion(
				getRpart() * q.inverse().getRpart() - getIpart() * q.inverse().getIpart()
						- getJpart() * q.inverse().getJpart() - getKpart() * q.inverse().getKpart(),
				getRpart() * q.inverse().getIpart() + q.inverse().getRpart() * getIpart()
						+ getJpart() * q.inverse().getKpart() - getKpart() * q.inverse().getJpart(),
				getRpart() * q.inverse().getJpart() - getIpart() * q.inverse().getKpart()
						+ getJpart() * q.inverse().getRpart() + getKpart() * q.inverse().getIpart(),
				getRpart() * q.inverse().getKpart() + getIpart() * q.inverse().getJpart()
						- getJpart() * q.inverse().getIpart() + getKpart() * q.inverse().getRpart());
		// TODO!!!
	}

	/**
	 * Left quotient of quaternions.
	 * 
	 * @param q
	 *            (left) divisor
	 * @return quaternion <code>inverse(q)*this</code>
	 */
	public Quaternion divideByLeft(Quaternion q) {
		return new Quaternion(
				q.inverse().getRpart() * getRpart() - q.inverse().getIpart() * getIpart()
						- q.inverse().getJpart() * getJpart() - q.inverse().getKpart() * getKpart(),
				q.inverse().getRpart() * getIpart() + q.inverse().getIpart() * getRpart()
						+ q.inverse().getJpart() * getKpart() - q.inverse().getKpart() * getJpart(),
				q.inverse().getRpart() * getJpart() - q.inverse().getIpart() * getKpart()
						+ q.inverse().getJpart() * getRpart() + q.inverse().getKpart() * getIpart(),
				q.inverse().getRpart() * getKpart() + q.inverse().getIpart() * getJpart()
						- q.inverse().getJpart() * getIpart() + q.inverse().getKpart() * getRpart());// TODO!!!
	}

	/**
	 * Equality test of quaternions. Difference of equal numbers is (close to)
	 * zero.
	 * 
	 * @param qo
	 *            second quaternion
	 * @return logical value of the expression <code>this.equals(qo)</code>
	 */
	//Math.abs http://www.tutorialspoint.com/java/lang/math_abs_int.htm
	//equals http://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java
	@Override
	public boolean equals(Object qo) {
		if (Math.abs(getRpart() - ((Quaternion) qo).getRpart()) > TAPSUS)
			return false;
		if (Math.abs(getIpart() - ((Quaternion) qo).getIpart()) > TAPSUS)
			return false;
		if (Math.abs(getJpart() - ((Quaternion) qo).getJpart()) > TAPSUS)
			return false;
		if (Math.abs(getKpart() - ((Quaternion) qo).getKpart()) > TAPSUS)
			return false;
		return true;
	}


	/**
	 * Dot product of quaternions. (p*conjugate(q) + q*conjugate(p))/2
	 * 
	 * @param q
	 *            factor
	 * @return dot product of this and q
	 */
	public Quaternion dotMult(Quaternion q) {
		Quaternion first, second, sum;
		first = this.times(q.conjugate());
		second = q.times(this.conjugate());
		sum = first.plus(second);
		return new Quaternion(sum.getRpart() / 2., sum.getIpart() / 2., sum.getJpart() / 2., sum.getKpart() / 2.);
	}
	// TODO!!!

	/**
	 * Integer hashCode has to be the same for equal objects.
	 * 
	 * @return hashcode
	 */
	@Override
	public int hashCode() {
		return toString().hashCode(); // TODO!!!
	}

	/**
	 * Norm of the quaternion. Expressed by the formula norm(a+bi+cj+dk) =
	 * Math.sqrt(a*a+b*b+c*c+d*d)
	 * 
	 * @return norm of <code>this</code> (norm is a real number)
	 */
	public double norm() {
		return Math.sqrt(
				getRpart() * getRpart() + getIpart() * getIpart() + getJpart() * getJpart() + getKpart() * getKpart()); // TODO!!!
	}

	/**
	 * Main method for testing purposes.
	 * 
	 * @param arg
	 *            command line parameters
	 */
	public static void main(String[] arg) {
		Quaternion arv1 = new Quaternion(-1., 1, 2., -2.);
		if (arg.length > 0)
			arv1 = valueOf(arg[0]);
		System.out.println("first: " + arv1.toString());
		System.out.println("real: " + arv1.getRpart());
		System.out.println("imagi: " + arv1.getIpart());
		System.out.println("imagj: " + arv1.getJpart());
		System.out.println("imagk: " + arv1.getKpart());
		System.out.println("isZero: " + arv1.isZero());
		System.out.println("conjugate: " + arv1.conjugate());
		System.out.println("opposite: " + arv1.opposite());
		System.out.println("hashCode: " + arv1.hashCode());
		Quaternion res = null;
		try {
			res = (Quaternion) arv1.clone();
		} catch (CloneNotSupportedException e) {
		}
		;
		System.out.println("clone equals to original: " + res.equals(arv1));
		System.out.println("clone is not the same object: " + (res != arv1));
		System.out.println("hashCode: " + res.hashCode());
		res = valueOf(arv1.toString());
		System.out.println("string conversion equals to original: " + res.equals(arv1));
		Quaternion arv2 = new Quaternion(1., -2., -1., 2.);
		if (arg.length > 1)
			arv2 = valueOf(arg[1]);
		System.out.println("second: " + arv2.toString());
		System.out.println("hashCode: " + arv2.hashCode());
		System.out.println("equals: " + arv1.equals(arv2));
		res = arv1.plus(arv2);
		System.out.println("plus: " + res);
		System.out.println("times: " + arv1.times(arv2));
		System.out.println("minus: " + arv1.minus(arv2));
		double mm = arv1.norm();
		System.out.println("norm: " + mm);
		System.out.println("inverse: " + arv1.inverse());
		System.out.println("divideByRight: " + arv1.divideByRight(arv2));
		System.out.println("divideByLeft: " + arv1.divideByLeft(arv2));
		System.out.println("dotMult: " + arv1.dotMult(arv2));
	}
}
// end of file
