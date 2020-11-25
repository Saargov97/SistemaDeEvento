/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.util.Date;

/**
 *
 * @author Rafa_
 */
public class Evento {
    
    private int id;
    private String nom_evento;
    private String dta_evento;
    private Double vlr_evento;
    private String des_carga_horaria;
    private int num_vaga;
    private int userId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_evento() {
        return nom_evento;
    }

    public void setNom_evento(String nom_evento) {
        this.nom_evento = nom_evento;
    }

    public String getDta_evento() {
        return dta_evento;
    }

    public void setDta_evento(String dta_evento) {
        this.dta_evento = dta_evento;
    }

    public Double getVlr_evento() {
        return vlr_evento;
    }

    public void setVlr_evento(Double vlr_evento) {
        this.vlr_evento = vlr_evento;
    }

    public String getDes_carga_horaria() {
        return des_carga_horaria;
    }

    public void setDes_carga_horaria(String des_carga_horaria) {
        this.des_carga_horaria = des_carga_horaria;
    }

    public int getNum_vaga() {
        return num_vaga;
    }

    public void setNum_vaga(int num_vaga) {
        this.num_vaga = num_vaga;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    
    
    
}
