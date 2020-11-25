package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import entitys.User;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static rest.HttpRestInterface.httpRestPrincipal;
import static rest.HttpRestInterface.urlDefault;

public class HttpRestUser implements HttpRestInterface {

    @Override
    public int getResponseCode() {
        return httpRestPrincipal.getResponseCode();
    }

    public List<User> getUsers() {
        List<User> listUser;

        String url = urlDefault + "/users";
        String json = "";

        try {
            json = httpRestPrincipal.sendGet(url);
        } catch (Exception ex) {
            Logger.getLogger(HttpRestAuth.class.getName()).log(Level.SEVERE, null, ex);
        }

        Gson gson = new Gson();

        Type listUserType = new TypeToken<List<User>>() {
        }.getType();
        listUser = gson.fromJson(json, listUserType);

        return listUser;
    }

    public User createUser(User user) {

        Gson gson = new Gson();
        String json = gson.toJson(user);

        String url = urlDefault + "/newUser";

        try {
            json = httpRestPrincipal.sendPost(url, json);
        } catch (Exception ex) {
            Logger.getLogger(HttpRestAuth.class.getName()).log(Level.SEVERE, null, ex);
        }

        user = gson.fromJson(json, User.class);

        return user;
    }
}
