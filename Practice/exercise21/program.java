public class program
{
	public int test(char key[], char answer[])
	{
        int ret =0;
        for(int i=0;i<key.length;i++)
        {
            if(answer[i]==(key[i]))
                ret=ret+4;
            else if(answer[i]=='?') ret=ret;
            else if(answer[i]!=key[i] && key[i]!='?')
                ret=ret-1;
        }
		return ret;
	}
}
