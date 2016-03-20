public class Mold
{
	private String[][] cake;
	private int height;
	private int length;
	private int numberOfCake;
	private int maxVolume;
	
	public Mold(int height, int length, String shape) {
		this.height = height;
		this.length = length;
		this.numberOfCake = 0;
		this.maxVolume = 0;
		String[][] moldShape = new String[height][length];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < length; j++) {
				moldShape[i][j] = shape.substring(length * i + j, length * i + j + 1);
			}
		}
		this.cake = moldShape;
	}

	public int getNumberOfCake() {
		return this.numberOfCake;
	}

	public int getMaxVolume() {
		return this.maxVolume;
	}

	public void cut() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < length; j++) {
				if(this.cake[i][j].equals(".")) {
		 			this.maxVolume = Math.max(cutTheCake(i,j, ".", "@"), this.maxVolume);
		 			this.numberOfCake++;
		 		}
			}
		}
	}

	public int cutTheCake(int startRow, int startColumn, String cake, String mold) {
		int volume = 0;
		if(startRow >= this.height || startRow < 0) {
			return 0;
		}
		if(startColumn >= this.length || startColumn < 0) {
			return 0;
		}
		if(!this.cake[startRow][startColumn].equals(cake)) {
		 	return 0;
		 }
		 this.cake[startRow][startColumn] = mold;
		 volume++;
		 	
		 volume += cutTheCake(startRow - 1, startColumn, cake, mold);
		 volume += cutTheCake(startRow + 1, startColumn, cake, mold);
		 volume += cutTheCake(startRow, startColumn - 1, cake, mold);
		 volume += cutTheCake(startRow, startColumn + 1, cake, mold);

		 return volume;
		 
	}

	public void print() {
		for(int i = 0; i < this.height; i++) {
				for(int j = 0; j < this.length; j++) {
					System.out.print(this.cake[i][j]);
				}
				System.out.println("");
			}
	}
}