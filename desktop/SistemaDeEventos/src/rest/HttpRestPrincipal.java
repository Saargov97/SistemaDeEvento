package rest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRestPrincipal {

    private final String USER_AGENT = "Mozilla/5.0";
    private static final int BUFFER_SIZE = 4096;
    private int responseCode;

    public int getResponseCode() {
        return this.responseCode;
    }

    // HTTP GET request
    public String sendGet(String pUrl) throws Exception {

        String url = pUrl;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GETt
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "UTF-8");
        //con.setRequestProperty("authorization", token);

        this.responseCode = con.getResponseCode();
        System.err.println("\nSending 'GET' request to URL : " + url);
        System.err.println("Response Code : " + responseCode);

        StringBuilder response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        //print result
        return response.toString();

    }

    // HTTP POST request
    public String sendPost(String pURL, String parametros) throws Exception {
        String url = pURL;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        //con.setRequestProperty("authorization", token);

        String urlParameters = parametros;
        urlParameters = java.net.URLDecoder.decode(urlParameters, "UTF-8");

        // Send post request
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();

        }

        this.responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + pURL);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        StringBuilder response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        //print result
        return response.toString();

    }

    // HTTP PUT request
    public String sendPut(String pURL, String parametros) throws Exception {
        String url = pURL;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("PUT");
        con.setRequestProperty("Accept-Charset", "UTF-8");
        //con.setRequestProperty("authorization", token);

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept-Language", "UTF-8");

        String urlParameters = parametros;

        // Send post request
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        this.responseCode = con.getResponseCode();
        System.out.println("\nSending 'PUT' request to URL : " + pURL);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        StringBuilder response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        //print result
        return response.toString();

    }

    // HTTP DELETE request
    public String sendDelete(String pURL, String parametros) throws Exception {

        URL obj = new URL(pURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Accept-Charset", "UTF-8");
        //con.setRequestProperty("authorization", token);

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept-Language", "UTF-8");

        String urlParameters = parametros;

        // Send post request
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        this.responseCode = con.getResponseCode();
        System.out.println("\nSending 'DELETE' request to URL : " + pURL);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        StringBuilder response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        //print result
        return response.toString();

    }

    public void downloadFile(String fileURL, String extension, String saveFile) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            /*  if (disposition != null) {
             // extracts file name from header field
             int index = disposition.indexOf("filename=");
             if (index > 0) {
             fileName = disposition.substring(index + 10,
             disposition.length() - 1);
             }
             } else {
             // extracts file name from URL
             fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
             fileURL.length());
             }*/
            if (saveFile.contains(extension)) {
                fileName = saveFile;
            } else {
                fileName = saveFile + extension;
            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}
