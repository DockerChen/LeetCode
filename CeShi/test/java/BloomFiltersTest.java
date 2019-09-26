

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * BloomFilters Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/25/2019</pre>
 */
public class BloomFiltersTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
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
   Method method = BloomFilters.getClass().getMethod("hashcode_1", String.class); 
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
   Method method = BloomFilters.getClass().getMethod("hashcode_2", String.class); 
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
   Method method = BloomFilters.getClass().getMethod("hashcode_3", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    @Test
    public void bloomFilterTest() {
        long star = System.currentTimeMillis();
        BloomFilters bloomFilters = new BloomFilters(10000000);
        for (int i = 0; i < 10000000; i++) {
            bloomFilters.add(i + "");
        }
        Assert.assertTrue(bloomFilters.check(1 + ""));
        Assert.assertTrue(bloomFilters.check(2 + ""));
        Assert.assertTrue(bloomFilters.check(3 + ""));
        Assert.assertTrue(bloomFilters.check(999999 + ""));
        Assert.assertFalse(bloomFilters.check(400230340 + ""));
        long end = System.currentTimeMillis();
        System.out.println("Ö´ÐÐÊ±¼ä£º" + (end - star));
    }



} 
