
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Shahar and Or
 *
 */
public class Monom implements function
{
	private double _coefficient; // a - real number
	private int _power; // b - none negative number

	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b)
	{
		if (b > 0) // Everything is good :)
		{
			this.set_coefficient(a);
			this.set_power(b);
		}
		if (b == 0) // Power is zero, X==1
		{
			this.set_coefficient(a);
		}

		if (b < 0) // power needs to be not negative
		{
			throw new IllegalArgumentException("exponent cannot be negative: " + b);
		}
	}
	public Monom(Monom ot) 
	{
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this. // Nigzeret
	 * @return
	 */
	public Monom derivative() 
	{
		if(this.get_power()==0) 
		{return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) 
	{
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public boolean checkString(String s)
	{
		if(s.matches("[+-]?[0-9]*[.]?[0-9]*\\*?[xX]?\\^?[0-9]*")) // regular expression to check monom pattern
		{
			if (s.contains(".x") || s.equals("^") || s.equals("+") || s.equals("-") || s.charAt(s.length()-1) == '^') // some special cases
				return false;
			else
				return true;
		}
		else
			return false;
	}
/**
 * Make monom from the string, good examples: {"2x^2", "+3*x^7", "X", "-2x", "12.0"}
 * Bad examples {"2x^2+1", "*x^7", "X", "-2^4x"}
 * @param s represents string for monom
 */
	public Monom(String s) // the string format: a*x^b , a - real number, b - not negative
	{
		s = s.replaceAll("\\s", ""); // Remove Spaces

		if (s == null || s.isEmpty() || !checkString(s)) 
		{
			throw new IllegalArgumentException("The String is inValid..");
		}
		else 
		{


			s = s.toLowerCase();
			int indexof_multy = s.indexOf('*');
			int indexof_x = s.indexOf('x');
			int indexof_pow = s.indexOf('^');

			/*
			int a1,b1;
			try {a1 = Integer.parseInt(a);}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			 */
			if (!s.contains("x")) // If the Monom is a free number
			{
				this._power = 0;
				try
				{
					this._coefficient = Double.parseDouble(s); //try it
				}
				catch(Exception e) 
				{
					throw new IllegalArgumentException("The String is inValid..");
				}

			}
			if (s.equals("x") || s.equals("+x")) 
			{
				this._coefficient = 1;
				this._power = 1;
			}
			else if (s.equals("-x")) 
			{
				this._coefficient = -1;
				this._power = 1;
			} else 
			{
				if (s.contains("*") && s.contains("^")) 
				{
					try
					{
						this._coefficient = Double.parseDouble(s.substring(0, indexof_multy));
						this._power = Integer.parseInt(s.substring(indexof_pow + 1, s.length()));
					}
					catch(Exception e)
					{
						throw new IllegalArgumentException("The String is inValid..");
					}
				}
				if (s.contains("*") && !s.contains("^")) 
				{
					this._power = 1;
					try
					{
						this._coefficient = Double.parseDouble(s.substring(0, indexof_multy));
					}
					catch(Exception e)
					{
						throw new IllegalArgumentException("The String is inValid..");
					}
				}
				if (s.contains("^") && !s.contains("*")) 
				{
					try 
					{
						set_power(Integer.parseInt(s.substring(indexof_pow + 1, s.length())));
					}
					catch(Exception e)
					{
						throw new IllegalArgumentException("The String is inValid..");
					}
					//this._power = Integer.parseInt(s.substring(indexof_pow + 1, s.length()));
					if (s.substring(0, indexof_x).isEmpty() || s.substring(0, indexof_x).equals("+")) 
					{
						this._coefficient = 1;
					} else if (s.substring(0, indexof_x).equals("-")) 
					{
						this._coefficient = -1;
					} else 
					{
						try {
							this._coefficient = Double.parseDouble(s.substring(0, indexof_x));
						}
						catch(Exception e)
						{
							throw new IllegalArgumentException("The String is inValid..");
						}
					}
				}
				if (!s.contains("^") && !s.contains("*")) 
				{
					if (indexof_x == -1) 
					{
						try {
							this._coefficient = Double.parseDouble(s);
						}
						catch(Exception e)
						{
							throw new IllegalArgumentException("The String is inValid..");
						}
						this._power = 0;
					}
					else 
					{
						try
						{
							this._coefficient = Double.parseDouble(s.substring(0, indexof_x));
						}
						catch(Exception e)
						{
							throw new IllegalArgumentException("The String is inValid..");
						}
						this._power = 1;
					}
				}
			}
		}
	}

	public void add(Monom m) 
	{
		// same power
		if (m._power == this._power)
		{
			this._coefficient = this._coefficient + m._coefficient;
		}
		else
		{
			//not same power
			System.err.println("EROR: The powers are not the same, they can't be add");
		}

	}
	public void add(double a, int b) throws Exception {
		if(b==this._power) {
			this._coefficient+=a;
		}
		else if(a==0) return;
		else {
			throw new Exception("the power must be the same as the original monom");
		}
	}
	public void multipy(Monom d) 
	{
		this._coefficient = (d._coefficient * this._coefficient);
		this._power = (d._power + this._power);
	}
	public void substract(Monom m)
	{
		if(m.get_power()==this.get_power()) 
			this._coefficient -=m.get_coefficient();
		else if(m.get_coefficient()==0) return;
		else if(this.isZero()) return;
		else
			throw new RuntimeException( "the power must be equals or the monom must be zero");
	}
	public boolean equals(Object other)
	{
		if(other instanceof Monom)
		{
			Monom m = (Monom) other;
			if(this._coefficient == 0 && m._coefficient == 0)
				return true;
			if(Math.abs(this._coefficient - m._coefficient) < EPSILON && this._power == m._power)
			{
				return true;
			}
			return false;
		}
		else
		{
			if (other instanceof Polynom)
			{
				Polynom p = new Polynom(this.toString());
				return p.equals(other);
			}
			else if (other instanceof ComplexFunction)
			{
				for (double step = -15; step <= 15; step+=0.1)
				{
					if(Math.abs(this.f(step)-((function)other).f(step)) > Monom.EPSILON)
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	/**
	 * returns the monom in this format: "a*x^b"
	 */
	public String toString() 
	{
		if (this._power == 0)
			return this._coefficient + "";
		if (this._power > 0) 
		{
			return (this._coefficient + "*" + "x^" + this._power);
		} 
		else 
		{
			return ("" + this._coefficient);
		}
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************


	private void set_coefficient(double a)
	{
		if (a == 0)
			this._power = 0;
		else
			this._coefficient = a;
	}
	private void set_power(int p) 
	{
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	@Override
	public function initFromString(String s) 
	{
		// TODO Auto-generated method stub
		function m = new Monom(s);
		return m;
	}
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		function ans = new Monom(this);
		return ans;
	}

}
