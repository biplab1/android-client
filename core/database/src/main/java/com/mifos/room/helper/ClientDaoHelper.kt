/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.room.helper

import com.mifos.core.common.utils.Page
import com.mifos.room.dao.ClientDao
import com.mifos.room.entities.accounts.ClientAccounts
import com.mifos.room.entities.accounts.loans.LoanAccount
import com.mifos.room.entities.accounts.savings.SavingsAccount
import com.mifos.room.entities.client.Client
import com.mifos.room.entities.client.ClientDate
import com.mifos.room.entities.client.ClientPayload
import com.mifos.room.entities.group.GroupWithAssociations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This DatabaseHelper Managing all Database logic and staff (Saving, Update, Delete).
 * Whenever DataManager send response to save or request to read from Database then this class
 * save the response or read the all values from database and return as accordingly.
 */
@Singleton
class ClientDaoHelper @Inject constructor(
    private val clientDao: ClientDao,
) {
    private val json: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    fun parseJsonToMap(jsonString: String): Map<String, Any> {
        val jsonElement = json.parseToJsonElement(jsonString)
        return json.decodeFromJsonElement(
            MapSerializer(
                String.serializer(),
                valueSerializer = TODO(),
            ),
            jsonElement,
        )
    }

    /**
     * This Method save the single Client in Database with ClientId as Primary Id
     *
     * @param client Client
     * @return saved Client
     */
    fun saveClient(client: Client): Flow<Client> =
        flow {
            val clientDate = client.activationDate.getOrNull(0)?.let { year ->
                client.activationDate.getOrNull(1)?.let { month ->
                    client.activationDate.getOrNull(2)?.let { day ->
                        ClientDate(client.id.toLong(), 0, year, month, day)
                    }
                }
            }
            val updatedClient = client.copy(clientDate = clientDate)
            clientDao.insertClient(updatedClient)
            emit(updatedClient)
        }

    /**
     * Reading All Clients from table of Client and return the ClientList
     *
     * @return List Of Client
     */

    fun readAllClients(): Flow<Page<Client>> {
        return clientDao.getAllClients().map { clients ->
            Page<Client>().apply {
                pageItems = clients
            }
        }
    }

    /**
     * Retrieves all clients associated with a specific group and returns a `GroupWithAssociations` object.
     *
     * @param groupId The ID of the group for which the associated clients should be retrieved.
     * @return A `Flow` of `GroupWithAssociations`, which contains a list of `clientMembers` associated with the
     *         specified group.
     */

    fun getGroupAssociateClients(groupId: Int): Flow<GroupWithAssociations> {
        return clientDao.getClientsByGroupId(groupId).map { clients ->
            GroupWithAssociations().copy(
                clientMembers = clients,
            )
        }
    }

    /**
     * This Method select query with clientId, In return the Client Details will be come.
     *
     * @param clientId of the client
     * @return A 'Flow' of 'Client'
     */
    fun getClient(clientId: Int): Flow<Client> {
        return clientDao.getClientByClientId(clientId).map { client ->
            client.copy(
                activationDate = listOf(
                    client.clientDate?.day,
                    client.clientDate?.month,
                    client.clientDate?.year,
                ),
            )
        }
    }

    /**
     * This Method  write the ClientAccount in tho DB. According to Schema Defined in Model
     *
     * @param clientAccounts Model of List of LoanAccount and SavingAccount
     * @param clientId       Client Id
     * @return null
     */
    suspend fun saveClientAccounts(
        clientAccounts: ClientAccounts,
        clientId: Int,
    ) {
        val loanAccounts = clientAccounts.loanAccounts
        val savingsAccounts = clientAccounts.savingsAccounts

        for (loanAccount: LoanAccount in loanAccounts) {
            val updatedLoanAccount = loanAccount.copy(clientId = clientId.toLong())
            clientDao.insertLoanAccount(updatedLoanAccount)
        }

        for (savingsAccount: SavingsAccount in savingsAccounts) {
            val updatedSavingsAccount = savingsAccount.copy(clientId = clientId.toLong())
            clientDao.insertSavingsAccount(updatedSavingsAccount)
        }
    }

    /**
     * This Method Read the Table of LoanAccount and SavingAccount and return the List of
     * LoanAccount and SavingAccount according to clientId
     *
     * @param clientId Client Id
     * @return Return the ClientAccounts according to client Id
     */
    // TODO resolve if first() can be used or not
    fun readClientAccounts(clientId: Int): Flow<ClientAccounts> {
        return flow {
            val loanAccounts = clientDao.getLoanAccountsByClientId(clientId.toLong()).first()
            val savingsAccounts = clientDao.getSavingsAccountsByClientId(clientId.toLong()).first()

            val clientAccounts = ClientAccounts()
            clientAccounts.loanAccounts = loanAccounts
            clientAccounts.savingsAccounts = savingsAccounts
            emit(clientAccounts)
        }
    }

    /**
     * Saving ClientTemplate into Database ClientTemplate_Table
     *
     * @param clientsTemplate fetched from Server
     * @return void
     */
    /*
    fun saveClientTemplate(clientsTemplate: ClientsTemplate): Flow<ClientsTemplate> {
        return flow {
            clientDao.insertClientsTemplate(clientsTemplate)

            clientDao.insertOfficeOptions(clientsTemplate.officeOptions)
            clientDao.insertStaffOptions(clientsTemplate.staffOptions)
            clientDao.insertSavingProductOptions(clientsTemplate.savingProductOptions)

            clientDao.genderOptions.forEach{ it.optionType}
        }

    }
     */

    /**
     * Reading ClientTemplate from Database ClientTemplate_Table
     *
     * @return ClientTemplate
     */
    // TODO readClientTemplate

    /**
     * Saving ClientPayload into Database ClientPayload_Table
     *
     * @param clientPayload created in offline mode
     * @return Client
     */
    /*
    suspend fun saveClientPayloadToDB(clientPayload: ClientPayload): Client {
        val currentTime = System.currentTimeMillis()
        val updatedClientPayload = clientPayload.copy(clientCreationTime = currentTime)

        val updatedDataTablePayloads = updatedClientPayload.datatables?.map { dataTablePayload ->
            dataTablePayload.copy(
                clientCreationTime = currentTime,
                dataTableString = json.encodeToString<String?>(dataTablePayload.data)
            )
        } ?: emptyList()

        val clientId = clientDao.insertClientPayload(updatedClientPayload)
        clientDao.insertDataTablePayloads(updatedDataTablePayloads)

        val savedClient = clientDao.getClientByClientId(clientId)
        emit(savedClient)
    }
     */

    /**
     * Reading All Entries in the ClientPayload_Table
     *
     * @return List<ClientPayload></ClientPayload>>
     */
    fun readAllClientPayload(): Flow<List<ClientPayload>> {
        return flow {
            clientDao.getAllClientPayload().map { clientPayloads ->
                clientPayloads.map { clientPayload ->
                    val dataTablePayloads =
                        clientPayload.clientCreationTime?.let {
                            clientDao.getDataTablePayloadByCreationTime(
                                it,
                            )
                        }
                    val updatedDataTables = dataTablePayloads?.map { dataTablePayload ->
                        val data: HashMap<String, Any>? =
                            dataTablePayload.dataTableString?.let { json.decodeFromString(it) }
                        dataTablePayload.copy(data = data)
                    }
                    clientPayload.copy(datatables = updatedDataTables.toString())
                }
                emit(clientPayloads)
            }
        }
    }

    /**
     * This Method for deleting the client payload from the Database according to Id and
     * again fetch the client List from the DataTablePayload
     *
     * @param id is Id of the Client Payload in which reference client was saved into Database
     * @return List<ClientPayload> A flow emitting the updated client payload.
     */

    suspend fun deleteAndUpdatePayloads(
        id: Int,
        clientCreationTIme: Long,
    ): Flow<List<ClientPayload>> {
        clientDao.deleteClientPayloadById(id)
        clientDao.deleteDataTablePayloadByCreationTime(clientCreationTIme)
        return readAllClientPayload()
    }

    /**
     * This method updates the database with the given ClientPayload and returns the updated payload.
     *
     * @param clientPayload The client payload data to be updated in the database.
     * @return List<ClientPayload> A flow emitting the updated client payload.
     */
    fun updateDatabaseClientPayload(clientPayload: ClientPayload): Flow<ClientPayload> {
        return flow {
            clientDao.updateDatabaseClientPayload(clientPayload)
            emit(clientPayload)
        }
    }
}
