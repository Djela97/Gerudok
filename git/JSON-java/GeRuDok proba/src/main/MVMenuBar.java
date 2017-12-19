package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.NewController;
import controllers.OpenController;
import controllers.OpenWorkspace;
import controllers.RenameController;
import controllers.SaveController;
import controllers.SaveProject;
import controllers.SaveWorkspace;
import drvo.Dokument;
import drvo.Project;
import drvo.Stranica;
import drvo.Workspace;

public class MVMenuBar extends JMenuBar
{

	private static final long serialVersionUID = 1L;
	
	public MVMenuBar()
	{
		this.setBackground(Color.WHITE);
		JMenu file = new JMenu("File");
		this.add(file);
		
		JMenu workspace = new JMenu("Workspace");
		file.add(workspace);
		
		JMenuItem newWorkspace = new JMenuItem("New");
		JMenuItem openWorkspace = new JMenuItem("Open");
		JMenuItem saveWorkspace = new JMenuItem("Save");
		JMenuItem renameWorkspace = new JMenuItem("Rename");
		JMenuItem deleteWorkspace = new JMenuItem("Delete");
		workspace.add(newWorkspace);
		workspace.add(saveWorkspace);
		workspace.add(openWorkspace);
		workspace.add(renameWorkspace);
		workspace.add(deleteWorkspace);
		
		JMenu project = new JMenu("Project");
		file.add(project);
		
		JMenuItem saveProject = new JMenuItem("Save");
		JMenuItem newProject = new JMenuItem("New");
		JMenuItem renameProject = new JMenuItem("Rename");
		JMenuItem openProject = new JMenuItem("Open");
		JMenuItem deleteProject = new JMenuItem("Delete");
		project.add(newProject);
		project.add(saveProject);
		project.add(openProject);
		project.add(renameProject);
		project.add(deleteProject);
		
		JMenu dokument = new JMenu("Dokument");
		file.add(dokument);
		
		JMenuItem newDokument = new JMenuItem("New");
		JMenuItem renameDokument = new JMenuItem("Rename");
		JMenuItem deleteDokument = new JMenuItem("Delete");
		dokument.add(newDokument);
		dokument.add(renameDokument);
		dokument.add(deleteDokument);
		
		JMenu stranica = new JMenu("Stranica");
		file.add(stranica);
		
		JMenuItem newStranica = new JMenuItem("New");
		JMenuItem renameStranica = new JMenuItem("Rename");
		JMenuItem deleteStranica= new JMenuItem("Delete");
		stranica.add(newStranica);
		stranica.add(renameStranica);
		stranica.add(deleteStranica);
		
		JMenu slot = new JMenu("Slot");
		file.add(slot);
		
		JMenuItem newSlot = new JMenuItem("New");
		JMenuItem renameSlot = new JMenuItem("Rename");
		JMenuItem deleteSlot = new JMenuItem("Delete");
		slot.add(newSlot);
		slot.add(renameSlot);
		slot.add(deleteSlot);
		
		
		
		openWorkspace.addActionListener(new OpenController(new Workspace(null)));
		openProject.addActionListener(new OpenController(new Project(null,null)));
		saveWorkspace.addActionListener(new SaveWorkspace());
		
		saveProject.addActionListener(new SaveProject());
		
		
		newWorkspace.addActionListener(new NewController(new Workspace(null)));
		newProject.addActionListener(new NewController(new Project(null,null)));
		newDokument.addActionListener(new NewController(new Dokument(null, null)));
		//newStranica.addActionListener(new NewController(new Stranica(null,null)));
		
		renameWorkspace.addActionListener(new RenameController(new Workspace(null)));
		renameProject.addActionListener(new RenameController(new Project(null,null)));
		renameDokument.addActionListener(new RenameController(new Dokument(null,null)));
		
		/*deleteDokument.addActionListener(new DeleteController(new Dokument(null,null)));
		deleteWorkspace.addActionListener(new DeleteController(new Workspace(null)));
		deleteProject.addActionListener(new DeleteController(new Project(null,null)));
		
		/*JMenuItem newWorkspace = new JMenuItem("New");
		JMenuItem openWorkspace = new JMenuItem("Open");
		JMenuItem saveWorkspace = new JMenuItem("Save");
		JMenuItem renameWorkspace = new JMenuItem("Rename");
		JMenuItem deleteWorkspace = new JMenuItem("Delete");
		file.add(newWorkspace);
		file.add(saveWorkspace);
		file.add(openWorkspace);
		file.add(renameWorkspace);
		file.add(deleteWorkspace);
		newWorkspace.addActionListener(new NewController());
		renameWorkspace.addActionListener(new RenameController());
		saveWorkspace.addActionListener(new SaveController());
		openWorkspace.addActionListener(new OpenController());*/
	}
}
