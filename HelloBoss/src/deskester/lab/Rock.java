package deskester.lab;

import java.util.regex.Pattern;

public class Rock {

	/**
	 * @param args
	 */
	static String rtitle="New Folder";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Rock rock =new Rock();
		rock.getRenamedFolderTitle("(34)afasasd");*/
		if(rtitle.contains("New folder")){
	        System.out.println("rtitle==>"+rtitle);
    	}else{
    		System.out.println("ELSErtitle==>"+rtitle);
    	}

	}

	
	 public String getRenamedFolderTitle(String title) {
	        String[] sp = title.split(Pattern.quote("("));
	        String renamedTitle = "";
	        int len = sp.length;
	        String lastSeg = sp[len-1];
	        System.out.println("lastSeg==>"+lastSeg);
	         for(int i=0;i <len-1;i++) {
	        	 renamedTitle = renamedTitle + sp[i]+"(";
	        	 System.out.println("FOR---LOOPrenamedTitle==>"+renamedTitle);
	         }
	         int lastIndex = lastSeg.lastIndexOf(')');
	         int count = 0;
	         String appendLastSeg = "";
	         if(lastIndex != -1 && lastIndex !=0) {
		         String last = lastSeg.substring(0,lastIndex);
		         count = Integer.parseInt(last);
		         count++;
		         appendLastSeg = count+")";
		         renamedTitle = renamedTitle+appendLastSeg;
		        
	         }else {
	        	 lastSeg = lastSeg+"(1)";
	        	 renamedTitle = renamedTitle + lastSeg;
	        	 
	         }
	         System.out.println("renamedTitle==>"+renamedTitle);
	         return renamedTitle;
	    }
}
