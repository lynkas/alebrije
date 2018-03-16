import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class options {

    nep nep = new nep();
    private JSONObject buttonJson = new JSONObject();

    options() throws IOException {
        JSONArray showButton = new JSONArray();
        JSONArray function = new JSONArray();
        JSONObject function0 = new JSONObject();
        function0.put("text", "Show All by Time");
//        function0.put("resize_keyboard",true);
        JSONObject function1 = new JSONObject();

        function1.put("text", "Show All by Shows");
//        function1.put("resize_keyboard",true);

        JSONObject function2 = new JSONObject();
        function2.put("text", "Update");

//        function.put(function0);
//        function.put(function1);
        function.put(function2);
        showButton.put(function);
        JSONObject keyboard = new JSONObject();
        keyboard.put("keyboard", showButton);
        keyboard.put("resize_keyboard", true);
        buttonJson.put("chat_id", 0);

        buttonJson.put("reply_markup", keyboard);
        buttonJson.put("text", "New New New New");

    }

    JSONObject inLine() {
        JSONObject allInLine = new JSONObject();
        JSONArray inLineKeyboard = new JSONArray();
        for (String showName : nep.getShowSet()) {
            JSONObject thisShow = new JSONObject();
            thisShow.put("text", showName);
            thisShow.put("callback_data", showName);
            JSONArray showLine = new JSONArray();
            showLine.put(thisShow);
            inLineKeyboard.put(showLine);
        }
        JSONObject inline_keyboard = new JSONObject();
        inline_keyboard.put("inline_keyboard", inLineKeyboard);
        allInLine.put("chat_id", 0);
        allInLine.put("reply_markup", inline_keyboard);
        allInLine.put("text", "These are the New Shows in 2 Weeks");


        return allInLine;
    }


    JSONObject getshowButton(int chat_id) {
        return buttonJson.put("chat_id", chat_id);
    }

    nep getNep() {
        return nep;
    }

    JSONObject getInLine(int chat_id) {
        return inLine().put("chat_id", chat_id);
    }
}

