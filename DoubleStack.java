import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class DoubleStack {
	private LinkedList<Double> stack;

	public static void main(String[] argum) {
//		System.out.println(interpret("5 1 - 7 * 6 3 / +"));
//		System.out.println(interpret("2. 15. -"));
//		DoubleStack.interpret ("35. 10. + -"); // RuntimeException testInterpretUnderflow
//		System.out.println(DoubleStack.interpret("3 x")); // 7.
		System.out.println(DoubleStack.interpret("1.  2.    +")); // 3.
		System.out.println(DoubleStack.interpret("   \t \t356.  \t \t")); // 356.
		System.out.println(DoubleStack.interpret("\t2. \t5. +   \t")); // 7.
//		System.out.println(DoubleStack.interpret("1. \t0. A   \t")); // 7.
		System.out.println(DoubleStack.interpret("2. 0.  /")); // 7.
		//		DoubleStack.interpret();
//		DoubleStack.interpret();
					

	}

	DoubleStack() {
		stack = new LinkedList<Double>();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		DoubleStack temp = new DoubleStack();
		if (stack.size() > 0) {
			for (int i = 0; i < stack.size(); i++) {
				temp.stack.addLast(stack.get(i));
			}
		}
		return temp;
	}

	public boolean stEmpty() {
		return stack.size() == 0; // TODO!!! Your code here!
	}

	public void push(double a) {
		stack.addLast(a);

		// magasini elemendi lisamine, lisab elemendi viimaseks
		// TODO!!! Your code here!
	}

	public double pop() {
		if (stEmpty())
			throw new IndexOutOfBoundsException("Magasini alatäitumine" + Arrays.asList(stack));
		return stack.removeLast();

	} // pop

	public void op(String s) {
		
		if (stack.size() < 2)
			throw new RuntimeException("Operatsiooni \"" +  s +  "\" sooritamiseks on vajalik vähemalt kaks reaalarvu");
		
		double arg2 = pop();
		double arg1 = pop();

		if (s.equals("+"))
			push(arg1 + arg2);
		else if (s.equals("-"))
			push(arg1 - arg2);
		else if (s.equals("*"))
			push(arg1 * arg2);
		else if (s.equals("/"))
			push(arg1 / arg2);
		else {
			throw new IndexOutOfBoundsException("Tundmatu operatsioon:  \"" +  s + "\"");
		}
	}

	public double tos() {
		// tipu lugemine eemaldamiseta
		if (stEmpty())
			throw new IndexOutOfBoundsException("Puuduvad elemendid");
		return stack.getLast();

		// TODO!!! Your code here!a
	}

	@Override
	public boolean equals(Object o) {
		if (stack.equals(((DoubleStack) o).stack)) {
			return true;
		} else {
			return false;

		}

	}

	
	@Override
	public String toString() {
		if (stEmpty()) {
			return "Magasin on tühi";

		}
		// teisendus sõneks (tipp lõpus)
		return stack.toString();
	}

	public static double interpret(String pol) {

		String[] MyArray = pol.trim().split("\\s+");
		String operators = "+-*/";
		DoubleStack MinuStack = new DoubleStack();

		for (String t : MyArray) {
			if (operators.contains(t))
				MinuStack.op(t);
			else
			{			
				try {
					MinuStack.push(Double.parseDouble(t));
				} catch (Exception e) {
					throw new RuntimeException("Leitud sõnet ei ole võimalik konverteerida reaalarvuks: \"" + t + "\"" );
				}			
			} 
		}
			
		if (MinuStack.stack.size() != 1) {				
			throw new RuntimeException("Magasini suurus peab olema 1 pärast tehete tegemist"
					+ Arrays.asList(MinuStack.stack) + " RPN: " + pol);
		}
					
		return MinuStack.pop();

	}
}
