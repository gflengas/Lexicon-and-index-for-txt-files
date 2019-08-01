import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.*;
public class Generate {
	Word[] Lexicon=new Word[9];//Lexiko me arxiko mege8os 9 le3ewn
	int WordCount=0;
	List<Reference[]> Index = new ArrayList<Reference[]>(); //Eurethrio se morfh arraylist
	
	public void AddFile(String FileName) throws IOException{
		System.out.println("Importing from :"+FileName);
		String Line=null;
		boolean Exist,check;
		int ArrGro=9;
		String NextWord;
		long position=0;
		int page = 0;
		int i;
		RandomAccessFile Book =new RandomAccessFile(FileName,"r");//anoigma arxeiou gia na diabasei to periexomeno tou.
		while((Line=Book.readLine())!=null){//diabasma seira-seira
			Scanner s = new Scanner(Line);//xrhsh enos scanner gia na diabasma le3ewn apo ka8e seira pou metaferete apo to arxeio 
			position=Book.getFilePointer();//to potision xrhsimopoihte gia na 3eroume se pio byte tou keimenou briskomaste 
			while(s.hasNext()){//diabasma le3h-le3h mexri na ftasoume sto telos ths grammhs kai na diabasoume kainourgia 
				if (WordCount%9==0) {//an to le3iko einai gemato antigrafoume to periexomeno tou se ena neo megalhterou mege8ous gia na baloume mia nea kataxorhsh 
					Lexicon = Arrays.copyOf(Lexicon, Lexicon.length + ArrGro);
				}
				Exist=false;
				NextWord=s.next().toUpperCase();//apo8ukeush ths epomenhs le3hs se ena string kai metatroph tou se kefalea 
				NextWord=NextWord.replaceAll("[^a-zA-Z]+","");//aferesh apo sto string olwn twn idikwn xarakthrwn 
				for(Word word:Lexicon){//yagnei to le3iko gia na elen3ei ama uparxei idi h le3h 
					if(word!=null){
						check=word.CompareWords(NextWord);	
						if(check){//se periptosh pou uparxei apo8ukeuh thn selida sthn opoia uparxei ston Index
							Exist=true;
							page=word.getPage();
						}
					}
				}
				if(Exist){//Se periptosh pou h le3h uparxei idi
					Reference[] NewRef=new Reference[100];
					NewRef=Index.get(page);//metaferoume to periexomeno ths selida sthn opoia uparxei se mia  Object Array Reference
					i=0;
					for(Reference ref:NewRef){
						if(ref!=null){//Yagnoume na broume thn prwth kenh 8esh sto Object Array
							i++;
						}
						else{
							Reference Entry=new Reference(FileName,position,page);//dhmiourgoume ena neo Reference 
							NewRef[i]=Entry;//Kai to metaferoume sto Object Array to opoio apo8ukeuete automata logo ths leitougrias ths .get(int arg0) 
						}
					}
				}
				else{//Se periptosh pou h le3h den uparxei 
					Word NewWord =new Word(NextWord,WordCount);//Dhmiourgeia enos object Word 
					Lexicon[WordCount]=NewWord;//Eisodos ths le3hs sto le3iko
					Reference[] NewRef=new Reference[100];//dhmiourgia enos reference object array
					Reference Entry=new Reference(FileName,position,WordCount);//to opoio perilambanei thn 1h eisodo gia thn sugkekrimenh le3h 
					NewRef[0]=Entry;
					Index.add(NewRef);//sto eurethrio
					WordCount++;
				}
				position=position+NextWord.length();//pros8etoume to mikos ths le3hs se bytes sto position oste na exoume thn 8esh ths epomenhs le3hs 
			}
			s.close();
		}
		Book.close();
		
	}
	public void sorting(){//Ta3inomhsh tou Lexikou
		System.out.println("Sorting Lexicon...");
		Arrays.sort(Lexicon, Word.WordNameComparator);
	}
	public void GenerateLex() throws IOException{//Dhmiourgia tou arxeiou tou le3ikou
		long size=0;
		long fullsize=0;
		System.out.println("Generating Lexicon File...");
		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("Lexicon"));
		for(Word word:Lexicon){
			if (word!=null){//Metaferoume sto arxeio Lexicon oles tis word mia ka8e fora 
				size=size+word.length();//Xrhsimopoihte gia to elegxo poswn object xwraei akoma sthn selida 
				fullsize=fullsize+word.length();;
				if(size<=128){//ama ta sunoliko mhkos twn dedomenwn ths selidas den 3epernaei ta 128 bytes me thn eisodo mia neas le3hs tote sunexizoume sthn idia selida
					out.writeObject(word);
				}
				else{//allios allazoume selida 
					out.write('\n');//allagh selidas
					out.writeObject(word);
					size=word.length();
				}
			}
		}
		out.close();
		System.out.println("Lexicon size: " + fullsize + " bytes"); 
	}
	public void GenerateInd() throws IOException{
		System.out.println("Generating Index File...");
		long size=0;
		long fullsize=0;
		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("Index"));
		for(Reference[] ref:Index){
			for(Reference ins:ref){//Metaferoume sto arxeio Index oles tis Reference mia ka8e fora 
				if(ins!=null){
					size=size+ins.length();//Xrhsimopoihte gia to elegxo poswn object xwraei akoma sthn selida 
					fullsize=fullsize+ins.length();;
					if(size<=128){//ama ta sunoliko mhkos twn dedomenwn ths selidas den 3epernaei ta 128 bytes me thn eisodo mia neas Reference tote sunexizoume sthn idia selida
						out.writeObject(ins);
					}
					else{//allios allazoume selida 
						out.write('\n');//allagh selidas
						out.writeObject(ins);
						size=ins.length();
					}
				}
			}
		}
		out.close();
        System.out.println("Index size: " +fullsize  + " bytes");         
	}
	/*
	public void printAll() {//ektupwsh tou periexomenou twn lexicon kai index gia elegxo tou programmatos
		System.out.println("Printing Lexicon:");
		for(Word word:Lexicon){
			if (word!=null){
				System.out.println(word);
			}
		}
		System.out.println("Printing Index:");	 
		for(Reference[] ref:Index){
			for(Reference ins:ref){
				if(ins!=null){
					System.out.println(ins);
				}
			}
			System.out.println("----------------------------------------------------------");
		}
	}
	*/
}
