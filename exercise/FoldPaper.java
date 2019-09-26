import java.util.Scanner;

public class FoldPaper {

    public static void foldPaper(int n) {

        inOrder(1, n, true);

    }

    public static void inOrder(int i, int n, boolean down) {
        if (i > n) {
            return;
        } else {
            inOrder(i + 1, n, true);
            System.out.println(down ? "down" : "up");
            inOrder(i + 1, n, false);
        }

    }

    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        while ((n = scanner.nextInt()) > 0) {
            foldPaper(n);
        }

    }
}
