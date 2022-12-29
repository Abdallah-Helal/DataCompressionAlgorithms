import java.util.*;
 
public class Lzw
{
    public static Scanner scan = new Scanner(System.in) ;
    public void encode ()
    {
        HashMap<String,Integer> mp = new HashMap<String,Integer>() ;
        for (int i=0 ; i<128 ; i++)
            mp.put(String.valueOf((char) i) , i) ;
        System.out.print("Enter the text :");
        String txt=scan.next();
        int i =0 , k=128 , mxtag = 0 , tagsNum=0;
        while (i<txt.length())
        {
            String tmp = "" ;
            tmp+=txt.charAt(i) ;
            while (i+1<txt.length() && mp.get(tmp+txt.charAt(i+1)) != null)
            {
                i++;
                tmp+=txt.charAt(i) ;
            }
            int tag =mp.get(tmp)  ;
            System.out.print(tag + " ");
            mxtag = Math.max(mxtag, tag);
            tagsNum++ ;
            if(i+1<txt.length())
            {
                tmp+=txt.charAt(i+1);
                mp.put(tmp,k) ;
            }
            k++;
            i++;
        }
        System.out.println(" ") ;
        double tagSize = Math.floor( Math.log10(mxtag)/ 0.3010299 )+1;
        int compressedSize = (int)(tagsNum * tagSize)  ;
        int originalSize = 8*txt.length() ;
        double ratio = 1.0*compressedSize /originalSize ;
        System.out.println("tag size =" + tagSize + "bits") ;
        System.out.println("original size =" + originalSize + "bits") ;
        System.out.println("compressed size =" + compressedSize + "bits") ;
        System.out.println("Ratio = " + ratio) ;


    }
    public void decode ()
    {
        HashMap<Integer,String> mp = new HashMap<Integer,String>() ;
        for (int i=0 ; i<128 ; i++)
            mp.put( i, String.valueOf((char) i)) ;
        System.out.print("Enter the # tags :");
        int n= scan.nextInt() ;
        int k=128 ;
        int x = scan.nextInt() ; // first char in txt
        String txt=mp.get(x)  , prev=txt ;
        while( --n >0)
        {
            x = scan.nextInt();
            if (mp.get(x)== null)
                mp.put(k, prev+prev.charAt(0));
            else     
                mp.put(k, prev+mp.get(x).charAt(0));    
            txt+=mp.get(x) ;
            prev=mp.get(x) ;
            k++;
        }
        System.out.println(txt);
    }
}
