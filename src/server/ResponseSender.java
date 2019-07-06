package server;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ResponseSender {
    private BufferedWriter bufferedWriter;
    private Gson gson = new Gson();

    public ResponseSender(OutputStream outputStream) {
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public void sendResponse(Response response){
        try {
            bufferedWriter.write(gson.toJson(response));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeBufferedWriter() throws IOException {
        this.bufferedWriter.close();
    }

}
