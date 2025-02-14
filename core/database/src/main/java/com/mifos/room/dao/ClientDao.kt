/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.mifos.core.model.objects.clients.Page
import com.mifos.room.entities.accounts.ClientAccounts
import com.mifos.room.entities.accounts.loans.LoanAccount
import com.mifos.room.entities.accounts.savings.SavingsAccount
import com.mifos.room.entities.client.Client
import com.mifos.room.entities.client.ClientPayload
import com.mifos.room.entities.group.GroupWithAssociations
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveClient(client: Client)

    @Query("SELECT * FROM Client")
    fun readAllClients(): Flow<Page<Client>>

    @Query("SELECT * FROM Client WHERE id = :groupId")
    fun getGroupAssociateClients(groupId: Int): Flow<GroupWithAssociations>

    @Query("SELECT * FROM Client WHERE id = :clientId LIMIT 1")
    fun getClient(clientId: Int): Flow<Client>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveClientAccounts(
        clientAccounts: ClientAccounts,
        clientId: Int,
    ): Flow<ClientAccounts>

    @Query("SELECT * FROM LoanAccount WHERE clientId = :clientId")
    fun getLoanAccounts(clientId: Long): Flow<List<LoanAccount>>

    @Query("SELECT * FROM SavingsAccount WHERE clientId = :clientId")
    fun getSavingsAccounts(clientId: Long): Flow<List<SavingsAccount>>

    // TODO add readClientAccounts, use combine

    // TODO saveClientTemplate

    // TODO readClientTemplate

    // TODO saveClientPayloadToDB

    // TODO readClientPayloadFromDB

    // TODO deleteClientPayload

    @Query("DELETE FROM ClientPayload WHERE id = :id")
    fun deleteClientPayloadById(id: Int)

    @Query("DELETE FROM DataTablePayload WHERE clientCreationTime = :clientCreationTime")
    fun deleteDataTablePayloadByTime(clientCreationTime: Long)

    @Transaction
    fun deleteClientPayload(id: Int, clientCreationTime: Long): Flow<List<ClientPayload>>

    @Update
    suspend fun updateDatabaseClientPayload(clientPayload: ClientPayload): Flow<ClientPayload>
}
