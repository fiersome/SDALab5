import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program ini memodelkan cetakan kue yang digunakan oleh pak chanek
 *
 * @author gibran muhammad fajar wisesa 1506688922
 * @version 2016.20.03
 *
**/
public class SDA1516L5
{
	public static void main (String[] args) 
	{
		try {
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
			String[] rowAndColumn = buffReader.readLine().split(" ");

			int row = Integer.parseInt(rowAndColumn[0]);
			int column = Integer.parseInt(rowAndColumn[1]);
			
			String input = "";
			
			for(int i = 0; i < row; i++) {
				input += buffReader.readLine();
			}

			Mold cakeMold = new Mold(row, column, input);
			cakeMold.cut();

			/* output program */
			System.out.println(cakeMold.getMaxVolume());
			System.out.println(cakeMold.getNumberOfCake());
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		
	}
}

/**
 * Kelas ini memodelkan cetakan yang digunakan pak chanek untuk mencetak kue
 * dari kelas ini dapat diketahui volume terbesar dari kue yang dicetak
 * dan banyak nya kue yang dapat dicetak
 *
 * @author gibran muhammad fajar wisesa 1506688922
 * @version 2016.20.03
 **/
class Mold
{
	private String[][] cake;
	private int height;
	private int length;
	private int numberOfCake;
	private int maxVolume;
	
	/**
	 * Kelas mold memerlukan data berupa tinggi, panjang dan bentuk unik cetakan
	 *
	 * @param height tinggi cetakan
	 * @param length panjang cetakan
	 * @param shape bentuk cetakan
	 * 
	**/
	public Mold(int height, int length, String shape) 
	{
		this.height = height;
		this.length = length;
		this.numberOfCake = 0;
		this.maxVolume = 0;
		String[][] moldShape = new String[height][length];

		/** membuat cetakan berupa array 2D sesuai dengan bentuk yang diinginkan **/
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < length; j++) {
				moldShape[i][j] = shape.substring(length * i + j, length * i + j + 1);
			}
		}

		this.cake = moldShape;
	}

	/** 
	 * mengembalikan jumlah kue yang dicetak
	 *
	 * @return jumlah kue yang dapat dicetak oleh cetakan
	 *
	 **/
	public int getNumberOfCake() 
	{
		return this.numberOfCake;
	}

	/** 
	 * mengembalikan volume terbesar dari beberapa kue yang dicetak
	 *
	 * @return volume terbesar
	 *
	 **/
	public int getMaxVolume() 
	{
		return this.maxVolume;
	}

	/**
	 * method untuk mulai memotong kue sesuai dengan cetakannya
	 **/
	public void cut() 
	{

		/** cek setiap elemen dari kue **/
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < length; j++) {
				if(this.cake[i][j].equals(".")) {
		 			this.maxVolume = Math.max(cutOneCake(i,j, ".", "@"), this.maxVolume);
		 			this.numberOfCake++;
		 		}
			}
		}
	}

	/**
	 * method untuk memotong 1 kue yang sesuai pada cetakan nya
	 *
	 * @param startRow indeks baris pada array 2D kue
	 * @param startColumn indeks kolom pada array 2D kue
	 * @param cakePointer penanda bahwa objek tersebut adalah kue
	 * @param emptySpacePointer penanda bahwa kue tersebut sudah dipotong
	 *
	 * @return volume dari kue yang dipotong
	 *
	 **/
	private int cutOneCake(int startRow, int startColumn, String cakePointer, String emptySpacePointer) 
	{
		int volume = 0;
		if(startRow >= this.height || startRow < 0) {
			return 0;
		}
		if(startColumn >= this.length || startColumn < 0) {
			return 0;
		}
		if(!this.cake[startRow][startColumn].equals(cakePointer)) {
		 	return 0;
		 }
		 this.cake[startRow][startColumn] = emptySpacePointer;
		 volume++;
		 	
		 volume += cutOneCake(startRow - 1, startColumn, cakePointer, emptySpacePointer);
		 volume += cutOneCake(startRow + 1, startColumn, cakePointer, emptySpacePointer);
		 volume += cutOneCake(startRow, startColumn - 1, cakePointer, emptySpacePointer);
		 volume += cutOneCake(startRow, startColumn + 1, cakePointer, emptySpacePointer);

		 return volume;
	}
}