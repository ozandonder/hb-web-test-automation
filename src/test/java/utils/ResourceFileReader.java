package utils;

import configs.BaseConfig;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ResourceFileReader {
    private JSONObject jsonObject;
    private final BaseConfig baseConfig = new BaseConfig();
    private final JSONParser parser = new JSONParser();
    private final String resourcePackagePath = "src/test/java/resources/";

    /**
     * Get content data from contents.json file.
     * @param pageName Name of the page
     * @param key Key to retrieve the content
     * @return Content data as String
     */
    public String getContentData(String pageName, String key) {
        String filePath = resourcePackagePath + "contents.json";
        try {
            Object obj = parser.parse(new FileReader(filePath));
            jsonObject = (JSONObject) ((JSONObject) obj).get(baseConfig.getLocale());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject tempObj = (JSONObject) jsonObject.get(pageName);
        return (String) tempObj.get(key);
    }

    /**
     * Get resource data from resources.json file.
     * @param pageName Name of the page
     * @param key Key to retrieve the resource
     * @return Resource data as String
     */
    public String getResourceData(String pageName, String key) {
        String filePath = resourcePackagePath + "resources.json";
        try {
            Object obj = parser.parse(new FileReader(filePath));
            jsonObject = (JSONObject) ((JSONObject) obj).get(baseConfig.getLocale());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject tempObj = (JSONObject) jsonObject.get(pageName);
        return (String) tempObj.get(key);
    }

    /**
     * Get validation data from validations.json file.
     * @param pageName Name of the page
     * @param key Key to retrieve the validation
     * @return Validation data as String
     */
    public String getValidationData(String pageName, String key) {
        String filePath = resourcePackagePath + "validations.json";
        try {
            Object obj = parser.parse(new FileReader(filePath));
            jsonObject = (JSONObject) ((JSONObject) obj).get(baseConfig.getLocale());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject tempObj = (JSONObject) jsonObject.get(pageName);
        return (String) tempObj.get(key);
    }
}