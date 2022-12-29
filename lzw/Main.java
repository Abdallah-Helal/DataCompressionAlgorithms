import java.util.*;
public class Main {
    public static Scanner scan = new Scanner(System.in) ;
    public static void main(String[] args) {
        Lzw encoder = new Lzw();
        char choice = 'Y' ; 
        int q ;
        while (choice == 'y' || choice == 'Y')
        {
            System.out.println("1-Encode txt");
            System.out.println("2-decode # of tags");
            q= scan.nextInt();
            if (q==1)
                encoder.encode();
            else 
                encoder.decode();
            System.out.println("Do you want to continue ? (y/n) ");
            choice = scan.next().charAt(0);
        }
    }
}
// ABAABABBAABAABAAAABABBBBBBBB
// ABAABABBAABAABAAAABABBBBBBBB
// 14
//65 66 65 128 128 129 131 134 130 129 66 138 139 138