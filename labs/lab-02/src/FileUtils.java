import java.io.*;
public class FileUtils
{	
	private CallBack cb;
	public FileUtils(CallBack cb)
	{
		this.cb = cb;
	}
	public void copy(FileInputStream in, FileOutputStream out, int bufSize)
	throws IOException
	{
		int total = in.available();
		byte[] buf = new byte[bufSize];
		int copied = 0;
		int len = in.read(buf);
		while(len > 0)
		{
			copied += len;
			out.write(buf, 0, len);
			len = in.read(buf);
			cb.tellProgress((int)(100.0 * copied / total));
		}
	}
}