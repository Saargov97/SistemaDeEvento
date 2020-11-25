/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.UserDao;
import entitys.User;
import java.awt.Image;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import screens.FormLogin;

/**
 *
 * @author Rafa_
 */
public class SistemaDeEventos {
    
    private static UserDao ProxyUser;
    private static List<User> lsUser;
    

    public static void main(String[] args) {
        try {
            UserDao ProxyUser = new UserDao();
            lsUser = ProxyUser.getUsers();

            /*for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
             if ("SystemLookAndFeel".equals(info.getName())) {
             try {
             UIManager.setLookAndFeel(info.getClassName());
             } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
             Logger.getLogger(Watcher.class.getName()).log(Level.SEVERE, null, ex);
             }
             break;
             }
             }*/
            FormLogin login = new FormLogin();
            FormLogin.setListUser(lsUser);
            login.setLocationRelativeTo(null);

            login.setVisible(true);
        } catch (Exception e) {
            System.out.println("Erro: " + e.toString());
            JOptionPane.showMessageDialog(null, "Problema ao iniciar aplicação. Por favor, verifique!");
        }
    }

    private void setIconImage(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
