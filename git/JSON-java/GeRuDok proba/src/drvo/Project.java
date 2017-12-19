package drvo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFileChooser;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import controllers.Sejvbl;
import main.Dokumenta;
import main.MainView;

public class Project extends Dokumenta implements Serializable,Sejvbl
{

	private static final long serialVersionUID = 1L;
	private String name;
	private transient Workspace parent;
	private ArrayList<Dokument> dokumenti = new ArrayList<>();
	private String putanjaFajla = null;

	public Project(Workspace parent, String name) 
	{
		
		this.parent = parent;
		this.name = name;
		
	}
	@Override
	public Enumeration children()
	{
		return (Enumeration)dokumenti;
	}

	@Override
	public boolean getAllowsChildren()
	{
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex)
	{
		return dokumenti.get(childIndex);
	}

	@Override
	public int getChildCount()
	{
		return dokumenti.size();
	}

	@Override
	public int getIndex(TreeNode node)
	{
		return dokumenti.indexOf(node);
	}

	@Override
	public TreeNode getParent()
	{
		return parent;
	}

	@Override
	public boolean isLeaf()
	{
		return false;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPutanjaFajla()
	{
		return putanjaFajla;
	}
	public void setPutanjaFajla(String putanjaFajla)
	{
		this.putanjaFajla = putanjaFajla;
	}
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
	public ArrayList<Dokument> getDokumenti()
	{
		return dokumenti;
	}
	public void setParent(Workspace parent)
	{
		this.parent = parent;
	}
	public void setDokumenti(ArrayList<Dokument> dokumenti)
	{
		this.dokumenti = dokumenti;
	}
	
	public void saveProject(PrintWriter writer)
	{
		if(putanjaFajla==null)
		{
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = jfc.showOpenDialog(null);
			setPutanjaFajla(jfc.getSelectedFile().getAbsolutePath());
			
			ObjectOutputStream os;
			try 
			{
				System.out.println(getPutanjaFajla());
			os = new ObjectOutputStream(new FileOutputStream(getPutanjaFajla() + "\\" + getName() + ".gpj"));
			os.writeObject(this);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		writer.println(getPutanjaFajla() + "\\" + getName() + ".gpj");
	}
	
	public void saveProject()
	{
		if(putanjaFajla==null)
		{
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = jfc.showOpenDialog(null);
			setPutanjaFajla(jfc.getSelectedFile().getAbsolutePath());
			
			ObjectOutputStream os;
			try 
			{
				System.out.println(getPutanjaFajla());
			os = new ObjectOutputStream(new FileOutputStream(getPutanjaFajla() + "\\" + getName() + ".gpj"));
			os.writeObject(this);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			ObjectOutputStream os;
			try 
			{
				System.out.println(getPutanjaFajla());
			os = new ObjectOutputStream(new FileOutputStream(getPutanjaFajla() + "\\" + getName() + ".gpj"));
			os.writeObject(this);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void openProject(String putanja) throws Exception
	{
		this.setPutanjaFajla(putanja);
		ObjectInputStream os = new ObjectInputStream(new FileInputStream(getPutanjaFajla()));
		Project p = null;
		p = (Project)os.readObject();
		Project zaDodavanje = new Project(MainView.getInstance().getWorkspace(), p.getName());
		for(Dokument d : p.getDokumenti())
			zaDodavanje.getDokumenti().add(d);
		
		MainView.getInstance().getWorkspace().addProject(zaDodavanje);
		MainView.getInstance().osvezi();
	}

}
