package jp.ojt.sst.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import jp.ojt.sst.file.ConfigFile;
import jp.ojt.sst.file.StackTraceFile;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Main Frame of this tool.
 * 
 */
public class Main extends JFrame {

	/** serial version UID */
	private static final long serialVersionUID = -6021444475828013574L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setTitle("SummaryStackTraceTool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 120);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Choose LogFile:");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setEnabled(false);
		panel.add(textField);
		textField.setColumns(15);
		
		JButton btnNewButton = new JButton("Reference...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigFile confFile = ConfigFile.getInstance();
				JFileChooser fc = new JFileChooser(confFile.getReferencePath());
				fc.addChoosableFileFilter(new FileNameExtensionFilter("LogFile(*.log)", "log"));
				int selected = fc.showOpenDialog(null);
				if(selected == JFileChooser.APPROVE_OPTION) {
					String refPath = fc.getSelectedFile().getAbsolutePath();
					textField.setText(refPath);
					confFile.saveReferencePath(refPath);
				}
			}
		});
		panel.add(btnNewButton);
		
		JPanel panelButton = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelButton.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelButton, BorderLayout.SOUTH);
		
		JButton btnExecute = new JButton("EXECUTE");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigFile confFile = ConfigFile.getInstance();
				StackTraceFile stFile = new StackTraceFile(textField.getText(), confFile.getSearchWord());
				stFile.read();
				stFile.outputCSV();
				JOptionPane.showMessageDialog(null, "Success!! output csv file.");
			}
		});
		panelButton.add(btnExecute);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelButton.add(btnExit);
	}
}
