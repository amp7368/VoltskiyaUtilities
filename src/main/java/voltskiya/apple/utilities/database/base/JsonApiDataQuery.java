package voltskiya.apple.utilities.database.base;

import com.google.gson.JsonElement;
import org.apache.commons.lang.NotImplementedException;
import voltskiya.apple.utilities.database.exception.JsonDataException;
import voltskiya.apple.utilities.database.permission.endpoint.ApiPermissionEndpoint;
import voltskiya.apple.utilities.database.request.JsonApiDataStorage;
import voltskiya.apple.utilities.database.request.JsonDataUpdateRequest;

public class JsonApiDataQuery<T> {

    private final JsonDataName name;
    private T inst;

    public JsonApiDataQuery(JsonDataName name, T inst) {
        this.name = name;
        this.inst = inst;
    }

    public final JsonApiDataQuery<T> register() {
        JsonApiDataStorage.addQuery(this);
        return this;
    }

    public final Object getInst() {
        return inst;
    }

    public final JsonApiDataQuery<T> setInst(T inst) {
        this.inst = inst;
        return this;
    }

    public final String getPath() {
        return this.name.getPath();
    }

    public ApiPermissionEndpoint permission() {
        return ApiPermissionEndpoint.RESTRICT;
    }

    public final void post(JsonDataUpdateRequest request) throws JsonDataException {
        if (request.action == JsonDataUpdateAction.UPDATE) {
            if (request.valuePath.equals("")) {
                this.setInst(this.update(request.value));
            }
        }
    }

    public T update(JsonElement inst) {
        throw new NotImplementedException();
    }
}
