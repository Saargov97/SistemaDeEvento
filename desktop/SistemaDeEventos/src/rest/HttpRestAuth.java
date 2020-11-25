package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import utils.Functions;
import entitys.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import static rest.HttpRestInterface.httpRestPrincipal;
import static rest.HttpRestInterface.urlDefault;

public class HttpRestAuth implements HttpRestInterface {

    @Override
    public int getResponseCode() {
        return httpRestPrincipal.getResponseCode();
    }

    public User login(User user) {//throws IllegalAccessException {
        String json = "{"
                + "\"email\": \"" + user.getEmail() + "\""
                + ", \"password\": \"" + user.getPassword() + "\""
                + "}";

        String url = urlDefault + "/login";

        try {
            json = httpRestPrincipal.sendPost(url, json);

            Functions functions = new Functions();
            
            user.setId(Integer.parseInt(functions.getJsonParam(json, "user", "id")));
            //user.setToken(functions.getJsonParam(json, "user", "token"));
            user.setEmail(functions.getJsonParam(json, "user", "email"));
            user.setNom_pessoa(functions.getJsonParam(json, "user", "nom_pessoa"));            
            user.setNum_cpf(functions.getJsonParam(json, "user", "num_cpf"));
        } catch (Exception ex) {
            Logger.getLogger(HttpRestAuth.class.getName()).log(Level.SEVERE, null, ex);
            //throw new IllegalAccessException(ex.getMessage()); 
        }

        return user;
    }

}
