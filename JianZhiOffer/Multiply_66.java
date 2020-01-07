public class Multiply_66 {
    public int[] multiply(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        }
        int[] B = new int[A.length];
        int[] C = new int[A.length];
        int[] D = new int[A.length];
        C[0] = 1;
        D[D.length - 1] = 1;
        for (int i = 1; i < C.length; i++) {
            C[i] = C[i - 1] * A[i - 1];
        }
        for (int i = D.length - 2; i >= 0; i--) {
            D[i] = D[i + 1] * A[i + 1];
        }

        for (int i = 0; i < B.length; i++) {
            B[i] = C[i] * D[i];
        }

        return B;


    }

}
