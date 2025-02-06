/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.data.repository

import com.mifos.room.entities.client.Client
import com.mifos.room.entities.client.ClientPayload
import com.mifos.room.entities.organisation.Office
import com.mifos.room.entities.organisation.Staff
import com.mifos.room.entities.templates.clients.ClientsTemplate
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditya Gupta on 10/08/23.
 */
interface CreateNewClientRepository {

    fun clientTemplate(): Flow<ClientsTemplate>

    suspend fun offices(): List<Office>

    suspend fun getStaffInOffice(officeId: Int): List<Staff>

    fun createClient(clientPayload: ClientPayload): Flow<Client>

    fun uploadClientImage(id: Int, file: MultipartBody.Part?): Flow<ResponseBody>
}
