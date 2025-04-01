package voltskiya.apple.utilities.database.permission;

import java.util.List;
import voltskiya.apple.utilities.database.permission.expression.ApiPermissionExpression;

public enum ApiPermissionNode implements ApiPermissionExpression {
    EMPTY,
    GAMEMASTER,
    ADMIN;

    @Override
    public boolean hasPermission(ApiPermissionNode[] user) {
        return List.of(user).contains(this);
    }

    @Override
    public String toString() {
        return name();
    }
}
