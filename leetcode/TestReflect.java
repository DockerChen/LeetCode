import java.lang.reflect.*;

public class TestReflect {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class aClass = int.class;
        System.out.println(aClass);
        StringBuilder str = new StringBuilder("123");
        Class sclass = str.getClass();
        System.out.println(sclass);
        boolean res;
        if (str instanceof StringBuilder) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

        Class cc = String.class;
        Object s = cc.newInstance();
        System.out.println(s);

        //获取String所对应的Class对象
        Class<?> c = String.class;
//获取String类带一个String参数的构造器
        Constructor constructor = c.getConstructor(String.class);
//根据构造器创建实例
        Object obj = constructor.newInstance("23333");
        System.out.println(obj);
    }

}

