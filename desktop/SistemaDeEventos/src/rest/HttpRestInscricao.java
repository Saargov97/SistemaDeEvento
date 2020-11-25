package rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entitys.Inscricao;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static rest.HttpRestInterface.httpRestPrincipal;
import static rest.HttpRestInterface.urlDefault;

public class HttpRestInscricao implements HttpRestInterface {

    @Override
    public int getResponseCode() {
        return httpRestPrincipal.getResponseCode();
    }

    public List<Inscricao> getInscricoesEvento(String eventoId) {
        List<Inscricao> listInscricao;

        String url = urlDefault + "/inscricaoEvento/" + eventoId;
        String json = "";

        try {
            json = httpRestPrincipal.sendGet(url);
        } catch (Exception ex) {
            Logger.getLogger(HttpRestAuth.class.getName()).log(Level.SEVERE, null, ex);
        }

        Gson gson = new Gson();

        Type listInscricaoType = new TypeToken<List<Inscricao>>() {
        }.getType();
        listInscricao = gson.fromJson(json, listInscricaoType);

        return listInscricao;
    }

    public Inscricao inscreverUserEvento(Inscricao inscricao) {

        Gson gson = new Gson();
        String json = gson.toJson(inscricao);

        String url = urlDefault + "/inscricao";

        try {
            json = httpRestPrincipal.sendPost(url, json);
        } catch (Exception ex) {
            Logger.getLogger(HttpRestAuth.class.getName()).log(Level.SEVERE, null, ex);
        }

        inscricao = gson.fromJson(json, Inscricao.class);

        return inscricao;
    }

    public void checkinUserEvento(String inscricaoId) {
        String url = urlDefault + "/checkin/" + inscricaoId;

        try {
            httpRestPrincipal.sendPut(url, "");
        } catch (Exception ex) {
            Logger.getLogger(HttpRestAuth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
