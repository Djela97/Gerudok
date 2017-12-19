package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.MainView;

public class SaveWorkspace implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if(MainView.getInstance().getWorkspace().getProjekti().size()==0)
		{
			JPanel panel = new JPanel();
			JLabel lbl = new JLabel("Workspace ne moze biti prazan!!!");
			panel.add(lbl);
			JDialog dialog = new JDialog();
			panel.setSize(240,120);
			dialog.add(panel);
			dialog.setSize(240, 120);
			dialog.setVisible(true);
			return;
		}
		
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Izbarite gde da se sacuva workspace");
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		//chooser.showOpenDialog(null);
		if(chooser.getSelectedFile() != null)
			MainView.getInstance().saveWorkspace(chooser.getSelectedFile().getAbsolutePath());
		
	}

}
