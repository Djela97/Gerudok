package drvo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class Stranica implements TreeNode, Serializable
{

	private static final long serialVersionUID = 1L;
	private String name;
	private Dokument parent;
	private ArrayList<Slot> slotovi = new ArrayList<>();
	private ArrayList<Dokument> dokumentaKojaSadrzeStranicu = new ArrayList<>();
	private Dokument glavniDokument = null;
	
	public Stranica(Dokument parent, String name) 
	{
		
		this.parent = parent;
		this.name = name;
		
	}
	
	@Override
	public Enumeration children()
	{
		return (Enumeration)slotovi;
	}

	@Override
	public boolean getAllowsChildren()
	{
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex)
	{
		return slotovi.get(childIndex);
	}

	@Override
	public int getChildCount()
	{
		return slotovi.size();
	}

	@Override
	public int getIndex(TreeNode node)
	{
		return slotovi.indexOf(node);
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
	
	public void postaviGlavniDokument(Dokument d)
	{
		if(!dokumentaKojaSadrzeStranicu.contains(d))
			dokumentaKojaSadrzeStranicu.add(d);
		glavniDokument = dokumentaKojaSadrzeStranicu.get(dokumentaKojaSadrzeStranicu.indexOf(d));
	}
	
	public void obrisiStranicu()
	{
		if(dokumentaKojaSadrzeStranicu.size()>0)
		{
			dokumentaKojaSadrzeStranicu.remove(glavniDokument);
			postaviGlavniDokument(dokumentaKojaSadrzeStranicu.get(0));
		}
		else
		{
			dokumentaKojaSadrzeStranicu = new ArrayList<>();
			postaviGlavniDokument(null);
		}
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public String getName()
	{
		return name;
	}

	public ArrayList<Slot> getSlotovi()
	{
		return slotovi;
	}

	public ArrayList<Dokument> getDokumentaKojaSadrzeStranicu()
	{
		return dokumentaKojaSadrzeStranicu;
	}

	public Dokument getGlavniDokument()
	{
		return glavniDokument;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setParent(Dokument parent)
	{
		this.parent = parent;
	}

	public void setSlotovi(ArrayList<Slot> slotovi)
	{
		this.slotovi = slotovi;
	}

	public void setDokumentaKojaSadrzeStranicu(ArrayList<Dokument> dokumentaKojaSadrzeStranicu)
	{
		this.dokumentaKojaSadrzeStranicu = dokumentaKojaSadrzeStranicu;
	}

	public void setGlavniDokument(Dokument glavniDokument)
	{
		this.glavniDokument = glavniDokument;
	}

}
