package stringExtractor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListGPS {
    private List<GPS> lst;
    ListGPS(List<GPS> lst){
       this.lst=lst;
    }
    public String analysisGPS(){
        List<List<GPS>> lstGPS=new ArrayList<List<GPS>>();

        while (!lst.isEmpty()){
            List<GPS> lsttemp =new ArrayList<GPS>();
            lsttemp.add(lst.get(0));
            lst.remove(0);
            int i=0;
            while (i<lst.size())
                if (Calculater(lsttemp.get(0),lst.get(i))){
                    lsttemp.add(lst.get(i));
                    lst.remove(i);
                }
                else i+=1;
            lstGPS.add(lsttemp);
        }
        return topGPS(lstGPS);
    }

    public boolean Calculater(GPS center,GPS pointOut){
        return     Math.pow((center.getKinhDo()-pointOut.getKinhDo()),2)+
                Math.pow((center.getViDo()-pointOut.getViDo()),2)<=Math.pow(0.001,2);
    }
    public String topGPS(List<List<GPS>> lst){
    	
    	if(lst.size()!=0)
    	{
    	
           lst.sort(new Comparator<List<GPS>>() {
               @Override
               public int compare(List<GPS> o1, List<GPS> o2) {
                   return Integer.compare(o2.size(),o1.size());
               }
           });
	        for (List<GPS> item:lst)   System.out.println(item.size());;
	        String result="";
	        for(int i=0;i<3;i++) {
	        	double KinhDo=0.0,ViDo=0.0;
	        for (GPS item:lst.get(i)){
	            KinhDo+=item.getKinhDo();
	            ViDo+=item.getViDo();
	        }
	        
	        result+=KinhDo/lst.get(0).size()+"|"+ViDo/lst.get(0).size();
	        if(i>=lst.size()-1||i==2)break;
	        else result+="-";
	        }
	       
	        return result;
    	}
    	else {
    		return "9999|9999";
    	}
        
    }
}
