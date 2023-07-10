import java.util.ArrayList;
import java.util.HashMap;

/**
 * IJsonFileReader interface to read json file
 */
public interface IJsonFileReader {

    /**
     * Read the json file and return the parsed content
     *
     * @return HashMap contains parsed content
     */
    JsonGrammar readJsonFile();

    /**
     * Remove redundant data(key, value) from the json file
     *
     * @param jsonMap json file content
     */
    void removeRedundantData(HashMap<String, ArrayList<String>> jsonMap);
}
