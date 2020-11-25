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
public class Inscricao {

    private int id;
    private int eventoId;
    private int userId;
    private String des_hash;
    private String des_qrcode;
    private int ind_checkin;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Evento Evento;
    private User User;

    public Evento getEvento() {
        return Evento;
    }

    public void setEvento(Evento Evento) {
        this.Evento = Evento;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDes_hash() {
        return des_hash;
    }

    public void setDes_hash(String des_hash) {
        this.des_hash = des_hash;
    }

    public String getDes_qrcode() {
        return des_qrcode;
    }

    public void setDes_qrcode(String des_qrcode) {
        this.des_qrcode = des_qrcode;
    }

    public int getInd_checkin() {
        return ind_checkin;
    }

    public void setInd_checkin(int ind_checkin) {
        this.ind_checkin = ind_checkin;
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
