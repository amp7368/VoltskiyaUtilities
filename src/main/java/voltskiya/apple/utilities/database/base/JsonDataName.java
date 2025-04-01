package voltskiya.apple.utilities.database.base;

public record JsonDataName(String[] name) {

    public static final String SEPARATOR = "/";

    public static JsonDataName create(String plugin, String category, String... name) {
        String[] data = new String[name.length + 2];
        data[0] = plugin;
        data[1] = category;
        System.arraycopy(name, 0, data, 2, name.length);
        return new JsonDataName(data);
    }

    public String getPath() {
        return String.join(SEPARATOR, name);
    }
}
