import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class File {
	int p=-1;
	public int readFromLexicon(String WordName)throws IOException, ClassNotFoundException{
		ObjectInputStream in=new ObjectInputStream(new FileInputStream("Lexicon"));
		int k=0;
		try{//Oso to arxeio exei periexomeno diabazei apo auto
			while(true){
				k++;//prosbash sto disko
				in.read();//se periptosh pou uparxei to '\n' pou xrhsimopoihsa gia thn allagh selidas 
				Word NWord = (Word) in.readObject();	// diabazei ena object tupou word
				if(NWord.CompareWords(WordName)){
					p=NWord.Page;//an h le3h einai auth pou yagnoume apo8ikeuei thn selida sthn opoia briskete sto Index kai termatizei thn anazhthsh
					break;
				}
			}
		}catch(EOFException e){
			System.out.println("End Of Lexicon");
		}
		in.close();
		return k;
	}

	@SuppressWarnings("resource")
	public int locateOnIndex(String WordName) throws IOException, ClassNotFoundException{
		ObjectInputStream in=new ObjectInputStream(new FileInputStream("Index"));
		int k=0;
		boolean end=false;
		boolean found=false;
		if(p==-1){//an den exei alla3ei to p tote den exei bre8ei le3h ara termatizei to programma 
			System.out.println("Word Not Found!");
			return 0;
		}
		else{
			try{//Oso to arxeio exei periexomeno diabazei apo auto
				while(true){
					Reference ref = null;
					k++;//prosbash sto disko
					in.read();//se periptosh pou uparxei to '\n' pou xrhsimopoihsa gia thn allagh selidas 
					ref = (Reference) in.readObject();// diabazei ena object tupou Reference
					if(ref.Page==p){//Bre8ike arxeio me ari8mo selidas pou antistoixei se auton pou yagnoume
						found=true;//Bre8ike gia prwth fora arxeio me ari8mo selidas pou antistoixei se auton pou yagnoume
						System.out.println("Text:"+ref.FileName+" contains the word:"+WordName+" at the place:"+ref.Potition);
					}
					else{
						if(found){//Brikame gia proth fora afou to found egine true arxeio me diaforetiko  ari8mo selidas apo auton pou yagnoume
							end=true;//Ara exoume ektupwsei ola ta zhtoumena arxeia ara 
						}
					}
					if(found&&end){//mporoume na termathsoume thn anazhthsh
						break;
					}
				}
			}catch(EOFException e){
				System.out.println("End Of Index");
			}
			in.close();
			return k;
		}		
	}
}
