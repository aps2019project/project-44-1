package client;

import com.google.gson.Gson;
import server.Request;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class RequestSender {
    private static RequestSender REQUEST_SENDER = new RequestSender();
    private BufferedWriter bufferedWriter;
    private Gson gson = new Gson();


    public static RequestSender getInstance() {
        return REQUEST_SENDER;
    }

    private RequestSender() {
    }

    public void sendRequest(Request request) {
        try {
            bufferedWriter.write(gson.toJson(request));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBufferedWriter(OutputStream outputStream) {
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public void closeBufferedWriter() throws IOException {
        bufferedWriter.close();
    }

}
