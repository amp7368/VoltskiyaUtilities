package voltskiya.apple.utilities.database.permission.expression;

import voltskiya.apple.utilities.database.permission.ApiPermissionNode;

public interface ApiPermissionExpression {

    static ApiPermissionExpression of(ApiPermissionExpression[] components, boolean isOrOperator) {
        return new ApiPermissionExpressionComplex(components, isOrOperator);
    }

    static ApiPermissionExpression ofOr(ApiPermissionExpression[] components, boolean isOrOperator) {
        return new ApiPermissionExpressionComplex(components, true);
    }

    static ApiPermissionExpression ofAnd(ApiPermissionExpression[] components, boolean isOrOperator) {
        return new ApiPermissionExpressionComplex(components, false);
    }

    boolean hasPermission(ApiPermissionNode[] user);
}
