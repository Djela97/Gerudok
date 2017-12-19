package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import drvo.Project;
import main.MainView;

public class SaveProject implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		((Project)(MainView.getInstance().getDrvo().getSelectionModel().getSelectionPath().getLastPathComponent())).saveProject();
	}
	
}
