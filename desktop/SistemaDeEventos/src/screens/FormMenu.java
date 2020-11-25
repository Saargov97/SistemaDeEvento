/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import dao.EventoDao;
import dao.InscricaoDao;
import dao.UserDao;
import entitys.Evento;
import entitys.Inscricao;
import entitys.Basico;
import entitys.User;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FormMenu extends javax.swing.JFrame {

    //private final Functions functions;
    private static User loggedUser;
    private static EventoDao ProxyEvento;
    private static List<Evento> listEventosLoggedUser;
    private static DefaultListModel modelEventos = new DefaultListModel();
    private static InscricaoDao ProxyInscricao;
    private static List<Inscricao> listInscricao;
    private static DefaultListModel modelInscricoes = new DefaultListModel();
    private Map<String, List<Inscricao>> subsEvento = new HashMap<String, List<Inscricao>>();
    private static List<Basico> listBasico = new ArrayList<Basico>();
    private static List<String> listInscricaoSinc = new ArrayList<String>();
    private Map<String, List<Basico>> basicEvento = new HashMap<String, List<Basico>>();
    private static UserDao ProxyUser;

    public String hasCheckIn(int ind) {
        if (ind == 1) {
            return "Presente";
        } else {
            return "Ausente";
        }
    }

    public String getNome(String nome) {
        if (nome == null) {
            return "Indefinido";
        } else {
            return nome;
        }
    }

    public FormMenu(User user) {
        initComponents();

        this.loggedUser = user;

        ProxyEvento = new EventoDao();
        ProxyInscricao = new InscricaoDao();
        ProxyUser = new UserDao();
        jLstEventos.removeAll();
        jLstEventos.setModel(modelEventos);
        jLstInscricoes.removeAll();
        jLstInscricoes.setModel(modelInscricoes);

        //jLstEventos.getSelectedIndex()
        this.setTitle("Sistema de Eventos - Início");

        //functions = new Functions();
        //functions.setIcon(this);
        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                Evento e = listEventosLoggedUser.get(jLstEventos.getSelectedIndex());
                listInscricao = subsEvento.get(String.valueOf(e.getId()));
                modelInscricoes.removeAllElements();
                for (Inscricao i : listInscricao) {
                    modelInscricoes.addElement(i.getUser().getEmail() + " - " + getNome(i.getUser().getNom_pessoa()) + " - " + hasCheckIn(i.getInd_checkin()));
                }
                listBasico = basicEvento.get(String.valueOf(e.getId()));
                for (Basico b : listBasico) {
                    modelInscricoes.addElement(b.getEmail() + " - Indefinido - Presente");
                }
            }
        };
        jLstEventos.addListSelectionListener(listSelectionListener);
    }

    private String NameUser = "Default";

    public String getNameUser() {
        return NameUser;
    }

    public void setNameUser(String pNameUser) {
        this.NameUser = pNameUser;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuLateral = new javax.swing.JPanel();
        BotoesLaterais = new javax.swing.JPanel();
        Logo = new javax.swing.JPanel();
        txtSistema = new javax.swing.JLabel();
        Centro = new javax.swing.JPanel();
        DadosAtuais = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLstEventos = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLstInscricoes = new javax.swing.JList();
        jPanel6 = new javax.swing.JPanel();
        jBtnCheckIn = new javax.swing.JButton();
        jBtnCadastroBasico = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jBtnSincronizar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLblCheckIn = new javax.swing.JLabel();
        jLblCadastroBasico = new javax.swing.JLabel();
        BarraSuperior = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Watcher");
        setExtendedState(1);
        setMinimumSize(new java.awt.Dimension(800, 900));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        MenuLateral.setBackground(new java.awt.Color(72, 70, 70));
        MenuLateral.setMinimumSize(new java.awt.Dimension(200, 100));
        MenuLateral.setPreferredSize(new java.awt.Dimension(200, 100));
        MenuLateral.setLayout(new java.awt.BorderLayout());

        BotoesLaterais.setBackground(new java.awt.Color(72, 70, 70));
        MenuLateral.add(BotoesLaterais, java.awt.BorderLayout.CENTER);

        Logo.setBackground(new java.awt.Color(72, 70, 70));
        Logo.setMaximumSize(new java.awt.Dimension(100, 70));
        Logo.setMinimumSize(new java.awt.Dimension(100, 70));
        Logo.setPreferredSize(new java.awt.Dimension(100, 70));
        Logo.setLayout(new java.awt.BorderLayout());

        txtSistema.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtSistema.setForeground(new java.awt.Color(255, 255, 255));
        txtSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSistema.setText("Sistema de Eventos");
        txtSistema.setToolTipText("");
        Logo.add(txtSistema, java.awt.BorderLayout.CENTER);
        txtSistema.getAccessibleContext().setAccessibleName("Sistema de Eventos");

        MenuLateral.add(Logo, java.awt.BorderLayout.NORTH);

        getContentPane().add(MenuLateral, java.awt.BorderLayout.LINE_START);

        Centro.setLayout(new java.awt.BorderLayout());

        DadosAtuais.setBackground(new java.awt.Color(104, 102, 102));
        DadosAtuais.setLayout(new java.awt.BorderLayout());

        jDesktopPane1.setBackground(new java.awt.Color(104, 102, 102));
        jDesktopPane1.setLayout(new java.awt.GridLayout());

        jPanel1.setBackground(new java.awt.Color(104, 102, 102));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLstEventos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jLstEventos);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Meus Eventos");
        jPanel1.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        jDesktopPane1.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(104, 102, 102));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Inscritos");
        jPanel2.add(jLabel1);

        jLstInscricoes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jLstInscricoes);

        jPanel2.add(jScrollPane2);

        jPanel6.setBackground(new java.awt.Color(104, 102, 102));
        jPanel6.setMaximumSize(new java.awt.Dimension(32767, 80));
        jPanel6.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel6.setPreferredSize(new java.awt.Dimension(260, 80));
        jPanel6.setRequestFocusEnabled(false);

        jBtnCheckIn.setText("Check-In");
        jBtnCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCheckInActionPerformed(evt);
            }
        });

        jBtnCadastroBasico.setText("Cadastro Base");
        jBtnCadastroBasico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCadastroBasicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnCheckIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnCadastroBasico, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnCheckIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCadastroBasico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6);

        jDesktopPane1.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(104, 102, 102));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel5.setBackground(new java.awt.Color(104, 102, 102));
        jPanel5.setMaximumSize(new java.awt.Dimension(32767, 80));
        jPanel5.setMinimumSize(new java.awt.Dimension(0, 80));
        jPanel5.setPreferredSize(new java.awt.Dimension(260, 80));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sincronizar");

        jBtnSincronizar.setText("Sincronizar");
        jBtnSincronizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSincronizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnSincronizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 188, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSincronizar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel5);

        jPanel4.setBackground(new java.awt.Color(104, 102, 102));

        jLblCheckIn.setForeground(new java.awt.Color(255, 255, 255));
        jLblCheckIn.setText("0 presenças para sincronizar");

        jLblCadastroBasico.setForeground(new java.awt.Color(255, 255, 255));
        jLblCadastroBasico.setText("0 cadastros basicos para sincronizar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblCheckIn)
                    .addComponent(jLblCadastroBasico))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblCheckIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLblCadastroBasico)
                .addContainerGap(377, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4);

        jDesktopPane1.add(jPanel3);

        DadosAtuais.add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        Centro.add(DadosAtuais, java.awt.BorderLayout.CENTER);

        BarraSuperior.setBackground(new java.awt.Color(55, 55, 55));
        BarraSuperior.setMaximumSize(new java.awt.Dimension(32767, 70));
        BarraSuperior.setMinimumSize(new java.awt.Dimension(100, 70));
        BarraSuperior.setPreferredSize(new java.awt.Dimension(638, 70));

        javax.swing.GroupLayout BarraSuperiorLayout = new javax.swing.GroupLayout(BarraSuperior);
        BarraSuperior.setLayout(BarraSuperiorLayout);
        BarraSuperiorLayout.setHorizontalGroup(
            BarraSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );
        BarraSuperiorLayout.setVerticalGroup(
            BarraSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        Centro.add(BarraSuperior, java.awt.BorderLayout.NORTH);

        getContentPane().add(Centro, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        /*List<Application> listApplication = new UserDao().getApplicationsUser(this.loggedUser);

         if (listApplication.isEmpty()) {
         JOptionPane.showMessageDialog(null, "Não há aplicações habilitadas para o usuário informado. Por favor, verifique!", "Erro", JOptionPane.ERROR_MESSAGE, null);
         FormLogin login = new FormLogin();
         login.setLocationRelativeTo(null);

         login.setVisible(true);
         this.setVisible(false);
         } else {
         listApplication.stream().forEach((Application application) -> {
         jItemMenu lItemMenu = new jItemMenu();
         lItemMenu.setFormName(application.getFormName());
         lItemMenu.setTextButton(application.getName());
         lItemMenu.jDesktop = jDesktopPane1;
         BotoesLaterais.add(lItemMenu);
         });
         }

         // Testar sem Back-end
         /*for (int x = 0; x < 1; x++) {
         jItemMenu lItemMenu = new jItemMenu();
         // lItemMenu.setFormName(application.getFormName());
         lItemMenu.setFormName("FormUser");
         lItemMenu.setTextButton("User");
         lItemMenu.jDesktop = jDesktopPane1;
         BotoesLaterais.add(lItemMenu);
         }*/
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void jBtnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCheckInActionPerformed
        if (!modelInscricoes.isEmpty()) {
            int index = jLstInscricoes.getSelectedIndex();
            if (index > listInscricao.size() - 1) {
                JOptionPane.showMessageDialog(null, "Dados de cadastro básico não podem ser alterados!", "Erro", JOptionPane.ERROR_MESSAGE, null);
            } else {
                Inscricao i = listInscricao.get(index);
                String eventoId = String.valueOf(i.getEventoId());
                if (i.getInd_checkin() == 0) {
                    i.setInd_checkin(1);
                    listInscricaoSinc.add(String.valueOf(i.getId()));
                } else {
                    i.setInd_checkin(0);
                    listInscricaoSinc.remove(String.valueOf(i.getId()));
                }
                jLblCheckIn.setText(String.valueOf(listInscricaoSinc.size()) + " presenças para sincronizar");
                modelInscricoes.set(index, i.getUser().getEmail() + " - " + getNome(i.getUser().getNom_pessoa()) + " - " + hasCheckIn(i.getInd_checkin()));
                listInscricao.set(index, i);
                subsEvento.put(eventoId, listInscricao);
            }
        }
    }//GEN-LAST:event_jBtnCheckInActionPerformed

    private void jBtnSincronizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSincronizarActionPerformed
        //Save
        for (Map.Entry<String, List<Basico>> entrySet : basicEvento.entrySet()) {
            String key = entrySet.getKey();
            int eventoId = Integer.parseInt(key);
            List<Basico> listB = entrySet.getValue();
            for (Basico b : listB) {
                User u = new User();
                u.setEmail(b.getEmail());
                u.setPassword(b.getPassword());
                u = ProxyUser.createUser(u);
                b.setUserId(u.getId());
                Inscricao i = new Inscricao();
                i.setEventoId(eventoId);
                i.setUserId(u.getId());
                i = ProxyInscricao.inscreverUserEvento(i);
                ProxyInscricao.checkinUserEvento(String.valueOf(i.getId()));
            }
        }
        basicEvento = new HashMap<String, List<Basico>>();
        listBasico = new ArrayList<Basico>();
        for (String i : listInscricaoSinc) {
            ProxyInscricao.checkinUserEvento(i);
        }
        listInscricaoSinc = new ArrayList<String>();
        jLblCheckIn.setText(String.valueOf(listInscricaoSinc.size()) + " presenças para sincronizar");
        jLblCadastroBasico.setText(String.valueOf(basicEvento.size()) + " cadastros basicos para sincronizar");
        
        //Load      
        listEventosLoggedUser = ProxyEvento.getEventosUser(String.valueOf(loggedUser.getId()));
        modelEventos.removeAllElements();
        for (Evento e : listEventosLoggedUser) {
            String eventoId = String.valueOf(e.getId());
            listInscricao = ProxyInscricao.getEventosUser(eventoId);
            subsEvento.put(eventoId, listInscricao);
            modelEventos.addElement(e.getNom_evento());
        }
        listInscricao = null;
        
        JOptionPane.showMessageDialog(null, "Sincronizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
    }//GEN-LAST:event_jBtnSincronizarActionPerformed

    private void jBtnCadastroBasicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCadastroBasicoActionPerformed
        FFrameCadastroBasico lFrame = new FFrameCadastroBasico();//Funcoes.Pesquisar(2, 0);
        lFrame.setLocationRelativeTo(null);
        //   pesquisar.setUndecorated(true);
        lFrame.setResizable(false);
//        pesquisar.setAlwaysOnTop(true);
        lFrame.setVisible(true);
        //lFrame.fpc = this;
        lFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Evento e = listEventosLoggedUser.get(jLstEventos.getSelectedIndex());
                String eventoId = String.valueOf(e.getId());
                if (lFrame.getSave()) {
                    Basico b = new Basico();
                    b.setEventoId(e.getId());
                    b.setEmail(lFrame.getEmail());
                    b.setPassword(lFrame.getPassword());
                    if (listBasico == null) {
                        listBasico = new ArrayList<Basico>();
                    }
                    listBasico.add(b);
                    basicEvento.put(eventoId, listBasico);
                    modelInscricoes.addElement(b.getEmail() + " - Indefinido - Presente");
                    jLblCadastroBasico.setText(String.valueOf(listBasico.size()) + " cadastros basicos para sincronizar");
                }
            }
        });
    }//GEN-LAST:event_jBtnCadastroBasicoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BarraSuperior;
    private javax.swing.JPanel BotoesLaterais;
    private javax.swing.JPanel Centro;
    private javax.swing.JPanel DadosAtuais;
    private javax.swing.JPanel Logo;
    private javax.swing.JPanel MenuLateral;
    private javax.swing.JButton jBtnCadastroBasico;
    private javax.swing.JButton jBtnCheckIn;
    private javax.swing.JButton jBtnSincronizar;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLblCadastroBasico;
    private javax.swing.JLabel jLblCheckIn;
    private javax.swing.JList jLstEventos;
    private javax.swing.JList jLstInscricoes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel txtSistema;
    // End of variables declaration//GEN-END:variables
}