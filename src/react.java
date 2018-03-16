import java.io.IOException;
import java.util.Map;

public class react {
    network network = new network();
    options options = new options();

    react() throws IOException {
        Map<String, String> thisGuy = getdeal.receMes(network.getUpdates(600));
        if (thisGuy.size() == 0 || thisGuy.get("type").equals("group") || thisGuy.get("text") == null) return;
        else if (thisGuy.get("text").equals("Update") || thisGuy.get("text").equals("/start"))
            update(thisGuy.get("id"));
//        else if (thisGuy.get("text").equals("Show All by Time")) byTime(thisGuy.get("id"));
//        else if (thisGuy.get("text").equals("Show All by Shows")) byShows(thisGuy.get("id"));
        else {
            if (thisGuy.get("data") != null) {
                otherWords(thisGuy.get("data"), thisGuy.get("id"));
            } else {
                otherWords(thisGuy.get("text"), thisGuy.get("id"));
            }
        }
    }

    void update(String chat_id) throws IOException {
        options = null;
        options = new options();
        network.postKey(options.getshowButton(Integer.parseInt(chat_id))).toString();
        byShows(chat_id);
    }

    void byTime(String chat_id) throws IOException {
        StringBuffer text = new StringBuffer("");
        for (episode i : options.getNep().getEplist()) {
            text.append(i.toString());
        }
        network.postMessage(Integer.parseInt(chat_id), text.toString());
    }

    void byShows(String chat_id) throws IOException {
        network.postKey(options.getInLine(Integer.parseInt(chat_id))).toString() ;
    }

    void otherWords(String text, String chat_id) throws IOException {
        if (options.getNep().getShow().get(text) == null) {
            network.postMessage(Integer.parseInt(chat_id), "I don't find it!");
        } else {
            network.postMessage(Integer.parseInt(chat_id), "*" + text + "*");
            for (episode i : options.getNep().getShow().get(text)) {
                network.postMessage(Integer.parseInt(chat_id), i.toShortString());
            }
            network.postMessage(Integer.parseInt(chat_id), "*End*");


        }

    }
}