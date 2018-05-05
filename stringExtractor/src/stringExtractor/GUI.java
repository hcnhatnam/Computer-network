package stringExtractor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class GUI {
    public static int port;
	private JFrame frame;
	private JTextField IPText;
	private JTextField portTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 320, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		IPText = new JTextField();
		IPText.setBounds(113, 31, 110, 20);
		frame.getContentPane().add(IPText);
		IPText.setColumns(10);
		
		JLabel lblIpAdress = new JLabel("IP Adress");
		lblIpAdress.setBounds(37, 31, 71, 14);
		frame.getContentPane().add(lblIpAdress);
		
		JLabel lblNewLabel = new JLabel("Port");
		lblNewLabel.setBounds(37, 56, 71, 14);
		frame.getContentPane().add(lblNewLabel);
		
		portTxt = new JTextField();
		portTxt.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
				port = Integer.parseInt(portTxt.getText().toString());
			}
		});
		
		portTxt.setBounds(113, 56, 110, 20);
		frame.getContentPane().add(portTxt);
		portTxt.setColumns(10);
		//DOLONGBIEN
		//port = Integer.parseInt(portTxt.getText().toString());
		//port =9876;
		//
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				try {
					ServerAnalytic.main(args);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStart.setBackground(SystemColor.activeCaption);
		btnStart.setForeground(UIManager.getColor("Button.highlight"));
		btnStart.setBounds(133, 138, 71, 23);
		frame.getContentPane().add(btnStart);
	}
}
