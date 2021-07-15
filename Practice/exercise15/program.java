public class program
{
	public int[] test(int input[])
	{
		int c=0;
		for(int i=0;i<input.length;i++){
            if(input[i]!=0){
				c++;
			}
        }
		int[] ret=new int[c];
		int i=0,d=0;
		while(i<input.length && d<c)
		{
			if(input[i]!=0){
				ret[d]=input[i];
				i++;
				d++;
			}
			else{
				i++;
			}
		}
		return ret;
	}
}
