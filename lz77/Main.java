import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Character choice = 'y';
        int type;
        Encoder encoder = new Encoder();
        while (choice == 'y' || choice == 'Y') {
            System.out.println("1-Encode a text");
            System.out.println("2-Decode number of tags");
            type = scan.nextInt();
            if (type == 1)
                encoder.encode();
            else
                encoder.decode();
            System.out.print("do you want to continue (y/n) : ");
            choice = scan.next().charAt(0);
        }
        Encoder e = new Encoder();
        e.decode();
    }
}