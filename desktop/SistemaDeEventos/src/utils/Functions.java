package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Frame;
import java.awt.Toolkit;

public class Functions {
    public void setIcon(Frame frame) {
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../images/icon.png")));;;
    }
    
    public String getJsonParam(String json, String objectName, String paramName) {
        Gson gson = new Gson();
        return (gson.fromJson(json, JsonObject.class).getAsJsonObject().get(objectName).getAsJsonObject().get(paramName).toString().replaceAll("\"", ""));
    }
}
