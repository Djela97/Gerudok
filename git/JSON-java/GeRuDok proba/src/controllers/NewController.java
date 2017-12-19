package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import drvo.Dokument;
import drvo.Project;
import drvo.Workspace;
import main.Dokumenta;
import main.Main;
import main.MainView;

public class NewController implements ActionListener
{
	private Dokumenta d;
	
	
	public NewController(Dokumenta d)
	{
		this.d = d;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		d = (Dokumenta)MainView.getInstance().getDrvo().getSelectionModel().getSelectionPath().getLastPathComponent();
		if(d instanceof Workspace)
		{
			if(MainView.getInstance().getWorkspace()==null)
			{
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Izaberite putanju");
				
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(null);
				if(chooser.getSelectedFile() != null)
					MainView.getInstance().blankWorkspace(chooser.getSelectedFile().getAbsolutePath());
			}
			else
			{
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Izaberite gde ce workspace da se sacuva");
				
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(null);
				if(chooser.getSelectedFile() != null)
				{
					MainView.getInstance().saveWorkspace(chooser.getSelectedFile().getAbsolutePath());
					MainView.getInstance().blankWorkspace(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		}
		else if( d instanceof Project)
		{

			Project p = new Project(MainView.getInstance().getWorkspace(),"Projekat" + Integer.valueOf(MainView.getInstance().getWorkspace().getProjekti().size() + 1));
			MainView.getInstance().getWorkspace().addProject(p);
			MainView.getInstance().osvezi();
			
		}
		else if( d instanceof Dokument)
		{
			Project p = (Project)MainView.getInstance().getDrvo().getSelectionModel().getSelectionPath().getLastPathComponent();
			Dokument a = new Dokument(p, "Dokument " + Integer.valueOf(p.getDokumenti().size()+1));
			p.getDokumenti().add(a);
			MainView.getInstance().osvezi();
		}
		
	}

}
