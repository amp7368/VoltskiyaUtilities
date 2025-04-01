package voltskiya.apple.utilities.database.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JsonApiDataFolder {

    private final String path;
    private final List<String> children = new ArrayList<>();

    public JsonApiDataFolder(String path) {
        this.path = path;
    }

    public void add(String subPath) {
        this.children.add(subPath);
    }

    public Set<String> getChildren() {
        return Set.copyOf(children);
    }
}
