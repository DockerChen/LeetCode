package test;


import class_04.Code_01_PreInPosTraversal;
import class_06.Code_01_BFS;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TestBloomFilter Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>9月 24, 2019</pre>
 */
public class TestBloomFilterTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: hashMapTest()
     */
    @Test
    public void testHashMapTest() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: bloomFilterTest()
     */
    @Test
    public void testBloomFilterTest() throws Exception {
//TODO: Test goes here...

        long star = System.currentTimeMillis();
        TestBloomFilter.BloomFilters bloomFilters = new TestBloomFilter.BloomFilters(10000000);
        for (int i = 0; i < 1000000; i++) {
            bloomFilters.add(i + "");
        }
        Assert.assertTrue(bloomFilters.check(1 + ""));
        Assert.assertTrue(bloomFilters.check(2 + ""));
        Assert.assertTrue(bloomFilters.check(3 + ""));
        Assert.assertTrue(bloomFilters.check(999999 + ""));
        Assert.assertFalse(bloomFilters.check(400230340 + ""));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: add(String key)
     */
    @Test
    public void testAdd() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: check(String key)
     */
    @Test
    public void testCheck() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: hashcode_1(String key)
     */
    @Test
    public void testHashcode_1() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = TestBloomFilter.getClass().getMethod("hashcode_1", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: hashcode_2(String data)
     */
    @Test
    public void testHashcode_2() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = TestBloomFilter.getClass().getMethod("hashcode_2", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: hashcode_3(String key)
     */
    @Test
    public void testHashcode_3() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = TestBloomFilter.getClass().getMethod("hashcode_3", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
