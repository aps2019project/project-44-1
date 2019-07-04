package server;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ResponseSender {
    private BufferedWriter bufferedWriter;
    private Gson gson;

    public ResponseSender(OutputStream outputStream) {
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        gson = new Gson();
    }

    public void sendResponse(Response response){
        try {
            bufferedWriter.write(gson.toJson(response));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
