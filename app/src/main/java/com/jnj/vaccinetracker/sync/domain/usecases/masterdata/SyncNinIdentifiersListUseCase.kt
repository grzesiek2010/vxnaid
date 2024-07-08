package com.jnj.vaccinetracker.sync.domain.usecases.masterdata

import com.jnj.vaccinetracker.common.data.models.Constants
import com.jnj.vaccinetracker.common.data.repositories.MasterDataRepository
import com.jnj.vaccinetracker.common.domain.entities.MasterDataFile
import com.jnj.vaccinetracker.common.domain.entities.NinIdentifiersList
import com.jnj.vaccinetracker.sync.data.network.VaccineTrackerSyncApiDataSource
import com.jnj.vaccinetracker.sync.domain.helpers.SyncLogger
import com.jnj.vaccinetracker.sync.domain.usecases.masterdata.base.SyncMasterDataUseCaseBase
import javax.inject.Inject

class SyncNinIdentifiersListUseCase @Inject constructor(
    private val api: VaccineTrackerSyncApiDataSource,
    override val masterDataRepository: MasterDataRepository,
    override val syncLogger: SyncLogger,
) : SyncMasterDataUseCaseBase<NinIdentifiersList>() {
    override suspend fun getMasterDataRemote(): NinIdentifiersList = api.getIdentifiersList(Constants.NIN_IDENTIFIER_TYPE_NAME)

    override suspend fun storeMasterData(masterData: NinIdentifiersList) {
        masterDataRepository.writeNinIdentifiersList(masterData)
    }

    override val masterDataFile: MasterDataFile
        get() = MasterDataFile.NIN_IDENTIFIERS_LIST
}