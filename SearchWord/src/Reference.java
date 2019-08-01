import java.io.Serializable;

@SuppressWarnings("serial")
public class Reference implements Serializable{
	String FileName;
	long Potition;
	int Page;
	public Reference(String fileName, long potition, int page) {//Sta object tupou reference 8a apo8ukeuoume ta dedomena mias kataxorhshs sto eurethrio
		super();
		FileName = fileName;
		Potition = potition;
		Page = page;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public long getPotition() {
		return Potition;
	}
	public void setPotition(long potition) {
		Potition = potition;
	}
	public int getPage() {
		return Page;
	}
	public void setPage(int page) {
		Page = page;
	}
	@Override
	public String toString() {//Xrhsimopoihte gia thn ektupwsh twn dedomenwn enos reference
		return "(" + FileName +"---" + Potition   + ")";
	}
	public int length(){//Xrhsimopoihte gia thn eureush tou mhkous se bytes enos reference 
		int length=0;
		length=FileName.length()+Integer.BYTES+Long.BYTES;
		return length;
	}
}
