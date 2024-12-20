package com.mifos.core.data.repository

import com.mifos.core.objects.accounts.ClientAccounts
import com.mifos.core.objects.client.Client
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by Aditya Gupta on 06/08/23.
 */
interface ClientDetailsRepository {

    fun uploadClientImage(id: Int, file: MultipartBody.Part?): Observable<ResponseBody>

    fun deleteClientImage(clientId: Int): Observable<ResponseBody>

    suspend fun getClientAccounts(clientId: Int): ClientAccounts

    suspend fun getClient(clientId: Int): Client

}