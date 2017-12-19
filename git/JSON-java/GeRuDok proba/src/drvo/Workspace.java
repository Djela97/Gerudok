package drvo;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import controllers.Sejvbl;
import main.Dokumenta;
import main.MainView;

public class Workspace extends Dokumenta implements Sejvbl
{
	private DefaultMutableTreeNode koren;
	private String putanjaDoWorkspace;
	private ArrayList<Project> projekti = new ArrayList<>();
	
	public Workspace(String naziv)
	{
		koren = new DefaultMutableTreeNode(naziv);
	}
	
	
	public void changeName(String s)
	{
		koren.setUserObject(s.toString());
		MainView.getInstance().osvezi();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children()
	{
		return (Enumeration)projekti;
	}

	@Override
	public boolean getAllowsChildren()
	{
		return true;
	}

	@Override
	public TreeNode getChildAt(int arg0)
	{
		return projekti.get(arg0);
	}

	@Override
	public int getChildCount()
	{
		return projekti.size();
	}

	@Override
	public int getIndex(TreeNode arg0)
	{
		return projekti.indexOf(arg0);
	}

	@Override
	public TreeNode getParent()
	{
		return null;
	}

	@Override
	public boolean isLeaf()
	{
		return false;
	}
	
	@Override
	public String toString()
	{
		return koren.toString();
	}

	public ArrayList<Project> getProjekti()
	{
		return projekti;
	}

	public void setProjekti(ArrayList<Project> projekti)
	{
		this.projekti = projekti;
	}
	
	public void addProject(Project p)
	{
		this.projekti.add(p);
	}

	public String getPutanjaDoWorkspace()
	{
		return putanjaDoWorkspace;
	}

	public void setPutanjaDoWorkspace(String putanjaDoWorkspace)
	{
		this.putanjaDoWorkspace = putanjaDoWorkspace;
	}


	public DefaultMutableTreeNode getKoren()
	{
		return koren;
	}


	public void setKoren(DefaultMutableTreeNode koren)
	{
		this.koren = koren;
	}
}
