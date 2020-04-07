import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int CACHE_SIZE;

    //设置缓存容量
    public LRUCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    //插入数据时，容量大于初始容量时，移除最近最少使用的entry
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > CACHE_SIZE;
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> lruCache = new LRUCache<>(10);
        System.out.println(lruCache.size());
        for (int i = 0; i < 20; i++) {
            lruCache.put(i, i);
            System.out.println(lruCache.size());
            System.out.println(lruCache);
        }
    }
}
