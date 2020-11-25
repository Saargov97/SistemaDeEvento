package rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entitys.Evento;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static rest.HttpRestInterface.httpRestPrincipal;
import static rest.HttpRestInterface.urlDefault;

public class HttpRestEvento implements HttpRestInterface {

    @Override
    public int getResponseCode() {
        return httpRestPrincipal.getResponseCode();
    }

    public List<Evento> getEventosUser(String userId) {
        List<Evento> listEvento;

        String url = urlDefault + "/eventosUsuario/" + userId;
        String json = "";

        try {
            json = httpRestPrincipal.sendGet(url);
        } catch (Exception ex) {
            Logger.getLogger(HttpRestAuth.class.getName()).log(Level.SEVERE, null, ex);
        }

        Gson gson = new Gson();

        Type listEventoType = new TypeToken<List<Evento>>() {
        }.getType();
        listEvento = gson.fromJson(json, listEventoType);

        return listEvento;
    }
}
