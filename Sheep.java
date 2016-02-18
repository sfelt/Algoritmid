
public class Sheep {
	
	enum Animal {sheep, goat};
		
	public static void main (String[] args) {
		int uBound = 10000000;
		Animal[] arr = new Animal[uBound];		
		fillArray(arr);		
		
		long startTime = System.currentTimeMillis();
		reorder(arr);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println (estimatedTime);		
		}

	
	// sikud ja lambad segamini
	public static void fillArray(Animal[] animals) {
		for (int i = 0; i < animals.length; i++) {
			if (annaYksJuhuslikArv(0, 1) == 0) {
				animals[i] = Animal.goat;
			} else {
				animals[i] = Animal.sheep;
			}
		}
	}
	
	public static void reorder(Animal[] animals) {
		
		int goats = 0;	

		// loeme sikud kokku
		for (int i = 0; i < animals.length; i++) {
			if (animals[i] == Animal.goat) {
				goats++;
			}
		}

		// sikud massivi algusesse
		for (int i = 0; i < goats; i++) {
			animals[i] = Animal.goat;
			}
		
		// lambad massiivi lõppu
		for (int i = goats; i < animals.length; i++) {
			animals[i] = Animal.sheep;
			}
	}
	
	public static int annaYksJuhuslikArv(int min, int max) {

		int variatsioone = max - min + 1;
		int suvaline = (int) (Math.random() * variatsioone + min);

		return suvaline;

	}

}
