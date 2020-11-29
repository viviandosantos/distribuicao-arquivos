package jframes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.p2p.packets.FileData;
import panels.ArquivosPanel;
import panels.DownloadsPanel;

import javax.swing.JTabbedPane;

public class ArquivosFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//teste referencia projeto backend P2P
		//FileData f = new FileData();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArquivosFrame frame = new ArquivosFrame();
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
	public ArquivosFrame() {
		setResizable(false);
		setTitle("Distribuição de Arquivos Multimídia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		ArquivosPanel arquivosPanel = new ArquivosPanel();
		tabbedPane.addTab("Arquivos", arquivosPanel);
		DownloadsPanel downloadsPanel = new DownloadsPanel();
		tabbedPane.addTab("Meus downloads", downloadsPanel);

		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}

}
