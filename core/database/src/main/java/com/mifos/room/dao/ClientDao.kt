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
import com.mifos.core.model.objects.clients.Page
import com.mifos.room.entities.accounts.ClientAccounts
import com.mifos.room.entities.client.Client
import com.mifos.room.entities.group.GroupWithAssociations
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveClient(client: Client)

    @Query("SELECT * FROM Client")
    fun readAllClients(): Flow<Page<Client>>

    @Query("SELECT * FROM GroupTable WHERE id = :groupId")
    fun getGroupAssociateClients(groupId: Int): Flow<GroupWithAssociations>

    @Query("SELECT * FROM Client WHERE id = :clientId LIMIT 1")
    fun getClient(clientId: Int): Flow<Client>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveClientAccounts(
        clientAccounts: ClientAccounts,
        clientId: Int,
    ): Flow<ClientAccounts>

    @Query("SELECT * FROM ClientAccounts WHERE clientId = :clientId")
    fun readClientAccounts(clientId: Int): Flow<ClientAccounts>
}
