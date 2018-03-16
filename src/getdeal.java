import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class getdeal {


    public static Map<String, String> receMes(JSONObject mesJSON) {
        Map<String, String> message = new HashMap<>();
        if ((boolean) mesJSON.get("ok")) {
            JSONArray mesArray = mesJSON.getJSONArray("result");
            for (Object k : mesArray) {
                JSONObject m = new JSONObject(k.toString());
                network.updateID = (int) m.get("update_id") + 1;
                if (m.has("callback_query")) {
                    m = new JSONObject(m.get("callback_query").toString());
                }
                JSONObject mMessage = new JSONObject(m.get("message").toString());
                JSONObject mFrom = new JSONObject(mMessage.get("from").toString());
                JSONObject mChat = new JSONObject(mMessage.get("chat").toString());

                message.put("update_id", Integer.toString((network.updateID - 1)));
                message.put("message_id", mMessage.get("message_id").toString());
                message.put("date", mMessage.get("date").toString());
                if (mMessage.has("text"))
                    message.put("text", (String) mMessage.get("text"));
                if (m.has("data"))
                    message.put("data", (String) m.get("data"));
                getAllToMap(mFrom, message);
                getAllToMap(mChat, message);

            }


        }
        return message;
    }

    static void getAllToMap(JSONObject j, Map<String, String> map) {
        Set<String> a = j.keySet();
        for (String b : a) {
            map.put(b, j.get(b).toString());
        }
    }
}

