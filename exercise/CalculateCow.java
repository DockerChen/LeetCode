public class CalculateCow {

    public static int calculateCow(int year) {
        if (year == 1) {
            return 1;
        }
        if (year == 2) {
            return 2;
        }
        if (year == 3) {
            return 3;
        }
        
        return calculateCow(year - 1) + calculateCow(year - 3);

    }

    public static void main(String[] args) {
        System.out.println(calculateCow(4));
        System.out.println(calculateCow(5));
        System.out.println(calculateCow(6));

    }
}
