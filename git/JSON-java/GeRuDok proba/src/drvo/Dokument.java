package drvo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import main.Dokumenta;

public class Dokument extends Dokumenta implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String name;
	private Project parent;
	private ArrayList<Stranica> stranice = new ArrayList<>();

	
	public Dokument(Project parent, String name) 
	{
		
		this.parent = parent;
		this.name = name;
		
	}
	
	@Override
	public Enumeration children()
	{
		// TODO Auto-generated method stub
		return (Enumeration)stranice;
	}

	@Override
	public boolean getAllowsChildren()
	{
		return true;
	}

	@Override
	public TreeNode getChildAt(int arg0)
	{
		return stranice.get(arg0);
	}

	@Override
	public int getChildCount()
	{
		return stranice.size();
	}

	@Override
	public int getIndex(TreeNode arg0)
	{
		return stranice.indexOf(arg0);
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

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public String getName()
	{
		return name;
	}

	public ArrayList<Stranica> getStranice()
	{
		return stranice;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setParent(Project parent)
	{
		this.parent = parent;
	}

	public void setStranice(ArrayList<Stranica> stranice)
	{
		this.stranice = stranice;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
