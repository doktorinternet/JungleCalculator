import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Champion { //"dragontail-7.17.2/7.17.2/data/en_GB/champion.json"

    // Declare stat variables for champions

    String name;
    ArrayList<Stats> stats;

    JSONParser parser = new JSONParser();

    private Object getFile(){
        try{
            return parser.parse(new FileReader("dragontail-7.17.2/7.17.2/data/en_GB/champion.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    private double[] getChampStats(){

    }

    public Champion(String champName){

        Object obj = getFile();

        JSONObject champDB =  (JSONObject) obj;
        JSONObject champData;

            /*
            String name = (String) jsonObject.get("name");
            System.out.println(name);

            String city = (String) jsonObject.get("city");
            System.out.println(city);

            String job = (String) jsonObject.get("job");
            System.out.println(job);
            */

            // loop array
        JSONArray data = (JSONArray) champDB.get("data");
        Iterator<String> champList = data.iterator();

        while (champList.hasNext()) {
            String cN = champList.next();
            if(cN.equalsIgnoreCase(champName)) { // TODO skapa hjälpklass för metoder som denna. Gör så att namnet kan skrivas in godtyckligt utan att ge fel

                System.out.println(cN);
                name = cN;
                champData = (JSONObject) champDB.get("data");
                data = (JSONArray) champData.get(this.name);
                Iterator<String> champElements = data.iterator();

                while (champElements.hasNext()){
                    String element = champElements.next();

                    if (element.equalsIgnoreCase("stats")) {
                        champData = (JSONObject) champData.get(this.name);
                        data = (JSONArray) champData.get(element);
                        Iterator<String> statsJSON = data.iterator();

                        int i = 0;
                        while (statsJSON.hasNext())
                        {
                            stats.get(i).type = statsJSON.next();
                            i++;
                        }
                    }
                }
            }
        }
    }
}

/* Hjälpdata:
    "data":
    {
        "Ahri":
        {
            "version":"7.17.2",
            "id":"Ahri",
            "key":"103",
            "name":"Ahri",
            "title":"the Nine-Tailed Fox",
            "blurb":"Unlike other foxes that roamed the woods of southern Ionia, Ahri had always felt a strange connection
                     to the magical world around her; a connection that was somehow incomplete. Deep inside, she felt the
                     skin she had been born into was an ill fit for her...",
            "info":
            {
                "attack":3,
                "defense":4,
                "magic":8,
                "difficulty":5
                },
            "image":
            {
                "full":"Ahri.png",
                "sprite":"champion0.png",
                "group":"champion",
                "x":48,"y":0,"w":48,"h":48},
            "tags":
            [
                "Mage","Assassin"
            ],
            "partype":"Mana",
            "stats":
            {
                "hp":514.4,
                "hpperlevel":80,
                "mp":334,
                "mpperlevel":50,
                "movespeed":330,
                "armor":20.88,
                "armorperlevel":3.5,
                "spellblock":30,
                "spellblockperlevel":0.5,
                "attackrange":550,
                "hpregen":6.508,
                "hpregenperlevel":0.6,
                "mpregen":6,
                "mpregenperlevel":0.8,
                "crit":0,
                "critperlevel":0,
                "attackdamage":53.04,
                "attackdamageperlevel":3,
                "attackspeedoffset":-0.065,
                "attackspeedperlevel":2
            }
        }
    }
 */

class Stats {

    String type;
    double value;

    void setType(String t){
        type = t;
    }

    void setValue(int v){
        value = v;
    }

}