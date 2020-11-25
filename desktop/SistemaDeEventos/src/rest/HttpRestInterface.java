package rest;

public interface HttpRestInterface {
    final HttpRestPrincipal httpRestPrincipal = new HttpRestPrincipal();

    final String USER_AGENT = "Mozilla/5.0";
    static final String urlDefault = "http://177.44.248.1:8081";

    public int getResponseCode();
}