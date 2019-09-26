

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Calculator Tester. 
* 
* @author <Authors name> 
* @since <pre>09/25/2019</pre> 
* @version 1.0 
*/ 
public class CalculatorTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: calculate(int a, int b) 
* 
*/ 
@Test
public void testCalculate() throws Exception { 
//TODO: Test goes here...
    Calculator calculator=new Calculator();
    int sum= calculator.calculate(1,2);
    Assert.assertEquals(4,sum);

} 


} 
