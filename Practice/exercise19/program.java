public class program
{
	public int[][] test(int M1[][], int M2[][])
	{
		int m=M1.length;
		int n=M1[0].length;
		int[][] addition=new int[m][n];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				addition[i][j]=M1[i][j]+M2[i][j];
			}

		}
		return addition;
	}
}
