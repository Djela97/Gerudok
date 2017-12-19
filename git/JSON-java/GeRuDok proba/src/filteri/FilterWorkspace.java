package filteri;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FilterWorkspace extends FileFilter
{

	@Override
	public boolean accept(File f)
	{
		return (f.isDirectory() || 
                f.getName().toLowerCase().endsWith(".gwk"));
	}

	@Override
	public String getDescription()
	{
		return "*.gwk";
	}

}
