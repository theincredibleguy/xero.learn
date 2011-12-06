package pred.console;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.List;

import pred.datasource.SparseIndex;

public class PredConsole {
	
	private SparseIndex si = new SparseIndex();
	
	public SparseIndex getSparseIndex(){
		return si;
	}
	
	
	public static void main(String[] args) throws Exception{
		
		PredConsole pc = new PredConsole();
		HashMap<Integer, String> options = new HashMap<Integer, String>();
		
		BufferedInputStream bis = new BufferedInputStream(System.in); 
		int x;
		List<String> list = null;
		StringBuffer searchedString = new StringBuffer();
		StringBuffer temp = new StringBuffer();
		boolean searched = false;
		
		while((x=bis.read())!=-1){
			
			if(bis.available()>0){
				searched = false;
				temp.append((char)x);
			}
			else if(!searched){
				System.out.println("==" + temp.toString());
				list = pc.getSparseIndex().search(temp.toString());
				if(list.size() > 0){
					int index = 0;
					for(int i=0;i<list.size();i++){
						options.put(index, list.get(i));
						System.out.println(index + " " + options.get(index));
						index++;
					}
					int selectedIndex = -1;
					byte[] buffer = new byte[1];
					while(!options.containsKey(selectedIndex)){
						System.out.print("Select an option : ");
						bis.read(buffer);
						selectedIndex = Integer.parseInt((char)buffer[0]+"");
					}
					String selectedValue = options.get(selectedIndex);
					temp.append(selectedValue.substring(1));
					System.out.print(temp.toString());
					pc.getSparseIndex().resetSparseModel(selectedValue.substring(1));
					searched=true;
					searchedString.append(temp.toString());
					temp.delete(0, temp.toString().length());
				}
			}
		}
	}
}
