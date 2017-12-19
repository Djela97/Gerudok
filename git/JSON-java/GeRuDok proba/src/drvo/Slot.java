package drvo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class Slot implements TreeNode, Serializable
{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Stranica parent;
	private ArrayList<Elements> elementi = new ArrayList<>();

	@Override
	public Enumeration children()
	{
		return (Enumeration)elementi;
	}

	@Override
	public boolean getAllowsChildren()
	{
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex)
	{
		return elementi.get(childIndex);
	}

	@Override
	public int getChildCount()
	{
		return elementi.size();
	}

	@Override
	public int getIndex(TreeNode node)
	{
		return elementi.indexOf(node);
	}

	@Override
	public TreeNode getParent()
	{
		return parent;
	}

	@Override
	public boolean isLeaf()
	{
		return true;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public String getName()
	{
		return name;
	}

	public ArrayList<Elements> getElementi()
	{
		return elementi;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setParent(Stranica parent)
	{
		this.parent = parent;
	}

	public void setElementi(ArrayList<Elements> elementi)
	{
		this.elementi = elementi;
	}

}
