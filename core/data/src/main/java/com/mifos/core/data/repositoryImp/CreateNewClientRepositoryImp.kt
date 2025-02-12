/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.data.repositoryImp

import com.mifos.core.data.repository.CreateNewClientRepository
import com.mifos.core.network.datamanager.DataManagerClient
import com.mifos.core.network.datamanager.DataManagerOffices
import com.mifos.core.network.datamanager.DataManagerStaff
import com.mifos.room.entities.client.Client
import com.mifos.room.entities.client.ClientPayload
import com.mifos.room.entities.organisation.Office
import com.mifos.room.entities.organisation.Staff
import com.mifos.room.entities.templates.clients.ClientsTemplate
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import javax.inject.Inject

/**
 * Created by Aditya Gupta on 10/08/23.
 */
class CreateNewClientRepositoryImp @Inject constructor(
    private val dataManagerClient: DataManagerClient,
    private val dataManagerOffices: DataManagerOffices,
    private val dataManagerStaff: DataManagerStaff,
) : CreateNewClientRepository {

    override fun clientTemplate(): Flow<ClientsTemplate> {
        return dataManagerClient.clientTemplate
    }

    override suspend fun offices(): List<Office> {
        return dataManagerOffices.offices()
    }

    override suspend fun getStaffInOffice(officeId: Int): List<Staff> {
        return dataManagerStaff.getStaffInOffice(officeId)
    }

    override fun createClient(clientPayload: ClientPayload): Flow<Client> {
        return dataManagerClient.createClient(clientPayload)
    }

    override fun uploadClientImage(id: Int, file: MultipartBody.Part?): Flow<ResponseBody> {
        return dataManagerClient.uploadClientImage(id, file)
    }
}
