import java.util.HashMap;
import java.util.Map;

public class TestClass {
    public static void main(String[] args) {
        Map<Character,Integer> map=new HashMap(500);
        map.put('c',1);
        map.put('c',2);

//   创建静态内部类对象的一般形式为：
//      外部类类名.内部类类名 xxx = new 外部类类名.内部类类名()
//   创建成员内部类对象的一般形式为：
//      外部类类名.内部类类名 xxx = new 外部类类名.new 内部类类名()
        // 初始化Bean1
        Bean1 bean1 = new TestClass().new Bean1();
        bean1.I++;
        // 初始化Bean2
        Bean2 bean2 = new TestClass.Bean2();
        bean2.J++;
        //初始化Bean3
        Bean.Bean3 bean3 = new Bean().new Bean3();
        bean3.k++;
    }

    class Bean1 {
        public int I = 0;
    }

    static class Bean2 {
        public int J = 0;
    }
}

class Bean {
    class Bean3 {
        public int k = 0;
    }
}