import java.util.*;
public class Main {
    public static Scanner scan = new Scanner(System.in) ;
    public static void main(String[] args) {
        Huffman x = new Huffman("incodeFrom.txt" ,"incodeTo-decodeFrom.txt" , "decodeTo.txt");
        x.encode();
        x.decode();
       
    }
}
