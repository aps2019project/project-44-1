package client;

import com.google.gson.Gson;
import server.Request;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class RequestSender {
    private static RequestSender REQUEST_SENDER;
    private BufferedWriter bufferedWriter;
    private Gson gson;


    public static RequestSender getInstance() {
        return REQUEST_SENDER;
    }

    public RequestSender(OutputStream outputStream) {
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        this.gson = new Gson();
        REQUEST_SENDER = this;
    }

    public void sendRequest(Request request) {
        try {
            bufferedWriter.write(gson.toJson(request));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
