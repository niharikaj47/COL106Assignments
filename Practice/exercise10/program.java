public class program
{
	public int test(int n, int m)
	{
		/*
		Exercise 10: Least common multiple- Given two integers n and m, the objective
		is to compute the LCM (least common multiple) of n and m. LCM is the smallest
		number that is divisble by both n amd m. For e.g. is n is 12 and m is 14, the
		LCM is 84. If n is 32 and m is 16, the LCM is 32.
		*/
		int min=n;
		if(m<n) {min=m;}
		int gcd=1;
		for(int i=2;i<=min;i++)
		{
			if(n%i==0 && m%i==0)
			{
				gcd=i;
			}
		}
		int lcm=n*m/gcd;
		return lcm;
	}
}
