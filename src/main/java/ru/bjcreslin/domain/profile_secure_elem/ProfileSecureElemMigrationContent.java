package ru.bjcreslin.domain.profile_secure_elem;

import ru.bjcreslin.domain.Permission;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ProfileSecureElemMigrationContent {

    private final Permission[] permissions;

    public ProfileSecureElemMigrationContent(Permission[] permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return Arrays.stream(permissions).toList().stream()
                .map(ProfileSecureElemMigration::new)
                .map(ProfileSecureElemMigration::toString)
                .collect(Collectors.joining());
    }
}
