public class program
{
	public String[] test(String fileNames[])
	{

		int n=fileNames.length;
		int cnt=0;
		for(int i=0;i<n;i++)
        {
            int x = fileNames[i].length();
            if(x>=6)
            {
                if ((fileNames[i].substring(x-5, x)).equals( ".java"))
					cnt++;
            }
        }
		String[] javaFiles=new String[cnt];
		int d=0;
		int i=0;
		while(i<n && d<cnt)
		{
			int x = fileNames[i].length();
            if(x>=6)
            {
				if ((fileNames[i].substring(x-5, x)).equals( ".java")){
					javaFiles[d]=fileNames[i];
					d++;
					i++;
				}
				else{
					i++;
				}
			}
		}
		return javaFiles;
	}
}
