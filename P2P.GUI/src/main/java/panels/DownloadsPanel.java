package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import components.ButtonColumn;
import constants.CodigoStatusDownloadConstant;
import viewmodels.FileViewModel;
import viewmodels.enums.StatusDownloadEnum;

public class DownloadsPanel extends JPanel {
	private JTable table;
	private String statusEmAndamento = "Em andamento", 
			statusPausado = "Pausado", 
			statusCancelado = "Cancelado", 
			statusConcluido = "Concluído";
	private int indexPausar = 3, 
			indexRetomar = 4, 
			indexCancelar = 5, 
			indexExcluir = 6;

	/**
	 * Create the panel.
	 */
	public DownloadsPanel() {
		setToolTipText("Meus downloads");
		Color defaultColor = new Color(240, 248, 255);
		Color white = new Color(255, 255, 255);
		Font defaultFontTitle = new Font("Tahoma", Font.BOLD, 12);
		setBackground(defaultColor);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Meus downloads");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(defaultFontTitle);
		lblNewLabel.setBounds(10, 11, 808, 14);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 808, 333);
		add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel(getColumnsData(), getColumnNames());
	    table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			@Override
	        public boolean isCellEditable(int row, int column) {
				if (column >= 3) {
					String statusArquivo = table.getModel().getValueAt(row, 2).toString();
					
					if (statusArquivo.equals(statusEmAndamento)) {
						return column == indexPausar || column == indexCancelar; 
					} else if (statusArquivo.equals(statusPausado)) {
						return column == indexRetomar || column == indexCancelar;
					} else if (statusArquivo.equals(statusConcluido) || statusArquivo.equals(statusCancelado)) {
						return column == indexExcluir;
					} 
				}
				return false;         
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

		addButtons();		
		scrollPane.setViewportView(table);
		
	}
	
	private Vector<Vector<Object>> getColumnsData(){
		//TODO: obter a lista de arquivos no tipo ArrayList<viewmodels.FileViewModel>
		ArrayList<FileViewModel> arquivos = new ArrayList<FileViewModel>();
		int rowCount = 0;
		FileViewModel arquivo1 = new FileViewModel("Video.mp4", "60MB", StatusDownloadEnum.EmAndamento);
		FileViewModel arquivo2 = new FileViewModel("Song.mp3", "5MB", StatusDownloadEnum.Pausado);	
		FileViewModel arquivo3 = new FileViewModel("Pablo.png", "60KB", StatusDownloadEnum.Cancelado);	
		FileViewModel arquivo4 = new FileViewModel("aula.xlsx", "1GB", StatusDownloadEnum.Concluido);	
				
		arquivos.add(arquivo1);
		arquivos.add(arquivo2);
		arquivos.add(arquivo3);
		arquivos.add(arquivo4);
		
		Vector<Vector<Object>> dataColumns = new Vector<Vector<Object>>();

		for (FileViewModel a : arquivos) {
			Vector<Object> arquivoVector = new Vector<Object>();
		    arquivoVector.add(a.nome);
		    arquivoVector.add(a.tamanho);
		    arquivoVector.add(a.statusDownload.getText());
		    addActionButtonsNames(arquivoVector, a.statusDownload.codigoStatusDownload, rowCount);
		    rowCount++;
		    dataColumns.add(arquivoVector);
		}
		
		return dataColumns;
	}
	
	private void addActionButtonsNames(Vector<Object> obj, int codigoStatusDownload, int row) {		
		if (codigoStatusDownload == CodigoStatusDownloadConstant.EmAndamento) {
			obj.add("Pausar");
			obj.add("");
		    obj.add("Cancelar");
		    obj.add("");
		} else if (codigoStatusDownload == CodigoStatusDownloadConstant.Pausado) {
			obj.add("");
			obj.add("Retormar");
		    obj.add("Cancelar");
		    obj.add("");
		 } else if (codigoStatusDownload == CodigoStatusDownloadConstant.Concluido || 
				codigoStatusDownload == CodigoStatusDownloadConstant.Cancelado) {
			obj.add("");
			obj.add("");
		    obj.add("");
		    obj.add("Limpar");
		} 
	}
	
	private Vector<String> getColumnNames() {
		Vector<String> nameColumns = new Vector<String>();
		nameColumns.add("Nome");
		nameColumns.add("Tamanho");
		nameColumns.add("Status");
		nameColumns.add(""); //pausar
		nameColumns.add(""); //retormar
		nameColumns.add(""); //cancelar
		nameColumns.add(""); //limpar da lista
		  
		return nameColumns;
	}

	private void addButtons() {
		addPausarDownloadButton();
		addRetomarDownloadButton();		
		addCancelarDownloadButton();
		addExcluirDownloadButton();
	}

	private void addPausarDownloadButton() {
		Action pausarDownload = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Trigger para pausar download");
			}
		    
		};		
		ButtonColumn btnPausar = new ButtonColumn(table, pausarDownload, indexPausar);
		btnPausar.setMnemonic(KeyEvent.VK_D);
	}
	
	private void addRetomarDownloadButton() {
		Action retomarDownload = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Trigger para retomar download");
			}
		    
		};
		
		ButtonColumn btnRetomar = new ButtonColumn(table, retomarDownload, indexRetomar);
		btnRetomar.setMnemonic(KeyEvent.VK_D);
	}
	
	private void addCancelarDownloadButton() {
		Action cancelarDownload = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Trigger para cancelar download");
			}
		    
		};
		
		ButtonColumn btnCancelar = new ButtonColumn(table, cancelarDownload, indexCancelar);
		btnCancelar.setMnemonic(KeyEvent.VK_D);
	}
	
	private void addExcluirDownloadButton() {
		Action excluirDownload = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				//removendo da lista do JPanel
				JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
		        
				System.out.println("Trigger para excluir download no backend");
			}
		    
		};
		ButtonColumn btnExcluir = new ButtonColumn(table, excluirDownload, indexExcluir);
		btnExcluir.setMnemonic(KeyEvent.VK_D);
	}
}
