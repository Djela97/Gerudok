package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import filteri.FilterWorkspace;
import main.MainView;

public class OpenWorkspace implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Izaberite putanju do workspace-a");
		
		chooser.setFileFilter(new FilterWorkspace());
		int returnVal = chooser.showOpenDialog(null);
		//chooser.showOpenDialog(null);
		if(chooser.getSelectedFile() != null)
			MainView.getInstance().openWorkspace(chooser.getSelectedFile().getAbsolutePath());
	}

}
