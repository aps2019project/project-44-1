package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ResponseSender {
    private BufferedWriter bufferedWriter;
    private Gson gson;

    public ResponseSender(OutputStream outputStream,Gson gson) {
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        this.gson = gson;
    }

    //the cell of card and owner of the Card are transient
    public void sendResponse(Response response){
        try {
            System.out.println(gson.toJson(response));
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
