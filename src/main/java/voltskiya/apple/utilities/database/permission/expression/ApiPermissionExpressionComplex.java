package voltskiya.apple.utilities.database.permission.expression;

import java.util.Arrays;
import java.util.stream.Collectors;
import voltskiya.apple.utilities.database.permission.ApiPermissionNode;

public class ApiPermissionExpressionComplex implements ApiPermissionExpression {

    private final ApiPermissionExpression[] components;
    private final boolean isOrOperator;

    public ApiPermissionExpressionComplex(ApiPermissionExpression[] components, boolean isOrOperator) {
        this.components = components;
        this.isOrOperator = isOrOperator;
    }

    @Override
    public boolean hasPermission(ApiPermissionNode[] user) {
        if (isOrOperator) {
            return Arrays.stream(components).anyMatch(c -> c.hasPermission(user));
        } else {
            return Arrays.stream(components).allMatch(c -> c.hasPermission(user));
        }
    }

    @Override
    public String toString() {
        String delimiter = isOrOperator ? " or " : " and ";
        return Arrays.stream(components).map(Object::toString).collect(Collectors.joining(delimiter));
    }
}
