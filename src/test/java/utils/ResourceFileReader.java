package utils;

import configs.BaseConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ResourceFileReader {
    private BaseConfig baseConfig;
    private JSONParser parser;
    private final Map<String, JSONObject> loadedFiles = new ConcurrentHashMap<>();

    private BaseConfig getBaseConfig() {
        return baseConfig == null ? new BaseConfig() : baseConfig;
    }

    private JSONParser getParser() {
        return parser == null ? new JSONParser() : parser;
    }

    private JSONObject loadJsonFile(String fileName) {
        String locale = getBaseConfig().getLocale();
        if (!loadedFiles.containsKey(fileName)) {
            String filePath = "src/test/java/resources/" + fileName;
            try (FileReader reader = new FileReader(filePath)) {
                JSONObject jsonObject = (JSONObject) getParser().parse(reader);
                loadedFiles.put(fileName, (JSONObject) jsonObject.get(locale));
            } catch (IOException | ParseException e) {
                throw new RuntimeException("Error loading file: " + filePath, e);
            }
        }
        return loadedFiles.get(fileName);
    }

    /**
     * Get content data from contents.json file.
     *
     * @param pageName Name of the page
     * @param key      Key to retrieve the content
     * @return Content data as String
     */
    public String getContentData(String pageName, String key) {
        JSONObject jsonObject = loadJsonFile("contents.json");
        JSONObject pageObject = (JSONObject) jsonObject.get(pageName);
        return (String) pageObject.get(key);
    }

    /**
     * Get resource data from resources.json file.
     *
     * @param pageName Name of the page
     * @param key      Key to retrieve the resource
     * @return Resource data as String
     */
    public String getResourceData(String pageName, String key) {
        JSONObject jsonObject = loadJsonFile("resources.json");
        JSONObject pageObject = (JSONObject) jsonObject.get(pageName);
        return (String) pageObject.get(key);
    }

    /**
     * Get validation data from validations.json file.
     *
     * @param pageName Name of the page
     * @param key      Key to retrieve the validation
     * @return Validation data as String
     */
    public String getValidationData(String pageName, String key) {
        JSONObject jsonObject = loadJsonFile("validations.json");
        JSONObject pageObject = (JSONObject) jsonObject.get(pageName);
        return (String) pageObject.get(key);
    }
}