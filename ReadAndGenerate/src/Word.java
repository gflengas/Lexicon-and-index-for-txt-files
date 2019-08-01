import java.io.Serializable;
import java.util.Comparator;

@SuppressWarnings("serial")
public class Word implements Serializable{
	String Name;
	int Page;
	public Word(String name, int page) {//Sta object tupou reference 8a apo8ukeuoume ta dedomena mias kataxorhshs sto le3iko
		Name = name;
		Page = page;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getPage() {
		return Page;
	}
	public void setPage(int page) {
		Page = page;
	}
	@Override
	public String toString() {//Xrhsimopoihte gia thn ektupwsh twn dedomenwn enos word
		return "|" + Name +"--- "  + Page + "|";
	}
	public boolean CompareWords(String s){//Xrhsimopoihte gia na sugkrinoume to onoma enos object Word me ena string
		int c;
		c=s.compareTo(Name);
		if(c==0){
			return true;
		}
		else{
			return false;
		}
	}
	public static Comparator<Word> WordNameComparator= new Comparator<Word>(){//Xrhsimopihte gia na sugkrinoume ta onomata 2 object Word oste na ta ta3inomhsoume
		
		public int compare(Word w1, Word w2){
			if(w1!=null && w2!=null){
			String w1Name=w1.getName().toUpperCase();
			String w2Name=w2.getName().toUpperCase();
			return w1Name.compareTo(w2Name);
			}
			return 0;
		}
	};
	public int length(){//Xrhsimopoihte gia thn eureush tou mhkous se bytes enos word
		int length=0;
		length=Name.length()+Integer.BYTES;
		return length;
	}
}