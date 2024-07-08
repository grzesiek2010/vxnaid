package com.jnj.vaccinetracker.common.domain.usecases.masterdata

import com.jnj.vaccinetracker.common.data.datasources.MasterDataMemoryDataSource
import com.jnj.vaccinetracker.common.data.models.Constants
import com.jnj.vaccinetracker.common.data.repositories.MasterDataRepository
import com.jnj.vaccinetracker.common.domain.entities.MasterDataFile
import com.jnj.vaccinetracker.common.domain.entities.NinIdentifiersList
import com.jnj.vaccinetracker.common.domain.usecases.masterdata.base.GetMasterDataUseCaseBase
import com.jnj.vaccinetracker.common.helpers.AppCoroutineDispatchers
import com.jnj.vaccinetracker.sync.data.network.VaccineTrackerSyncApiDataSource
import com.jnj.vaccinetracker.sync.domain.helpers.SyncLogger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNinIdentifiersListUseCase @Inject constructor(
    private val vaccineTrackerApiDataSource: VaccineTrackerSyncApiDataSource,
    override val masterDataRepository: MasterDataRepository,
    override val dispatchers: AppCoroutineDispatchers,
    override val syncLogger: SyncLogger,
    private val masterDataMemoryDataSource: MasterDataMemoryDataSource,
) : GetMasterDataUseCaseBase<NinIdentifiersList, NinIdentifiersList>() {

    init {
        initState()
    }

    override fun getMemoryCache(): NinIdentifiersList? {
        return masterDataMemoryDataSource.getNinIdentifiersList()
    }

    override fun setMemoryCache(memoryCache: NinIdentifiersList?) {
        masterDataMemoryDataSource.setNinIdentifiersList(memoryCache)
    }

    override val masterDataFile: MasterDataFile
        get() = MasterDataFile.NIN_IDENTIFIERS_LIST

    override suspend fun NinIdentifiersList.toDomain(): NinIdentifiersList {
        return this
    }

    override suspend fun getMasterDataPersistedCache() = masterDataRepository.readNinIdentifiersList()

    override suspend fun getMasterDataRemote() = vaccineTrackerApiDataSource.getIdentifiersList(Constants.NIN_IDENTIFIER_TYPE_NAME)
}