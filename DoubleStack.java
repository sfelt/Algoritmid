

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
			   //magasinist elemendi v�tmine
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
			   //kahe magasini v�rdsuse kindlakstegemine
		      return true; // TODO!!! Your code here!
		   }

		   @Override
		   public String toString() {
			   //teisendus s�neks (tipp l�pus)
		      return null; // TODO!!! Your code here!
		   }

		   public static double interpret (String pol) {
		      return 0.; // TODO!!! Your code here!
		   }
/*aritmeetilise avaldise p��ratud poola kuju (sulgudeta postfikskuju, Reverse Polish Notation) pol interpreteerimiseks (v�ljaarvutamiseks) eelpool defineeritud reaalarvude magasini abil. 
Avaldis on antud stringina, mis v�ib sisaldada reaalarve (s.h. negatiivseid ja mitmekohalisi) ning tehtem�rke + - * / , mis on eraldatud t�hikutega (whitespace). 
Tulemuseks peab olema avaldise v��rtus reaalarvuna v�i erindi (RuntimeException) tekitamine, kui avaldis ei ole korrektne.
Korrektne ei ole, kui avaldises esineb lubamatuid s�mboleid, kui avaldis j�tab magasini �leliigseid elemente v�i kasutab magasinist liiga palju elemente. 
N�it. DoubleStack.interpret ("2. 15. -") peaks tagastama v��rtuse -13. .
*/
		}


