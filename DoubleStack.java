

import java.util.LinkedList;

public class DoubleStack {
	private LinkedList<Double> stack;
	

		   public static void main (String[] argum) {
		   
		   }

		   DoubleStack() {
			   stack = new LinkedList<Double>();
		   }

		   @Override
		   public Object clone() throws CloneNotSupportedException {
			   DoubleStack temp = new DoubleStack ();
			return temp;
		   }
		  

		   public boolean stEmpty() {
		      return stack.size() == 0; // TODO!!! Your code here!
		   }

		   public void push (double a) {
			   //magasini elemendi lisamine
		      // TODO!!! Your code here!
		   }

		   public double pop() {
			   //magasinist elemendi võtmine
		      return 0.; // TODO!!! Your code here!
		   } // pop

		   public void op (String s) {
		      // TODO!!!
		   }
		  
		   public double tos() {
			   //tipu lugemine eemaldamiseta
		      return 0.; // TODO!!! Your code here!
		   }

		   @Override
		   public boolean equals (Object o) {
			   //kahe magasini võrdsuse kindlakstegemine
		      return true; // TODO!!! Your code here!
		   }

		   @Override
		   public String toString() {
			   //teisendus sõneks (tipp lõpus)
		      return null; // TODO!!! Your code here!
		   }

		   public static double interpret (String pol) {
		      return 0.; // TODO!!! Your code here!
		   }
/*aritmeetilise avaldise pööratud poola kuju (sulgudeta postfikskuju, Reverse Polish Notation) pol interpreteerimiseks (väljaarvutamiseks) eelpool defineeritud reaalarvude magasini abil. 
Avaldis on antud stringina, mis võib sisaldada reaalarve (s.h. negatiivseid ja mitmekohalisi) ning tehtemärke + - * / , mis on eraldatud tühikutega (whitespace). 
Tulemuseks peab olema avaldise väärtus reaalarvuna või erindi (RuntimeException) tekitamine, kui avaldis ei ole korrektne.
Korrektne ei ole, kui avaldises esineb lubamatuid sümboleid, kui avaldis jätab magasini üleliigseid elemente või kasutab magasinist liiga palju elemente. 
Näit. DoubleStack.interpret ("2. 15. -") peaks tagastama väärtuse -13. .
*/
		}


