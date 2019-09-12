import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ScoreOrder {
    public static class User {
        public String name;
        public int score;

        public User(String name, int score) {
            this.name = name;
            this.score = score;
        }

    }

    public static class ScoreAscendingComparator implements Comparator<User> {
        @Override
        public int compare(User user1, User user2) {
            return user1.score - user2.score;

        }
    }

    public static class ScoreDescendingComparator implements Comparator<User> {
        @Override
        public int compare(User user1, User user2) {
            return user2.score - user1.score;

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, order;
        String name;
        int score;
        while (true) {
            n = scanner.nextInt();
            order = scanner.nextInt();
            User[] users = new User[n];
            for (int i = 0; i < n; i++) {
                name = scanner.next();
                score = scanner.nextInt();
                users[i] = new User(name, score);
            }
            if (order == 1) {
                Arrays.sort(users,new ScoreAscendingComparator());

            }else {
                Arrays.sort(users,new ScoreDescendingComparator());
            }

            for (int i = 0; i < n; i++) {
                System.out.println(users[i].name+" "+users[i].score);

            }
        }


    }
}
