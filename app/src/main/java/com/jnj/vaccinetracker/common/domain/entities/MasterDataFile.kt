package com.jnj.vaccinetracker.common.domain.entities

enum class MasterDataFile {
    CONFIGURATION, SITES, LOCALIZATION, ADDRESS_HIERARCHY, VACCINE_SCHEDULE, SUBSTANCES_CONFIG,
    SUBSTANCES_GROUP_CONFIG, NIN_IDENTIFIERS_LIST;

    val fileName
        get() = when (this) {
            CONFIGURATION -> "configuration.json"
            SITES -> "sites.json"
            LOCALIZATION -> "localization.json"
            ADDRESS_HIERARCHY -> "address_hierarchy.json"
            VACCINE_SCHEDULE -> "vaccine_schedule.json"
            SUBSTANCES_CONFIG -> "substances_config.json"
            SUBSTANCES_GROUP_CONFIG -> "substances_group_config.json"
            NIN_IDENTIFIERS_LIST -> "nin_identifiers_list.json"
        }

    val isEncrypted: Boolean
        get() = when (this) {
            CONFIGURATION -> true
            SITES -> true
            LOCALIZATION -> true
            ADDRESS_HIERARCHY -> true
            VACCINE_SCHEDULE -> true
            SUBSTANCES_CONFIG -> true
            SUBSTANCES_GROUP_CONFIG -> true
            NIN_IDENTIFIERS_LIST -> true
        }

    val syncName
        get() = when (this) {
            CONFIGURATION -> "config"
            SITES -> "locations"
            LOCALIZATION -> "localization"
            ADDRESS_HIERARCHY -> "addressHierarchy"
            VACCINE_SCHEDULE -> "vaccineSchedule"
            SUBSTANCES_CONFIG -> "substancesConfig"
            SUBSTANCES_GROUP_CONFIG -> "substancesGroupConfig"
            NIN_IDENTIFIERS_LIST -> "ninIdentifiersList"
        }
}