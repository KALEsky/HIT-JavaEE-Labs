public class Console implements CallBack
{
	public void tellProgress(int progress)
	{
		System.out.print("\b\b\b\b" + progress + "%");
	}
}