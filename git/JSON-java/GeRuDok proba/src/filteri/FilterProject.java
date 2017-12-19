package filteri;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FilterProject extends FileFilter
{

	@Override
	public boolean accept(File f)
	{
		return (f.isDirectory() || 
                f.getName().toLowerCase().endsWith(".gpj"));
	}

	@Override
	public String getDescription()
	{
		return "*.gpj";
	}

}
