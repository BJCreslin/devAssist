package ru.bjcreslin;

import ru.bjcreslin.domain.Permission;
import ru.bjcreslin.domain.base_clases.ChangeLog;
import ru.bjcreslin.enums.PermissionType;
import ru.bjcreslin.enums.Profile;

public class Solution {

    public static void main(String[] args) {
        Profile[] profiles = new Profile[]{
                Profile.AUDITOR,
                Profile.BUSINESS_ANALYST_GIBR,
                Profile.BUSINESS_ADMIN_CO,
                Profile.CURATOR_STBN,
                Profile.CURATOR_GIBR,
                Profile.CURATOR_DFS,
                Profile.CURATOR_DNSZKO,
                Profile.MANAGER_CURATOR_OBN,
                Profile.COORDINATOR_STBN,
                Profile.COORDINATOR_DNSZKO,
                Profile.ANALYST_STBN,
                Profile.ANALYST_DNSZKO,
                Profile.METHODOLOGIST_STBN,
                Profile.METHODOLOGIST_DNSZKO
        };

        Permission[] permissions = new Permission[]{
                new Permission(
                        "sar-conclusions#collateral-conclusions-tab",
                        PermissionType.ACTION,
                        "GET_PERMISSIONS_SAR_CONCLUSION",
                        null,
                        "Боковое меню «Заключения по залоговым объектам»",
                        profiles,
                        null
                ),

                new Permission(
                        "sar-conclusions#collateral-conclusions-tab#collateral-conclusions-table",
                        PermissionType.ACTION,
                        "GET_PERMISSIONS_SAR_CONCLUSION",
                        null,
                        "Таблица «Заключения по залоговым объектам»",

                        profiles,
                        null
                ),

                new Permission(
                        "sar-conclusions#collateral-conclusions-tab#view",
                        PermissionType.ACTION,
                        "GET_PERMISSIONS_SAR_CONCLUSION",
                        "GET_PERMISSIONS_SAR_CONCLUSION_CONCLUSION_TAB_VIEW",
                        "Боковое меню «Заключения по залоговым объектам»",
                        profiles,
                        "Право на просмотр информации:&#13;&#10; 1) Таблица с заключениями по залогам &#13;&#10;" +
                        "2) Выгрузка в Excel"
                )
        };

        ChangeLog changeLog = new ChangeLog(permissions);
        changeLog.create();
        System.out.println("Change log has been created.");
    }
}
