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

public class ChangeLog {

    private final String keyText;

    private final String author;

    private final String storyNumber;

    private final String tabName;

    private final Permission[] permissions;

    public ChangeLog(String keyText, String author, String storyNumber, String tabName, Permission[] permissions) {
        this.keyText = keyText;
        this.author = author;
        this.storyNumber = storyNumber;
        this.tabName = tabName;
        this.permissions = permissions;
    }

    public void create() {
        SecureElemPermissionId permissionId = new SecureElemPermissionId(keyText);
        String fileContent = getFileContent(permissions, permissionId);
        saveFile(permissionId, fileContent);
    }

    private String getFileContent(Permission[] permissions, SecureElemPermissionId permissionId) {

        return String.format(
                getTempleFileContent(),
                permissionId.toString(),
                author, storyNumber, tabName,
                new SecureElemMigrationContent(permissions),
                new SecureElemMigrationContentRollback(permissions),
                new ProfileSecureElemPermissionId(keyText),
                author, storyNumber, tabName,
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
            return String.join("\n", fileLines);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
