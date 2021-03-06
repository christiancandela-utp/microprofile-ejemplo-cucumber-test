package co.edu.utp.laboratorio.microprofile.ejemplo.test.util;

import io.restassured.response.Response;

public class DataManager {
    private static ThreadLocal<DataManager> instance = new ThreadLocal<>();

    private Response response;
    private String token;

    private DataManager(){

    }

    public static DataManager getInstance(){
        if( instance.get() == null ){
            instance.set(new DataManager());
        }
        return instance.get();
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
