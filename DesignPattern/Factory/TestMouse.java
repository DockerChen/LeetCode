package Factory;

public class TestMouse {
     public static void main(String[] args) {
         MouseFactory mf = new DellMouseFactory();
         Mouse m = mf.createMouse();
         m.sayHi();
     }



}
