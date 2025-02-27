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

import com.mifos.core.data.repository.SyncCentersDialogRepository
import com.mifos.core.entity.accounts.CenterAccounts
import com.mifos.core.entity.accounts.ClientAccounts
import com.mifos.core.entity.accounts.GroupAccounts
import com.mifos.core.entity.accounts.loan.LoanWithAssociations
import com.mifos.core.entity.accounts.savings.SavingsAccountWithAssociations
import com.mifos.core.entity.client.Client
import com.mifos.core.entity.group.Center
import com.mifos.core.entity.group.CenterWithAssociations
import com.mifos.core.entity.group.Group
import com.mifos.core.entity.group.GroupWithAssociations
import com.mifos.core.entity.templates.loans.LoanRepaymentTemplate
import com.mifos.core.entity.templates.savings.SavingsAccountTransactionTemplate
import com.mifos.core.network.datamanager.DataManagerCenter
import com.mifos.core.network.datamanager.DataManagerClient
import com.mifos.core.network.datamanager.DataManagerGroups
import com.mifos.core.network.datamanager.DataManagerLoan
import com.mifos.core.network.datamanager.DataManagerSavings
import rx.Observable
import javax.inject.Inject

/**
 * Created by Aditya Gupta on 16/08/23.
 */
class SyncCentersDialogRepositoryImp @Inject constructor(
    private val dataManagerCenter: DataManagerCenter,
    private val dataManagerLoan: DataManagerLoan,
    private val dataManagerSavings: DataManagerSavings,
    private val dataManagerGroups: DataManagerGroups,
    private val dataManagerClient: DataManagerClient,
) : SyncCentersDialogRepository {

    override fun syncCenterAccounts(centerId: Int): Observable<CenterAccounts> {
        return dataManagerCenter.syncCenterAccounts(centerId)
    }

    override fun syncLoanById(loanId: Int): Observable<LoanWithAssociations> {
        return dataManagerLoan.syncLoanById(loanId)
    }

    override fun syncLoanRepaymentTemplate(loanId: Int): Observable<LoanRepaymentTemplate> {
        return dataManagerLoan.syncLoanRepaymentTemplate(loanId)
    }

    override fun getCenterWithAssociations(centerId: Int): Observable<CenterWithAssociations> {
        return dataManagerCenter.getCenterWithAssociations(centerId)
    }

    override fun getGroupWithAssociations(groupId: Int): Observable<GroupWithAssociations> {
        return dataManagerGroups.getGroupWithAssociations(groupId)
    }

    override fun syncGroupAccounts(groupId: Int): Observable<GroupAccounts> {
        return dataManagerGroups.syncGroupAccounts(groupId)
    }

    override suspend fun syncClientAccounts(clientId: Int): ClientAccounts {
        return dataManagerClient.syncClientAccounts(clientId)
    }

    override fun syncGroupInDatabase(group: Group): Observable<Group> {
        return dataManagerGroups.syncGroupInDatabase(group)
    }

    override fun syncClientInDatabase(client: Client): Observable<Client> {
        return dataManagerClient.syncClientInDatabase(client)
    }

    override fun syncCenterInDatabase(center: Center): Observable<Center> {
        return dataManagerCenter.syncCenterInDatabase(center)
    }

    override fun syncSavingsAccount(
        type: String?,
        savingsAccountId: Int,
        association: String?,
    ): Observable<SavingsAccountWithAssociations> {
        return dataManagerSavings.syncSavingsAccount(type, savingsAccountId, association)
    }

    override fun syncSavingsAccountTransactionTemplate(
        savingsAccountType: String?,
        savingsAccountId: Int,
        transactionType: String?,
    ): Observable<SavingsAccountTransactionTemplate> {
        return dataManagerSavings.syncSavingsAccountTransactionTemplate(
            savingsAccountType,
            savingsAccountId,
            transactionType,
        )
    }
}
