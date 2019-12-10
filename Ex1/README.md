# Ex1# 
The tasks were written by:
Shahar Niknazar - 316416668
Or Gamliel - 208202663

myMath
We have 2 classes in our project:
Monom – shaped like a*x^b, when a is a real number and b is an integer(should be positive).
Polynom - shaped as array lists of monomials.




MONOM CLASS:
Monom class have few functions:
1. Add monoms with the same power(new one and exist one).
2. Calculate function value of specific x (int and double x).
3. Derivative of monom.
4. Multiply two monoms.
5. Check if two monoms are equals.
6. Check if the monom is the zero monom.
7. Subtract between two monoms with the same power.

8. GetCoef - help functions that get string that represent monom and find his coefficient.
9. GetPower - help functions that get string that represent monom and find his power.
10. To string - return a string of the monom.

The class have 3 constructors:
1. Default constructor.
2. Copy constructor.
3. String constructor - get string of monom and turn it to new monom.

we also have getters and setters:
1. power getter and setter.
2. coefficient getter and setter.
POLYNOM CLASS:
The class have 3 constructors:
1. default constructor.
2. string constructor - get string of polynom and make it to new polynom.
3. copy constructor.

Polynom class have few functions:
1. Add monom to our Polynom.
2. Add Polynom to our Polynom.
3. Subtract between two Polynoms.
4. Subtract monom from our Polynom.
5. Multiply two Polynoms.
6. Check if two Polynoms are equals.
7. Check if the Polynom is the zero monom.
8. Root - finds a root value of a Polynom while given 2 values,  one is positive and the other is negative. We also have epsilon which we make sure that it close to the 0 value.
9. Create a deep copy of this Polynom.
10. Derivative of the Polynom.
11. Area - Compute Riemann's Integral over this Polynom starting from x0, till xi using epsilon size steps.
12. Iterator (of Monoms) over our Polynom.
13. Calculate specific x of the Polynom (int & double).
14. To string - return a string of our Polynom.
