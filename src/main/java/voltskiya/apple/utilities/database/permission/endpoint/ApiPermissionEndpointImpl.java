package voltskiya.apple.utilities.database.permission.endpoint;

import voltskiya.apple.utilities.database.permission.expression.ApiPermissionExpression;

public record ApiPermissionEndpointImpl(ApiPermissionExpression read, ApiPermissionExpression add,
                                        ApiPermissionExpression write) implements ApiPermissionEndpoint {

}
