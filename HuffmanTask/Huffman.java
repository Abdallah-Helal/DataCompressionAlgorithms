import java.util.*;
import java.io.*;

class MyComparator implements Comparator<HuffmanNode> {
	public int compare(HuffmanNode x, HuffmanNode y)
	{

		return x.freq - y.freq;
	}
}

class HuffmanNode {

	int freq;
	char c;

	HuffmanNode left;
	HuffmanNode right;
}

public class Huffman
{
    private File in , out , f ;
	HashMap<Character,String> mp2 ;

	Huffman (String incodFrom , String decodedFrom , String decodedTo)
    {
		in = new File(decodedFrom) ;
        out = new File(decodedTo) ;
		f = new File(incodFrom) ;
		mp2 = new HashMap<Character,String>() ;

    }
	public void getCodes(HuffmanNode root , String s){
		if (root.left == null && root.right == null) {

			System.out.println(root.c + " " + s);
			mp2.put(root.c, s);
			

			return;
		}
		getCodes(root.left, s + "0" );
		getCodes(root.right, s + "1");
	}
    public void encode ()
    {
		
		int n;      char chr;   int freq;

        HashMap<Character,Integer> mp = new HashMap<Character,Integer>() ;

        Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        String txt=scan.next();
        n=txt.length();
        for (int i = 0; i < n; i++) {
            if(mp.get(txt.charAt(i))==null) mp.put(txt.charAt(i),0);
            else mp.put(txt.charAt(i),mp.get(txt.charAt(i))+1);
        }

        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(mp.size(), new MyComparator());
        for (Map.Entry<Character,Integer> entry : mp.entrySet()) {

			HuffmanNode hn = new HuffmanNode();

            chr= entry.getKey();
            freq= entry.getValue();
            
			hn.c = chr;
			hn.freq = freq;

			hn.left = null;
			hn.right = null;
			q.add(hn);
		}

		HuffmanNode root = null;
		while (q.size() > 1) {

			HuffmanNode x = q.peek();
			q.poll();

            HuffmanNode y = q.peek();
			q.poll();

			HuffmanNode newnode = new HuffmanNode();

			newnode.freq = x.freq + y.freq;
			newnode.c = '-';

			newnode.left = x;			newnode.right = y;

            root = newnode;
			q.add(newnode);
		}
		getCodes(root, "" );
		FileWriter w = null;
		try {
			w = new FileWriter(in);

			//write the encode to file  
			for (int i=0 ; i<n ; i++){
				w.write(mp2.get(txt.charAt(i)));
			}
			//write the table to file  
			for (Map.Entry<Character,String> entry : mp2.entrySet()) {	
				Character a= entry.getKey();
				String code= entry.getValue();
				w.write("\n" +a + " " + code ) ;
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
    public void decode (){
        Scanner scan=null;
        FileWriter writer = null ;
        try {
            scan = new Scanner (in);
			writer = new FileWriter(out) ;
        } catch (FileNotFoundException e2) {  e2.printStackTrace(); }
        catch (IOException e1) {  e1.printStackTrace(); }
        
        String txt = scan.next() ;	//get the encode 
        HashMap< String , String  > mp = new HashMap<>() ;

		// get the table
        while (scan.hasNextLine())
        {
			String x= scan.next();
            String code = scan.next() ;   
            mp.put(code, x) ;
            System.out.println( code + " "+  x) ;
        }
        for(int i=0 ; i< txt.length() ; i++)
        {
            String  tmp =String.valueOf(txt.charAt(i));
            while (i+1<txt.length() && mp.get(tmp) ==null)// the end of the string && 
            {
                i++ ;
                tmp += String.valueOf(txt.charAt(i) );
            }

            try {
				// System.out.print(mp.get(tmp));
                writer.write(mp.get(tmp)) ; //write the last temp or the last char 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
