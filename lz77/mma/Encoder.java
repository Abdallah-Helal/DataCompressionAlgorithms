import java.util.Scanner;

public class Encoder {
    public Scanner scan = new Scanner(System.in);

    public String decode() {
        Character a;
        int steps, size, n;
        System.out.print("Enter # of tags :");
        n = scan.nextInt();
        String rslt = "";
        while (n-- > 0) {
            steps = scan.nextInt();
            size = scan.nextInt();
            a = scan.next().charAt(0);
            if (steps != 0) {
                int beg = rslt.length() - steps;
                for (int i = beg; i < beg + size; i++)
                    rslt += rslt.charAt(i);
            }
            rslt += a;
        }
        System.out.println(rslt);
        return rslt;
    }

    public void encode() {
        int searchwindow, lookahead;
        String s;

        Scanner sc = new Scanner(System.in);
        // take the SearchWindow && lookAhead
        searchwindow = sc.nextInt();
        lookahead = sc.nextInt();
        s = sc.next();
        for (int i = 0; i < s.length();) {
            String temp = "";
            int cnt = 0;
            boolean flag = true;
            int firstch = -1;
            temp += s.charAt(i);

            while (flag) {
                flag = false;
                for (int j = Math.max(0, i - searchwindow); j < i; j++) {
                    String substr = "";
                    for (int k = j; k < j + temp.length(); k++) {
                        if (k == i)
                            break;
                        substr += s.charAt(k);
                    }
                    if (temp.equals(substr)) {
                        firstch = j;
                        flag = true;
                    }
                }
                if (flag) {
                    cnt++;
                    if (cnt + 1 == lookahead) {
                        flag = false;
                        continue;
                    }
                    temp += s.charAt(i + cnt);
                } else {
                    cnt++;
                    i += cnt;
                }
            }

            if (firstch == -1) {
                System.out.println("0 0 " + s.charAt(i - 1) + "\n");
            } else {
                int back = i - (temp.length() - 1);
                back -= firstch;
                System.out.println(
                        (back - 1) + " " + (temp.length() - 1) + " " + temp.charAt(temp.length() - 1) + "\n");
            }
        }

    }
}