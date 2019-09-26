public class Test extends Base {

    static {
        System.out.println("test static");
    }

    public Test() {
        System.out.println("test constructor");
    }

    public static void main(String[] args) {
        new Test();
    }
}

class Base {

    static {
        System.out.println("base static");
    }

    public Base() {
        System.out.println("base constructor");
    }
}
//
//class MyObject{
//    private static String str1="staticProperty";
//    private String str2="property";
//    public MyObject(){
//
//    }
//
//    public void print1(){
//        System.out.println(str1);
//        System.out.println(str2);
//
//    }
//
//    public static void print2(){
//        System.out.println(str1);
//        System.out.println(str2);
//        print1();
//    }
//
//
//}