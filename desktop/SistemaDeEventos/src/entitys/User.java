/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.util.Date;

public class User {

    private int id;
    private String email;
    private String password;
    private String password2;
    private String nom_pessoa;
    private String num_cpf;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getNom_pessoa() {
        return nom_pessoa;
    }

    public void setNom_pessoa(String nom_pessoa) {
        this.nom_pessoa = nom_pessoa;
    }

    public String getNum_cpf() {
        return num_cpf;
    }

    public void setNum_cpf(String num_cpf) {
        this.num_cpf = num_cpf;
    }
    
    

}
