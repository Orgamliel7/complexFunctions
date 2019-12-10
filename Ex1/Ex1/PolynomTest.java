package Ex1;

public class PolynomTest 
{
	public static void main(String[] args) 
	{
		test1(); // works
		test2(); // works
		test3(); // works
	}
	public static void test1() 
	{
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) 
		{
			Monom m = new Monom(monoms[i]);
			p1.add(m);
			double aa = p1.area(0, 1, 0.0001);
			p1.substract(p1);
			System.out.println(p1.toString());
		}
	}
	public static void test2() 
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
		System.out.println("p1: "+p1.toString());
		System.out.println("p2: "+p2.toString());
		p1.add(p2);
		System.out.println("p1+p2: "+p1); 

		p1.multiply(p2); 
		System.out.println("(p1+p2)*p2: "+p1.toString());
		
		
		String s1 = p1.toString();
		Polynom_able pp1 = new Polynom(s1); 
		System.out.println("from string: "+pp1.toString());
	}

	public static void test3()
	{
		Polynom p1 = new Polynom("1 + x^7+3.4x^5");
		Polynom p2 = new Polynom("x");
		Polynom p3 = new Polynom("x^2-5*X^2");
		Polynom p4 = new Polynom("0.5x^3+3*x^3+x+1x+7+8");
		//String[] monoms = {"1","x","x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		System.out.println();
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());
		System.out.println(p4.toString());
	}

}
