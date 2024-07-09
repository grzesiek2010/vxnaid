package com.jnj.vaccinetracker.sync.domain.usecases.masterdata

import com.jnj.vaccinetracker.common.data.repositories.MasterDataRepository
import com.jnj.vaccinetracker.common.domain.entities.MasterDataFile
import com.jnj.vaccinetracker.common.domain.entities.OtherSubstancesConfig
import com.jnj.vaccinetracker.sync.data.network.VaccineTrackerSyncApiDataSource
import com.jnj.vaccinetracker.sync.domain.helpers.SyncLogger
import com.jnj.vaccinetracker.sync.domain.usecases.masterdata.base.SyncMasterDataUseCaseBase
import javax.inject.Inject

class SyncOtherSubstancesConfigUseCase @Inject constructor(
    private val api: VaccineTrackerSyncApiDataSource,
    override val masterDataRepository: MasterDataRepository,
    override val syncLogger: SyncLogger,
) : SyncMasterDataUseCaseBase<OtherSubstancesConfig>() {
    override suspend fun getMasterDataRemote() = api.getOtherSubstancesConfig()

    override suspend fun storeMasterData(masterData: OtherSubstancesConfig) {
        masterDataRepository.writeOtherSubstancesConfig(masterData)
    }

    override val masterDataFile: MasterDataFile
        get() = MasterDataFile.OTHER_SUBSTANCES_CONFIG
}