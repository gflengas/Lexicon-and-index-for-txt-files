import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File F=new File();
		int f,s,t;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Which word do you want to search?");
		String word = keyboard.next();//diabasma le3hs apo to plhktrologio
		f=F.readFromLexicon(word.toUpperCase());//anazhthsh sto le3iko
		System.out.println("Number of times accessed the drive during readFromLexicon:"+f);
		s=F.locateOnIndex(word.toUpperCase());//anazhthsh sto eurethrio
		System.out.println("Number of times accessed the drive during locateOnIndex:"+s);
		t=f+s;
		System.out.println("Total Number of times accessed the drive:"+t);
		keyboard.close();
	}

}
