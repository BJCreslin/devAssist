package ru.bjcreslin.domain.secure_elem;

import ru.bjcreslin.domain.base_clases.PermissionIdAbstract;

public class SecureElemPermissionId extends PermissionIdAbstract {

    private final String textPart;

    private static final String ID_TEMPLATE = "%s_add_permissions_to_%s";

    public SecureElemPermissionId(String textPart) {
        this.textPart = textPart;
    }

    @Override
    public String toString() {
        return super.create(textPart, ID_TEMPLATE);
    }
}
