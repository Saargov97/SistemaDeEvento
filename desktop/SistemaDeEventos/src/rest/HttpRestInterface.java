package rest;

public interface HttpRestInterface {
    final HttpRestPrincipal httpRestPrincipal = new HttpRestPrincipal();

    final String USER_AGENT = "Mozilla/5.0";
    static final String urlDefault = "http://localhost:8081";

    public int getResponseCode();
}