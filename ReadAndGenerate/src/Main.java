import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Generate G = new Generate();
		G.AddFile("Kennedy");
		G.AddFile("MartinLutherKing");
		G.AddFile("Obama");
		G.sorting();
		G.GenerateLex();
		G.GenerateInd();
		System.out.println("Done!");
	}

}
