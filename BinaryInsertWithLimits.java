import java.util.*;


public class BinaryInsertWithLimits {

	public static void main(String[] args) {
		List<Integer> a = new ArrayList();
		// 12, 10, 34, 23, 9, 7, 8, 5, 6 
		a.add(12); a.add(10); a.add(34); a.add(23); a.add(9); 
		a.add(7); a.add(8); a.add(5); a.add(6); 
		sort(a, 0, 3);
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
		}
	}
	
//	allikad  http://stackoverflow.com/questions/3075752/binary-insertion-sort-algorithm
// http://geeksquiz.com/binary-insertion-sort/
// http://stackoverflow.com/questions/14355311/using-and-declaring-generic-listt
// http://math.hws.edu/javanotes/c7/s4.html

	public static void sort(List<Integer> a, int left, int right) {
		for (int i = left; i < right; ++i) { // ts�kkel �le k�ikide elementide
			
			int temp = a.get(i); // j�tame meelde i-nda elemendi
			
			int low = left; // vasakuks ��reks saab 0
			
			int up = i; // paremaks ��reks saab i
			
			// binary search selleks, et leida elemendile �iget asukohta
			while (low != up) { // kuni vasak saab v�rdseks paremaga
				int middle = (low + up) / 2; // leiame uuritava l�igu keskmise elemendi indeksi
				if (temp >= a.get(middle)) 	// kui temp on suurem kui l�igu keskmine element
					low = middle + 1; 	// v�tame uue l�igu paremalt ehk siis nihutame vasaku piiri 
										//keskmisest �he v�rra paremale
				else
					up = middle; /* vastasel juhul uurime edasi l�igu esimest poolt
									ehk siis nihutame parema ��re keskele */  				
			} // left == right ehk siis see on i-nda elemendi uueks asukohaks

			// nihutada i-ndat elementi uuele positsioonile, milleks on left <==> right, alustades kohalt i
			for (int j = i; j > low; --j) { // tagurpidi ts�kkel i-st kuni BST abil leitud �ige asukohani
				swap(a, j - 1, j); // vahetada massiivis j-s element temale eelneva elemendiga
			}
		}
	}

	public static <T> void swap(List<T> list, int i, int j) { 
		T k = list.get(i);
		list.set(i, list.get(j));
		list.set(j, k);
	}

}
