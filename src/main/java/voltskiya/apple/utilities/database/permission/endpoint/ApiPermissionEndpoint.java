package voltskiya.apple.utilities.database.permission.endpoint;

import voltskiya.apple.utilities.database.permission.ApiPermissionNode;
import voltskiya.apple.utilities.database.permission.expression.ApiPermissionExpression;
import voltskiya.apple.utilities.database.base.JsonDataUpdateAction;

public interface ApiPermissionEndpoint {

    ApiPermissionEndpoint RESTRICT = create(ApiPermissionNode.ADMIN);
    ApiPermissionEndpoint PUBLIC = create(ApiPermissionNode.EMPTY);

    static ApiPermissionEndpoint create(ApiPermissionNode defaultPermission) {
        return new ApiPermissionEndpointImpl(defaultPermission, defaultPermission, defaultPermission);
    }

    static ApiPermissionEndpointImpl create(ApiPermissionExpression read, ApiPermissionExpression add, ApiPermissionExpression write) {
        return new ApiPermissionEndpointImpl(read, add, write);
    }


    ApiPermissionExpression read();

    ApiPermissionExpression add();

    ApiPermissionExpression write();

    default ApiPermissionExpression perm(JsonDataUpdateAction action) {
        return switch (action) {
            case DELETE, UPDATE -> write();
            case ADD -> add();
            case READ -> read();
        };
    }
}
