package Ex1;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.function.Predicate;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Shahar and Or
 *
 */
public class Polynom implements Polynom_able
{
	private ArrayList<Monom>Polly; // Array List of Monoms

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() 
	{
		this.Polly=new ArrayList<Monom>();
	}
	/*
	public Polynom(Polynom p) {//copy constractor
		Polly = new ArrayList<Monom>();
		Iterator<Monom> it = p.iteretor();
		while(it.hasNext()) {
			Monom a = new Monom(it.next());
			add(a);
		}
	}
	 */
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "2*x^5+3x^7-2", "-2x^3", "-x+5"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) 
	{
		//test with while
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		int i = 0;

		s = s.replaceAll("\\s",""); // Remove Spaces

		String sign = ""; 
		if(s.charAt(0) == '-' || s.charAt(0) == '+')
		{
			sign = s.charAt(0) + "";
			s = s.substring(1); // remove first sign
		}

		boolean Finished = false;
		while (!s.isEmpty() && !Finished)
		{
			String tmp = sign + "";
			while(i < s.length() && s.charAt(i) != '+' && s.charAt(i) != '-')
			{
				tmp += s.charAt(i);
				i++;
			}
			Monom m_toAdd = new Monom(tmp);
			addMonomToPolly(monoms, m_toAdd);	//This Function will search equal power and add the monom
			if (i < s.length()) // Check we didn't finished the string
			{
				sign = s.charAt(i) + "";
				s = s.substring(i+1,s.length());
				i = 0;
			}
			else
				Finished = true;

		}
		monoms.sort(new Monom_Comperator());
		this.Polly = monoms;
		//printPolly(monoms); // Print a Polynom String from monoms Array list NEED TO DELETE

	}




	/**
	 *this function of type y=f(x), where both y and x are real numbers.
	 *@param x this is the value of x
	 *@return the value of this function on x
	 */
	@Override
	public double f(double x) 
	{
		double ans = 0;
		Iterator<Monom> runner = this.iteretor();
		while(runner.hasNext()) 
		{
			Monom m = runner.next();
			ans += m.f(x);
		}
		return ans;
	}
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 */
	@Override
	public void add(Monom m1) 
	{
		if (m1.get_coefficient() == 0)
			return;
		Iterator<Monom> runner = this.iteretor();
		while (runner.hasNext())
		{
			Monom m = runner.next();
			if (m.get_power() == m1.get_power()) 
			{
				try	
				{
					m.add(m1);
				}
				catch(Exception e) 
				{
					System.out.println(e.getMessage());
				}

				if (m.isZero()) 
				{
					Polly.remove(m);
				}
				return;
			}
		}
		this.Polly.add(m1);
		this.Polly.sort(new Monom_Comperator());
	}

	/**
	 * Add p1 to this Polynom
	 * @param add p1 to this polynom
	 */
	@Override
	public void add(Polynom_able p1) 
	{
		if (p1 instanceof Polynom)
		{
			Iterator<Monom> runner = p1.iteretor();
			while (runner.hasNext()) 
			{
				this.add(runner.next());
			}
			this.Polly.sort(new Monom_Comperator());
		}
		else
		{
			throw new IllegalArgumentException("p1 is not polynom");
		}
	}



	@Override
	public void substract(Polynom_able p1) 
	{
		// TODO Auto-generated method stub
		if (p1 instanceof Polynom)
		{
			Polynom_able p_tmp = p1.copy();
			p_tmp.multiply(new Monom(-1,0));
			Iterator<Monom> runner = p_tmp.iteretor();
			while (runner.hasNext())
			{
				this.add(new Monom(runner.next()));
			}
			this.Polly.sort(new Monom_Comperator());
		}
		else
		{
			throw new IllegalArgumentException("p1 is not polynom");
		}
	}
	@Override
	public void multiply(Monom m1) 
	{
		Iterator<Monom> runner = this.iteretor();
		Monom m2 = new Monom(m1);
		while(runner.hasNext())
		{
			runner.next().multipy(m2);
		}
		this.Polly.sort(new Monom_Comperator());
	}
	@Override
	public void multiply(Polynom_able p1) 
	{	
		// TODO Auto-generated method stub
		if(p1.isZero() || this.isZero())
		{
			Polly = new ArrayList<Monom>();
			Polly.add(new Monom(Monom.ZERO));
		}
		ArrayList<Monom> newMonoms = new ArrayList<Monom>();
		Polynom_able newp1 = (Polynom_able) p1.copy();

		for (Monom monom : Polly) {
			Iterator<Monom> itr = newp1.iteretor();
			while(itr.hasNext())
			{
				Monom m = new Monom(itr.next());
				m.multipy(monom);
				newMonoms.add(m);
			}
		}
		Polynom pol = new Polynom();
		for (Monom monom : newMonoms) {
			pol.add(monom);
		}
		this.Polly = pol.Polly;
		this.Polly.sort(new Monom_Comperator());
	}

	@Override
	public boolean equals(Object p1) 
	{
		// TODO Auto-generated method stub
		if(p1 instanceof Polynom_able)
		{
			Polynom_able p2 = (Polynom_able) p1;
			if(p2==null || this==null)
			{
				throw new RuntimeException("ERR one of the variables is null");
			}
			Iterator<Monom> itr = p2.iteretor();
			boolean ans = true ;
			int count = 0 ;
			while(itr.hasNext())
			{
				Monom m = new Monom(itr.next());

				if(count>=Polly.size()) 
				{
					return false;
				}
				else if(!Polly.get(count).equals(m))	
				{
					return false;
				}
				count++;
			}

			if(count!=Polly.size())
				return false;

			return ans;
		}
		else
		{
			if(p1 instanceof Monom)
			{
				Polynom m = new Polynom(p1.toString());
				return this.equals(m);
			}
			return false;
		}
	}

	@Override
	public boolean isZero() 
	{
		// TODO Auto-generated method stub
		Iterator<Monom> runner = this.iteretor();
		while (runner.hasNext())
		{
			if(!runner.next().isZero()) // At least 1 monom which is not zero founded
			{
				return false; // so its not zero polynom
			}
		}		
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) 
	{
		// TODO Auto-generated method stub
		if (f(x0) * f(x1) <= 0) // Different Signs, on and above the x pivot
		{
			double mid_x = (x1 + x0) / 2;
			if (Math.abs(f(mid_x)) <= eps) 
				return mid_x;
			if (f(x0) == 0) 
				return x0;
			if (f(x1) == 0) 
				return x1;

			// set the x with the same sign as mid_x as mid_x
			if (f(mid_x) * f(x1) > 0) // same sign
				x1 = mid_x;
			else
				x0 = mid_x;
		}
		else // There is no 1 postive and 1 negative.. can't find root..
		{
			throw new IllegalArgumentException("The array is invalid ");
		}
		return root(x0, x1, eps);
	}

	@Override
	public Polynom_able copy() 
	{
		// TODO Auto-generated method stub
		Iterator<Monom>runner=this.iteretor();
		Polynom p1=new Polynom();
		while(runner.hasNext()) 
		{
			p1.add(new Monom(runner.next()));
		}
		return p1;
	}

	@Override
	public Polynom_able derivative() 
	{
		// TODO Auto-generated method stub
		Iterator<Monom>runner=this.iteretor();
		Polynom p1=new Polynom();
		while(runner.hasNext()) 
		{
			Monom m = runner.next().derivative();
			if (m.get_coefficient() != 0 && m.get_power() >= 0)
				p1.add(m);
		}
		return p1;
	}


	// My Extra Functions
	/*
	public static void printPolly(ArrayList<Monom> monoms) //Didn't used it
	{
		// Print a Polynom String from monoms Array list
		String str = monoms.toString();
		str = str.replaceAll("\\, ", "+");
		str = str.replaceAll("\\*", "");
		str = str.replaceAll("\\+-", "-");
		str=str.substring(1,str.length()-1); // remove [ and ]
		if(str.charAt(0)=='+') 
			str=str.substring(1,str.length()-2);
		System.out.println(str);
	}
	*/
	/**
	 * This function tries first to add the monom if his power already exist in the polynom array
	 * @param monoms - The Monoms ArrayList
	 * @param monom_to_add - The new monom we wants to add
	 */
	public static void addMonomToPolly(ArrayList<Monom> monoms, Monom monom_to_add)
	{
		Iterator<Monom> runner = monoms.iterator();
		boolean monomAddedToPolly = false;
		while(runner.hasNext() && !monomAddedToPolly) // try add the monom if the power is already exist
		{
			Monom m_runner = runner.next();
			if (m_runner.get_power() == monom_to_add.get_power())
			{
				m_runner.add(monom_to_add);
				monomAddedToPolly = true;
			}
		}
		if (!monomAddedToPolly) // the power is not exist in polly, add the monom to the arraylist
		{
			monoms.add(monom_to_add); 
		}
	}
	@Override
	public double area(double x0, double x1, double eps) 
	{
		// TODO Auto-generated method stub
		if (x0 >= x1)
			return 0;
		if (eps <=0)
			return 0;
		if (eps > Math.abs(x1-x0))
			return 0;
		double sum = 0;
		for (double step = x0; step <= x1; step += eps) 
		{
			if (f(step) >= 0)
				sum += eps * f(step);
		}
		return Math.abs(sum);
	}


	@Override
	public Iterator<Monom> iteretor() 
	{
		// TODO Auto-generated method stub
		return this.Polly.iterator();
	}
	/**
	 * The representation of any monom in the Polynom is in this format: " a*x^b "
	 * I chose to display the power even if its 1, and I always show the multiply '*' between a and x.
	 */
	@Override
	public String toString() 
	{
		//StringBuilder sb = new StringBuilder();
		String s ="";
		Iterator <Monom> runner = this.iteretor();
		while(runner.hasNext()) 
		{
			Monom m = runner.next();
			if(!m.isZero()) 
			{
				s+="+"+m.toString();
				//sb.append("+"+m.toString());
			}
		}

		if(s=="") s="0";
		if(s.charAt(0) == '+')
			s = s.substring(1);
		s = s.replace("+-", "-");
		return s;
		
	}
	@Override
	public function initFromString(String s) 
	{
		// TODO Auto-generated method stub
		function p = new Polynom(s);
		return p;
	}

}
