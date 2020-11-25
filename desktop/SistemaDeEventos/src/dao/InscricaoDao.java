package dao;

import entitys.Inscricao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import rest.HttpRestInscricao;
import screens.FormMenu;

public class InscricaoDao {

    private int responseCode;

    public int getResponseCode() {
        return responseCode;
    }

    public List<Inscricao> getEventosUser(String eventoId) {
        HttpRestInscricao rest = new HttpRestInscricao();
        List<Inscricao> listInscricao = rest.getInscricoesEvento(eventoId);
        this.responseCode = rest.getResponseCode();
        return listInscricao;
    }

    public Inscricao inscreverUserEvento(Inscricao inscricao) {
        HttpRestInscricao rest = new HttpRestInscricao();
        inscricao = rest.inscreverUserEvento(inscricao);
        this.responseCode = rest.getResponseCode();
        return inscricao;
    }

    public void checkinUserEvento(String inscricaoId) {
        HttpRestInscricao rest = new HttpRestInscricao();
        rest.checkinUserEvento(inscricaoId);
        this.responseCode = rest.getResponseCode();
    }

    /*public void populateTable(JTable tabela, List<User> pList) {
     Object[][] dadosTabela = null;
     Object[] cabecalho = new Object[4];
     cabecalho[0] = "Id";
     cabecalho[1] = "Nome";
     cabecalho[2] = "Usuário";
     cabecalho[3] = "E-mail";
     dadosTabela = new Object[pList.size()][4];
     int lin = 0;
     for (User u : pList) {
     dadosTabela[lin][0] = u.getId();
     dadosTabela[lin][1] = u.getName();
     dadosTabela[lin][2] = u.getUsername();
     dadosTabela[lin][3] = u.getEmail();
     lin++;
     }

     tabela.setModel(
     new DefaultTableModel(dadosTabela, cabecalho) {
     @Override
     public boolean isCellEditable(int row, int column
     ) {
     return false;
     }

     @Override
     public Class getColumnClass(int column
     ) {
     if (column == 2) {
     }
     return Object.class;
     }
     }
     );

     // permite seleção de apenas uma linha da tabela
     tabela.setSelectionMode(0);

     // redimensiona as colunas de uma tabela
     TableColumn column = null;
     for (int i = 0;
     i < tabela.getColumnCount();
     i++) {
     column = tabela.getColumnModel().getColumn(i);
     switch (i) {
     case 0:
     column.setMaxWidth(30);
     column.setPreferredWidth(30);
     column.setCellRenderer(FormMenu.rightRenderer);
     break;
     case 1:
     column.setPreferredWidth(140);
     break;
     //                case 2:
     //                    column.setPreferredWidth(14);
     //                    break;
     }
     }
     }

     public void populateTableSimplified(JTable tabela, List<User> pList) {
     Object[][] dadosTabela = null;
     Object[] cabecalho = new Object[2];
     cabecalho[0] = "Id";
     cabecalho[1] = "Nome";
     dadosTabela = new Object[pList.size()][2];
     int lin = 0;
     for (User u : pList) {
     dadosTabela[lin][0] = u.getId();
     dadosTabela[lin][1] = u.getName();
     lin++;
     }

     tabela.setModel(
     new DefaultTableModel(dadosTabela, cabecalho) {
     @Override
     public boolean isCellEditable(int row, int column
     ) {
     return false;
     }

     @Override
     public Class getColumnClass(int column
     ) {
     if (column == 2) {
     }
     return Object.class;
     }
     }
     );

     // permite seleção de apenas uma linha da tabela
     tabela.setSelectionMode(0);

     // redimensiona as colunas de uma tabela
     TableColumn column = null;
     for (int i = 0;
     i < tabela.getColumnCount();
     i++) {
     column = tabela.getColumnModel().getColumn(i);
     switch (i) {
     case 0:
     column.setMaxWidth(30);
     column.setPreferredWidth(30);
     column.setCellRenderer(FormMenu.rightRenderer);
     break;
     case 1:
     column.setPreferredWidth(140);
     break;
     //                case 2:
     //                    column.setPreferredWidth(14);
     //                    break;
     }
     }
     }*/
}
