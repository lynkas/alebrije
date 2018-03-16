import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class nep {
    private static ArrayList<episode> eplist = new ArrayList<>();
    private static Map<String, ArrayList<episode>> show;
    private static Set<String> showSet;

    nep() throws IOException {
        FileReader nepFile;
        BufferedReader br;
        JSONArray nepArray;
        show = new HashMap<>();
        nepFile = new FileReader("/srv/http/nep.json");
        br = new BufferedReader(nepFile);
        nepArray = new JSONArray(br.readLine());
        for (Object i : nepArray) {
            JSONArray b = new JSONArray(i.toString());
            episode e = new episode((String) b.get(8), (String) b.get(9), (String) b.get(10), (String) b.get(11), (double) b.get(0));
            eplist.add(e);


            if (show.get(e.getShowName()) == null) {
                show.put(e.getShowName(), new ArrayList<>());
            }
            show.get(e.getShowName()).add(e);
        }

        showSet = show.keySet();

    }

    public ArrayList<episode> getEplist() {
        return eplist;
    }

    public Map<String, ArrayList<episode>> getShow() {
        return show;
    }

    public Set<String> getShowSet() {
        return showSet;
    }
}
