package panels;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import java.util.ArrayList;
import java.util.Vector;

import components.ButtonColumn;
import viewmodels.FileViewModel;

public class ArquivosPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField tfNomeArquivo;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ArquivosPanel() {
		setToolTipText("Arquivos");
		Color defaultColor = new Color(240, 248, 255);
		Color white = new Color(255, 255, 255);
		Font defaultFontTitle = new Font("Tahoma", Font.BOLD, 12);
		setBackground(defaultColor);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do arquivo");
		lblNewLabel.setBounds(10, 11, 104, 15);
		add(lblNewLabel);
		lblNewLabel.setFont(defaultFontTitle);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		tfNomeArquivo = new JTextField();
		tfNomeArquivo.setBounds(10, 37, 810, 20);
		add(tfNomeArquivo);
		tfNomeArquivo.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(716, 68, 104, 23);
		add(btnPesquisar);
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Trigger para pesquisar arquivos a serem baixados");
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparFiltro();
			}
		});
		btnLimpar.setBounds(623, 68, 83, 23);
		add(btnLimpar);
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 810, 262);
		add(scrollPane);
		
	    DefaultTableModel model = new DefaultTableModel(getColumnsData(), getColumnNames());
	    table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			//habilitando apenas botão de download
			@Override
	        public boolean isCellEditable(int row, int column) {
				return column == 4;         
	        };
	    };
	   	    
	    table.setOpaque(true);
	    table.setFillsViewportHeight(true);
		table.setEnabled(true);
		table.setBorder(null);
		table.setBackground(white);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(white);
		tableHeader.setFont(defaultFontTitle);
		
		Action download = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Trigger para iniciar download");
			}
		    
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(table, download, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		scrollPane.setViewportView(table);
	}
	
	//Retorna a lista de arquivos a ser exibida
	private Vector<Vector<Object>> getColumnsData(){
		//TODO: obter a lista de arquivos no tipo ArrayList<viewmodels.FileViewModel>
		ArrayList<FileViewModel> arquivos = new ArrayList<FileViewModel>();
		
		FileViewModel arquivo1 = new FileViewModel("Nome arquivo teste 1", "12 KB", "AUAFSD$F52314353", "Servidor B");
		FileViewModel arquivo2 = new FileViewModel("Nome arquivo teste 2", "35 KB", "EUR45F$&*567TRGD", "Servidor A");		
		
		arquivos.add(arquivo1);
		arquivos.add(arquivo2);
		
		Vector<Vector<Object>> dataColumns = new Vector<Vector<Object>>();

		for(FileViewModel a : arquivos) {
			Vector<Object> arquivoVector = new Vector<Object>();

			arquivoVector.add(a.nome);
			arquivoVector.add(a.tamanho);
			arquivoVector.add(a.hash);
			arquivoVector.add(a.servidorOrigem);
			arquivoVector.add("Download");
			
			dataColumns.add(arquivoVector);
		}
		
		
		return dataColumns;
	}
	
	private Vector<String> getColumnNames() {
		Vector<String> nameColumns = new Vector<String>();
		nameColumns.add("Nome");
		nameColumns.add("Tamanho");
		nameColumns.add("Hash");
		nameColumns.add("Servidor");
		nameColumns.add("");
		  
		return nameColumns;
	}
	
	private void limparFiltro() {
		  tfNomeArquivo.setText("");
	}
}
