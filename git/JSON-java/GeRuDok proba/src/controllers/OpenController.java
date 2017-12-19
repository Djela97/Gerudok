package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import drvo.Project;
import drvo.Workspace;
import filteri.FilterProject;
import filteri.FilterWorkspace;
import main.Dokumenta;
import main.MainView;

public class OpenController implements ActionListener
{
	private Dokumenta d;
	
	
	public OpenController(Dokumenta d)
	{
		this.d = d;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if(d instanceof Workspace)
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Izaberite putanju do workspace-a");
			
			chooser.setFileFilter(new FilterWorkspace());
			int returnVal = chooser.showOpenDialog(null);
			//chooser.showOpenDialog(null);
			if(chooser.getSelectedFile() != null)
				MainView.getInstance().openWorkspace(chooser.getSelectedFile().getAbsolutePath());
		}
		else if(d instanceof Project)
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Izaberite putanju do projekta");
			
			chooser.setFileFilter(new FilterProject());
			int returnVal = chooser.showOpenDialog(null);
			//chooser.showOpenDialog(null);
			if(chooser.getSelectedFile() != null)
			{
				Project p = new Project(null,null);
				try
				{
					p.openProject(chooser.getSelectedFile().getAbsolutePath());
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

}
