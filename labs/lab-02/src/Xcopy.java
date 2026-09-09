import java.io.*;
public class XCopy
{
	public static void main(String[] args)
	{
		try
		{
			CallBack cb = new Console();
			FileUtils fu = new FileUtils(cb);
			FileInputStream in = new FileInputStream(args[0]);
			FileOutputStream out = new FileOutputStream(args[1]);
			fu.copy(in, out, 1024);
		}
		catch(IOException e)
		{	
			e.printStackTrace();
		}
		
	}
}