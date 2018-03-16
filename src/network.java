import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class network {
    private static final String link = "https://api.telegram.org/bot6666666666666666666666666666666666666666666";
    private static final String sendMessage = "/sendMessage?";
    private static final String getUpdates = "/getUpdates?";
    private static final String offset = "offset=";
    private static final String timeout = "&timeout=";
    private static final String chat_id = "&chat_id=";
    private static final String text = "&text=";
    private static final String markdown = "&parse_mode=Markdown";
    static int updateID = 628395604;
    //    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080));
    private URL reqlink;

    JSONObject postKey(JSONObject keyJson) throws IOException {
        URL url = new URL(link + sendMessage);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setDoOutput(true);
        httpConn.addRequestProperty("Content-Type", "application/json");
        httpConn.setRequestMethod("POST");
        httpConn.connect();
        OutputStream stream = httpConn.getOutputStream();
        stream.write(keyJson.toString().getBytes());
        stream.flush();
        stream.close();
        InputStream inputStream = httpConn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer returnMessage = new StringBuffer("");
        String thisLine;
        while ((thisLine=br.readLine())!=null)returnMessage.append(thisLine);

        return new JSONObject(returnMessage.toString());
    }

    void postMessage(int id, String sendText) throws IOException {

        StringBuffer openlink = new StringBuffer(link);
        reqlink = new URL(openlink.append(sendMessage).append(chat_id).append(id).append(text).append(escape(sendText)).append(markdown).toString());
        BufferedReader in = new BufferedReader(new InputStreamReader((reqlink.openConnection()).getInputStream()));
        StringBuffer message = new StringBuffer("");
        String i;
        while ((i = in.readLine()) != null) message.append(i);
    }

    JSONObject getUpdates(int timeouttime) throws IOException {
        StringBuffer openlink = new StringBuffer(link);
        openlink.append("/getUpdates?");
        reqlink = new URL(openlink.append(offset).append(updateID).append(timeout).append(timeouttime).toString());
        BufferedReader in = new BufferedReader(new InputStreamReader((reqlink.openConnection()).getInputStream()));
        String i;
        StringBuffer message = new StringBuffer("");
        while ((i = in.readLine()) != null) message.append(i);
        return new JSONObject(message.toString());
    }

    //
    String escape(String s) {
        return s.replaceAll("%", "%25")
                .replaceAll("0D0AzX", "%0D%0A")
                .replaceAll(" ", "%20")
                .replaceAll("\\*", "%2A")
                .replaceAll("'", "%27")
                .replaceAll("!", "%21")
                .replaceAll("&", "%26")
                .replaceAll("#", "%23")
                .replaceAll("\\$", "%24")
                .replaceAll(",", "%2C")
                .replaceAll(":", "%3A")
                .replaceAll("\\?", "%3F")
                .replaceAll("\"", "%22")
                .replaceAll("\\(", "%28")
                .replaceAll("\\)", "%29")
                .replaceAll("\\+", "%2B")
                .replaceAll("/", "%2F")
                .replaceAll(";", "%3B")
                .replaceAll("<", "%3C")
                .replaceAll("=", "%3D")
                .replaceAll(">", "%3E")
                .replaceAll("@", "%40")
                .replaceAll("\\\\", "%5C")
                .replaceAll("\\|", "%7C");

    }
}
