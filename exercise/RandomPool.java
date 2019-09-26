import java.util.HashMap;

//泛型K，可以表示任意一种类型
public class RandomPool<K> {

    private HashMap<K, Integer> keyIndexMap;
    private HashMap<Integer, K> indexKeyMap;
    private int size;

    public RandomPool() {
        keyIndexMap = new HashMap<>();
        indexKeyMap = new HashMap<>();
        size = 0;
    }

    public void insert(K key) {
        if (!keyIndexMap.containsKey(key)) {
            //put方法会覆盖当前key对于的value值
            keyIndexMap.put(key, this.size);
            indexKeyMap.put(this.size++, key);

        }
    }

    public void delete(K key) {
        if (keyIndexMap.containsKey(key)) {
            int deleteIndex = keyIndexMap.get(key);
            int lastIndex = --size;
            K lastKey = indexKeyMap.get(lastIndex);

            keyIndexMap.put(lastKey, deleteIndex);
            indexKeyMap.put(deleteIndex, lastKey);

            keyIndexMap.remove(key);
            indexKeyMap.remove(lastIndex);

        }

    }

    public K getRandom() {
        if (size == 0) {
            return null;
        }
        int randomIndex = (int) (Math.random() * size);
        return indexKeyMap.get(randomIndex); // [0,size)

    }

    public static void main(String[] args) {
        RandomPool randomPool = new RandomPool();

        randomPool.insert("a");
        randomPool.insert("b");
        randomPool.insert("c");
        randomPool.insert(123);
        System.out.println(randomPool.getRandom());
        System.out.println(randomPool.getRandom());
        System.out.println(randomPool.getRandom());
        System.out.println(randomPool.getRandom());
        System.out.println(randomPool.getRandom());
        System.out.println(randomPool.getRandom());
    }

}
