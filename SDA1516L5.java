import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SDA1516L5
{
	public static void main (String[] args) {
		try {
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
			String[] rowAndColumn = buffReader.readLine().split(" ");
			int row = Integer.parseInt(rowAndColumn[0]);
			int column = Integer.parseInt(rowAndColumn[1]);
			
			String input = "";
			String[][] cake = new String[row][column];
			
			for(int i = 0; i < row; i++) {
				input += buffReader.readLine();
			}

			Mold cakeMold = new Mold(row, column, input);

			cakeMold.cut();

			System.out.println(cakeMold.getMaxVolume());
			System.out.println(cakeMold.getNumberOfCake());
			

			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		
	}
}