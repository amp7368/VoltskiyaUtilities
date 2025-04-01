package voltskiya.apple.utilities.database.request;

import com.google.gson.JsonElement;
import voltskiya.apple.utilities.database.base.JsonDataUpdateAction;

public class JsonDataUpdateRequest extends JsonDataRequest {

    public JsonDataUpdateAction action;
    public JsonElement value;

}
