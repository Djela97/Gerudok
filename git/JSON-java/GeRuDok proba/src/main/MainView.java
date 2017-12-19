package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import controllers.SaveWorkspace;
import drvo.Dokument;
import drvo.Project;
import drvo.Workspace;
import javafx.scene.layout.Border;



public class MainView extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	private Toolkit toolKit = Toolkit.getDefaultToolkit();

	private static MainView instance = null;
	
	public Workspace workspace;
	//private ArrayList<Project> projekti = new ArrayList<>();
	private DefaultTreeModel dtm;
	private JTree drvo = null;
	private JScrollPane jsp;
	private JLabel statusnaLinija;
	
	private BorderLayout bl = new BorderLayout();
	private JPanel root = new JPanel(bl);
	
	private MVMenuBar menu = new MVMenuBar();
	
	private MainView()
	{
		init();
	}
	
	public static MainView getInstance()
	{
		if(instance==null)
			instance = new MainView();
		
		return instance;
		
	}
	
	private void init()
	{
		this.setSize(new Dimension(840,600));
		this.setLocation((int)toolKit.getScreenSize().getWidth()/2 - 480,(int)toolKit.getScreenSize().getHeight()/2-300);
		this.setLayout(new BorderLayout());
		this.add(root);
		
		//defaultWorkspace();
		root.setBackground(Color.WHITE);
		root.add(menu,BorderLayout.NORTH);
		
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveWorkspace(String putanja)
	{
		osvezi();
		
		File f1 = new File(putanja + "\\" + workspace.toString() + ".gwk");
		if(!f1.exists())
			try
			{
				f1.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		try
		{
			
			PrintWriter writer = new PrintWriter(f1);
			System.out.println("D");
			for(Project p : workspace.getProjekti())
			{
				p.saveProject(writer);
			}
			writer.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		File f = new File(f1.getAbsolutePath());
		try
		{
			System.out.println(f1.getAbsolutePath());
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String red = null;
			while((red = reader.readLine())!=null)
				System.out.println(red);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void openWorkspace(String putanja)
	{
		osvezi();
		
		File workspaceFile = new File(putanja);
		StringTokenizer token = new StringTokenizer(workspaceFile.getName(), ".");
		workspace = new Workspace(token.nextToken());
		workspace.setPutanjaDoWorkspace(putanja);
		
		BufferedReader reader;
		try
		{
			reader = new BufferedReader(new FileReader(workspaceFile));
			String red = null;
			while((red = reader.readLine())!=null)
			{
				try
				{
					ObjectInputStream os = new ObjectInputStream(new FileInputStream(red));
					Project p = null;
					p = (Project)os.readObject();
					Project zaDodavanje = new Project(workspace, p.getName());
					for(Dokument d : p.getDokumenti())
						zaDodavanje.getDokumenti().add(d);
					
					MainView.getInstance().getWorkspace().addProject(zaDodavanje);
					MainView.getInstance().osvezi();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		dtm = new DefaultTreeModel(workspace);
		drvo = new JTree(dtm);
		dtm.reload();
		statusnaLinija = new JLabel(workspace.getPutanjaDoWorkspace());
		jsp = new JScrollPane(drvo);
		jsp.setBounds(10, 10, 170, 415);
		jsp.setPreferredSize(new Dimension(180, 480));
		root.add(jsp,BorderLayout.WEST);
		root.add(statusnaLinija,BorderLayout.SOUTH);
		osvezi();
	}
	
	public void blankWorkspace(String putanja)
	{
		osvezi();
		String[] parts = putanja.split("\\\\");
		workspace = new Workspace(parts[parts.length-1]);
		workspace.setPutanjaDoWorkspace(putanja);
		dtm = new DefaultTreeModel(workspace);
		drvo = new JTree(dtm);
		dtm.reload();
		statusnaLinija = new JLabel(putanja);
		jsp = new JScrollPane(drvo);
		jsp.setBounds(10, 10, 170, 415);
		jsp.setPreferredSize(new Dimension(180, 480));
		root.add(jsp,BorderLayout.WEST);
		root.add(statusnaLinija,BorderLayout.SOUTH);
		osvezi();
	}
	
	public void defaultWorkspace()
	{
		workspace = new Workspace("Workspace");
		workspace.setPutanjaDoWorkspace(null);
		dtm = new DefaultTreeModel(workspace);
		drvo = new JTree(dtm);
		dtm.reload();
		statusnaLinija = new JLabel("Morate da snimite ovaj workspace, napravite novi ili otvorite postojeci da bi se ovde prikazala lokacija");
		jsp = new JScrollPane(drvo);
		jsp.setBounds(10, 10, 170, 415);
		jsp.setPreferredSize(new Dimension(180, 480));
		root.add(jsp,BorderLayout.WEST);
		root.add(statusnaLinija,BorderLayout.SOUTH);
		osvezi();
	}
	
	public void osvezi()
	{
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void saveProject(String putanja)
	{
		Project p = (Project)MainView.getInstance().getDrvo().getSelectionPath().getLastPathComponent();
		//TODO
	}

	public JTree getDrvo()
	{
		return drvo;
	}

	public void setDrvo(JTree drvo)
	{
		this.drvo = drvo;
	}

	public Workspace getWorkspace()
	{
		return workspace;
	}

	public void setWorkspace(Workspace workspace)
	{
		this.workspace = workspace;
	}
}
