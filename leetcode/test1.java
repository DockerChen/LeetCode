import java.lang.reflect.*;

public class test1 {



    public static void test() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> c = methodClass.class;
        Class<?> i = methodClass.AA.class;


        Object object = c.newInstance();
        Method[] methods = c.getMethods();
        Method[] declaredMethods = c.getDeclaredMethods();
        //获取methodClass类的add方法
        Method method = c.getMethod("add", int.class, int.class);
        //getMethod()方法获取的所有方法
        System.out.println("getMethod获取的方法：");
        System.out.println(method);
        //getMethods()方法获取的所有方法
        System.out.println("getMethods获取的方法：");
        for (Method m : methods)
            System.out.println(m);
        //getDeclaredMethods()方法获取的所有方法
        System.out.println("getDeclaredMethods获取的方法：");
        for (Method m : declaredMethods)
            System.out.println(m);

        Field[] fields = c.getFields();
        Field[] declaredFields = c.getDeclaredFields();
        //getFields()方法获取的所有方法
        System.out.println("getFields获取的方法：");
        for (Field f : fields)
            System.out.println(f);

        //getDeclaredFields()方法获取的所有方法
        System.out.println("getDeclaredFields获取的方法：");
        for (Field f : declaredFields)
            System.out.println(f);

    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        test();
    }
}

class methodClass {
    interface AA {
        int gg = 1;
        String name = "hello";

        void AA();
    }

    public final int fuck = 3;
    private boolean flag = true;
    private static int num = 1;

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a + b;
    }
    private void print(int a){
        System.out.println(a);
    }
}