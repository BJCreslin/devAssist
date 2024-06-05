package ru.bjcreslin.domain.base_clases;

import ru.bjcreslin.domain.Permission;
import ru.bjcreslin.domain.profile_secure_elem.ProfileSecureElemMigrationContent;
import ru.bjcreslin.domain.profile_secure_elem.ProfileSecureElemMigrationContentRollback;
import ru.bjcreslin.domain.profile_secure_elem.ProfileSecureElemPermissionId;
import ru.bjcreslin.domain.secure_elem.SecureElemMigrationContent;
import ru.bjcreslin.domain.secure_elem.SecureElemMigrationContentRollback;
import ru.bjcreslin.domain.secure_elem.SecureElemPermissionId;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ChangeLog {

    private static final String KEY_TEXT = "collateral_conclusion";

    private static final String AUTHOR = "KreslinVYu";

    private static final String STORY_NUMBER = "DOSIE-11982 (12083)";

    private static final String TAB_NAME = "Заключения САР по залоговым объектам";

    private final Permission[] permissions;

    public ChangeLog(Permission[] permissions) {
        this.permissions = permissions;
    }

    public void create() {
        SecureElemPermissionId permissionId = new SecureElemPermissionId(KEY_TEXT);
        String fileContent = getFileContent(permissions, permissionId);
        saveFile(permissionId, fileContent);
    }

    private static String getFileContent(Permission[] permissions, SecureElemPermissionId permissionId) {

        return String.format(
                getTempleFileContent(),
                permissionId.toString(),
                AUTHOR, STORY_NUMBER, TAB_NAME,
                new SecureElemMigrationContent(permissions),
                new SecureElemMigrationContentRollback(permissions),
                new ProfileSecureElemPermissionId(KEY_TEXT),
                AUTHOR, STORY_NUMBER, TAB_NAME,
                new ProfileSecureElemMigrationContent(permissions),
                new ProfileSecureElemMigrationContentRollback(permissions)
        );
    }

    private static void saveFile(SecureElemPermissionId idText, String newFileContent) {
        Path filePath = Path.of(idText.toString() + ".xml");

        try {
            Files.writeString(filePath, newFileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getTempleFileContent() {
        URL resource = ChangeLog.class.getClassLoader().getResource("template.xml");
        if (resource == null) {
            throw new RuntimeException("File not found!");
        }
        try {
            Path path = Paths.get(resource.toURI());
            List<String> fileLines = Files.readAllLines(path);
            return fileLines.stream().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
