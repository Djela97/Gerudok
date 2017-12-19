package controllers;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import drvo.Dokument;
import drvo.Project;
import drvo.Workspace;
import main.Dokumenta;
import main.MainView;

public class RenameController implements ActionListener
{
	private Dokumenta d;
	
	
	public RenameController(Dokumenta d)
	{
		this.d = d;
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
		d = (Dokumenta)MainView.getInstance().getDrvo().getSelectionModel().getSelectionPath().getLastPathComponent();
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JTextField textField = new JTextField("Enter your choise");
		panel.add(textField);
		JButton btn = new JButton("Next");
		btn.setSize(50, 40);
		panel.add(btn);
		JDialog dialog = new JDialog();
		panel.setSize(240,120);
		dialog.add(panel);
		dialog.setSize(240, 120);
		dialog.setVisible(true);
		btn.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dialog.dispose();
				if(d instanceof Workspace)
				{
					MainView.getInstance().getWorkspace().changeName(textField.getText());
				}
				else if( d instanceof Project)
				{
					/*int index = MainView.getInstance().getWorkspace().getProjekti().indexOf(
							(Project)MainView.getInstance().getDrvo().getSelectionModel().getSelectionPath().getLastPathComponent());
					MainView.getInstance().getWorkspace().getProjekti().get(index).setName(textField.getText());*/
					Project p = (Project)MainView.getInstance().getDrvo().getSelectionModel().getSelectionPath().getLastPathComponent();
					p.setName(textField.getText());
					MainView.getInstance().osvezi();
				}
				else if( d instanceof Dokument)
				{
					Dokument d = (Dokument)MainView.getInstance().getDrvo().getSelectionModel().getSelectionPath().getLastPathComponent();
					d.setName(textField.getText());
					MainView.getInstance().osvezi();
				}
			}
		});
		
	}
	
}
