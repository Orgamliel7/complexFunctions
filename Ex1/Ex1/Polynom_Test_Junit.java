package Ex1;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import java.text.DecimalFormat;


class Polynom_Test_Junit {


	/*
	 * 1)  substract test
	 * 2)  add test
	 * 3)  test_f test
	 * 4)  multiply test
	 * 5)  isZero test
	 * 6)  root test
	 * 7)  derivative test
	 * 8)  area test
	 * 9)  generalTest1
	 * 10) generalTest2
	 * 
	 * 
	 */




	@Test

	public void testsubstract() throws Exception      //J-unit good test for substract

	{  

		Polynom_able p1= new Polynom("2x^5+3x^3-2");

		Polynom_able p2= new Polynom("2x^4-1x^3+6x^2+5");

		p1.substract(p2);

		assertEquals(p1.toString(),"2.0*x^5-2.0*x^4+4.0*x^3-6.0*x^2-7.0");

		assertNotEquals(p1.toString(), "3x^4-1x^3+2x^2-11x+7");		

	}


	@Test

	public void testadd() throws Exception               //J-unit good test for add
	{         


		Polynom_able p1= new Polynom("5x^5+3x^3-2");
		Polynom_able p2= new Polynom("3x^5+2x^3+6+2x^2");

		p1.add(p2);

		assertEquals(p1.toString(),"8.0*x^5+5.0*x^3+2.0*x^2+4.0");


	}

	@Test

	public void testf() throws Exception              //J-unit good test for test_f
	{      

		Polynom_able p= new Polynom("0.2x^4-1.5x^3+3x^2-1x^1-5");

		double actual= p.f(1);

		double expected= -4.3;

		assertEquals(expected, actual);

	}


	@Test

	public void testmultiply() throws Exception                   //J-unit good test for multiply
	{      

		Polynom_able p1= new Polynom("5x^5+3x^3-2");

		Polynom_able p2= new Polynom("8x^4-1x^3+2x^2-11x+7");

		p1.multiply(p2);;

		assertNotEquals(p1.toString(), "5.0x^5-8.0x^4+4.0x^3-2.0x^2+11.0x-9.0");			

	}


	@Test

	public void testIsZero() throws Exception          	   //J-unit good test for IsZero
	{
		Polynom_able p1= new Polynom("3x^4+6x^3-2");

		Polynom_able p2= new Polynom();

		boolean Test_1= p1.isZero();

		boolean Test_2= p2.isZero();

		assertFalse(Test_1);

		assertTrue(Test_2);

	}


	@Test

	public void testroot() throws Exception            		//J-unit good test for IsZero
	{
		Polynom_able p1= new Polynom("4x^3+2x^2+11x+23");

		double temp= p1.root(-2, 0, 0.01);

		double ans_root= -1.4189453125;

		assertEquals(ans_root, temp);




	}

	@Test

	public void testderivative() throws Exception                      //J-unit good test for derivative
	{            

		Polynom_able p1= new Polynom("3x^4-2x^3+2x^2");

		p1=p1.derivative();
		assertEquals(p1.toString(),"12.0*x^3-6.0*x^2+4.0*x^1");

		Polynom_able p2= new Polynom("6x^8-2x^5+2x^1");

		p2=p2.derivative();
		assertEquals(p2.toString(),"48.0*x^7-10.0*x^4+2.0");

		Polynom_able p3= new Polynom("20x^3-7x^2-5");

		p3=p3.derivative();
		assertEquals(p3.toString(),"60.0*x^2-14.0*x^1");

		Polynom_able p4= new Polynom("215x^70+3x^6");

		p4=p4.derivative();
		assertEquals(p4.toString(),"15050.0*x^69+18.0*x^5");
	}



	@Test


	void testArea()                                               //J-unit good test for area

	{
		double eps = Monom.EPSILON;
		Polynom_able p1 = new Polynom("x");
		double x1 = 0;
		double x2 = 4;
		double area = p1.area(x1, x2, eps);
		double ans = 7.999999797380996;
		String str = Double.toString(ans);
		double expected = Double.parseDouble(str);
		if(Math.abs(ans-expected)>eps)
		{
			fail("Should be: "+ans +" but was" + area);
		}

	}

	@Test
	
	void generalTest1()                                               //J-unit good test for generalTest1

	{
		Polynom p1 = new Polynom("1 + x^7+3.4x^5");
		Polynom p2 = new Polynom("x");
		Polynom p3 = new Polynom("x^2-5*X^2");
		Polynom p4 = new Polynom("0.5x^3+3*x^3+x+1x+7+8");
	
		assertEquals(p1.toString(),"1.0*x^7+3.4*x^5+1.0");
		assertEquals(p2.toString(),"1.0*x^1");
		assertEquals(p3.toString(),"-4.0*x^2");	
		assertEquals(p4.toString(),"3.5*x^3+2.0*x^1+15.0");
		
		
	}

@Test
	
	void generalTest2()                                               //J-unit good test for generalTest2

	{
	Polynom p1 = new Polynom(), p2 =  new Polynom();
	String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
	String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
	for(int i=0;i<monoms1.length;i++) 
	{
		Monom m = new Monom(monoms1[i]);
		p1.add(m);
	}
	for(int i=0;i<monoms2.length;i++) 
	{
		Monom m = new Monom(monoms2[i]);
		p2.add(m);
	}
	
		assertEquals(p1.toString(),"-4.7*x^2-1.0*x^1+6.0");
		assertEquals(p2.toString(),"1.7000000000000002*x^2+1.7*x^1+2.0");
		p1.add(p2);
		assertEquals(p1.toString(),"-3.0*x^2+0.7*x^1+8.0");
		p1.multiply(p2);
		assertEquals(p1.toString(),"-5.1000000000000005*x^4-3.9099999999999997*x^3+8.790000000000001*x^2+15.0*x^1+16.0");
		String s1 = p1.toString();
		Polynom_able pp1 = new Polynom(s1); 
		assertEquals(pp1.toString(),"-5.1000000000000005*x^4-3.9099999999999997*x^3+8.790000000000001*x^2+15.0*x^1+16.0");

		
		
	}

/*


	
	*/
	
	


}
