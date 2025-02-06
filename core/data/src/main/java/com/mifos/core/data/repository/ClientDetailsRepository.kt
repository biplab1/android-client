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
import com.mifos.room.entities.accounts.ClientAccounts
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditya Gupta on 06/08/23.
 */
interface ClientDetailsRepository {

    fun uploadClientImage(id: Int, file: MultipartBody.Part?): Flow<ResponseBody>

    fun deleteClientImage(clientId: Int): Flow<ResponseBody>

    suspend fun getClientAccounts(clientId: Int): ClientAccounts

    suspend fun getClient(clientId: Int): Client
}
