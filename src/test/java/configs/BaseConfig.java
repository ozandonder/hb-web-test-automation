package configs;

public class BaseConfig {
    public static final int SHORT_WAIT_TIMEOUT_SECONDS = 5;
    public static final int MODERATE_WAIT_TIMEOUT_SECONDS = 15;
    public static final int GLOBAL_WAIT_TIMEOUT_SECONDS = 30;
    public static final int LONG_WAIT_TIMEOUT_SECONDS = 60;

    String browser = System.getProperty("browser");
    String env = System.getProperty("projectEnv");
    String headless = System.getProperty("headless");
    String locale = System.getProperty("lang");

    public String baseUrl() {
        env = (env != null) ? env : "prod";
        String url = null;
        switch (env) {
            case "test" -> url = "";
            case "prod" -> url = "https://www.hepsiburada.com";
        }
        return url;
    }

    public String getBrowser() {
        return browser != null ? browser : "chrome";
    }

    public String getLocale() { return locale != null ? locale : "tr"; }

    public Boolean getHeadless() {
        return Boolean.parseBoolean(headless);
    }
}
