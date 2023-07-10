import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * JsonFileReader class to read json file
 */
public class JsonFileReader extends AbstractFileReader implements IJsonFileReader {

    private static final String[] redundantKeys = {"grammarTitle", "grammarDesc"};

    /**
     * constructor for JsonFileReader
     *
     * @param fileName name of the file
     */
    public JsonFileReader(String fileName) {
        super(fileName);
    }

    /**
     * constructor for JsonFileReader
     */
    public JsonFileReader() {
        super(null);
    }

    /**
     * Read the JSON file and return the parsed content
     * the format of the parsed content is in the test class JsonFileReaderTest
     *
     * @return HashMap contains parsed content
     */
    public JsonGrammar readJsonFile() {
        JsonGrammar jsonGrammar = null;
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader(getFileName());
            // convert JSON file to map
            jsonGrammar = new JsonGrammar(gson.fromJson(reader, HashMap.class));
            removeRedundantData(jsonGrammar.getGrammar());
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + getFileName());
        }
        return jsonGrammar;
    }

    /**
     * Remove redundant data(key, value) from the json file
     *
     * @param jsonMap json file content
     */
    public void removeRedundantData(HashMap<String, ArrayList<String>> jsonMap) {
        for (String key : redundantKeys) {
            if (jsonMap.containsKey(key)) {
                jsonMap.remove(key);
            }
        }
    }

    /**
     * override the toString method
     *
     * @return String
     */
    @Override
    public String toString() {
        return "JsonFileReader{" +
                "fileName='" + getFileName() + '\'' +
                '}';
    }

    /**
     * override the equals method
     *
     * @param o object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonFileReader that = (JsonFileReader) o;
        return getFileName().equals(that.getFileName());
    }

    /**
     * override the hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFileName());
    }
}
