package voltskiya.apple.utilities.database.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import voltskiya.apple.utilities.database.base.JsonApiDataQuery;
import voltskiya.apple.utilities.database.base.JsonDataName;
import voltskiya.apple.utilities.database.base.JsonDataUpdateAction;
import voltskiya.apple.utilities.database.exception.JsonDataException;
import voltskiya.apple.utilities.database.permission.ApiPermissionNode;
import voltskiya.apple.utilities.database.permission.expression.ApiPermissionExpression;

public class JsonApiDataStorage {

    private static final Map<String, JsonApiDataQuery<?>> queryData = new HashMap<>();
    private static final Map<String, JsonApiDataFolder> folders = new HashMap<>();

    public static void addQuery(JsonApiDataQuery<?> data) {
        queryData.put(data.getPath(), data);
        String[] fullPath = data.getPath().split(JsonDataName.SEPARATOR);
        StringBuilder currentPath = new StringBuilder();
        for (String segment : fullPath) {
            JsonApiDataFolder folder;
            synchronized (folders) {
                folder = folders.computeIfAbsent(segment, JsonApiDataFolder::new);
            }
            currentPath.append(segment);
            folder.add(currentPath.toString());
            currentPath.append(JsonDataName.SEPARATOR);
        }
    }

    public static void post(JsonDataUpdateRequest request, ApiPermissionNode[] user) throws JsonDataException {
        JsonApiDataQuery<?> data = findData(request);
        ApiPermissionExpression permission = data.permission().perm(request.action);
        if (!permission.hasPermission(user))
            throw new JsonDataException("Permission Denied: missing some of '%s'".formatted(data.permission()), 403);
        data.post(request);
    }

    private static JsonApiDataQuery<?> findData(JsonDataRequest request) throws JsonDataException {
        JsonApiDataQuery<?> data = queryData.get(request.filePath);
        if (data == null) throw new JsonDataException("Could not find '%s'".formatted(request.filePath), 404);
        return data;
    }

    public static Object get(JsonDataGetRequest request, ApiPermissionNode[] user) throws JsonDataException {
        JsonApiDataQuery<?> data = findData(request);
        ApiPermissionExpression permission = data.permission().perm(JsonDataUpdateAction.READ);
        if (!permission.hasPermission(user)) throw new JsonDataException("Permission Denied: missing read", 403);
        return data.getInst();
    }

    public static Set<String> index(String... path) {
        String pathString = String.join(JsonDataName.SEPARATOR, path);
        JsonApiDataFolder folder;
        synchronized (folders) {
            folder = folders.get(pathString);
        }
        if (folder == null) return Collections.emptySet();
        return folder.getChildren();
    }
}
