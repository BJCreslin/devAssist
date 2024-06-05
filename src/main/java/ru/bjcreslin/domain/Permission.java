package ru.bjcreslin.domain;

import ru.bjcreslin.enums.PermissionType;
import ru.bjcreslin.enums.Profile;

public class Permission {

    private static final String PREFIX = "urn:%s:attr:01:subject:";

    private final String parent;

    private final PermissionType type;

    private final String name;

    private final String relKey;

    private final String key;

    private final String abacPermPresAttrCode;

    private final String abacPermPresGroupAction;

    private final String abacPermPresUserAction;

    private final Profile[] profiles;

    private final boolean koPermission;

    private final String description;

    public Permission(String key,
                      PermissionType type,
                      String abacPermPresGroupAction,
                      String abacPermPresUserAction,
                      String name,
                      Profile[] profiles,
                      String description) {
        String[] parts = key.split("#");
        this.relKey = parts[parts.length - 1];
        this.key = key;
        this.parent = getParent(key);
        this.abacPermPresAttrCode = PREFIX + key.replace("#", "_");
        this.type = type;
        this.abacPermPresGroupAction = abacPermPresGroupAction;
        this.name = name;
        this.profiles = profiles;
        this.abacPermPresUserAction = abacPermPresUserAction;
        koPermission = abacPermPresUserAction != null;
        this.description = description;
    }

    private String getParent(String key) {
        String parentLast = key.replace(relKey, "");
        StringBuilder sb = new StringBuilder(parentLast);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String getParent() {
        return parent;
    }

    public PermissionType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getRelKey() {
        return relKey;
    }

    public String getKey() {
        return key;
    }

    public String getAbacPermPresAttrCode() {
        return abacPermPresAttrCode;
    }

    public String getAbacPermPresGroupAction() {
        return abacPermPresGroupAction;
    }

    public Profile[] getProfiles() {
        return profiles;
    }

    public String getAbacPermPresUserAction() {
        return abacPermPresUserAction;
    }

    public boolean isKoPermission() {
        return koPermission;
    }

    public String getDescription() {
        return description;
    }
}
