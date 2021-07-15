public class program
{
	public float[] test(int b, int c)
	{
		/*
		Exercise 11: Roots of polynomial- Write a Java program that given b and c,
		computes the roots of the polynomial x*x+b*x+c. You can assume that the
		roots are real valued and need to be return in an array.
		Return the result in an array [p,q] where p<=q meaning the smaller 
		element should be the first element of the array
		*/
		float ret[] = {1.0f,2.0f};
		double x1=(-b-Math.sqrt(b*b-4*c)) / (2);
		double x2=(-b+Math.sqrt(b*b-4*c)) / (2);
		float f1=(float) x1;
		float f2=(float) x2;
		ret[0]=f1;
		ret[1]=f2;
		return ret;
	}
}
