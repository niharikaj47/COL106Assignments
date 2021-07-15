public class program
{
	public float test(int numbers[])
	{
        int sum=0;
		int n=numbers.length;
        for(int i=0;i<n;i++)
        {
            sum=sum+numbers[i];
        }
		float sum2=(float) sum;
        float ans=0;
		if(n>0){
        	ans=sum/n;}
		return ans;
	}
}
