/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.network.datamanager

import com.mifos.core.entity.organisation.Office
import com.mifos.core.network.BaseApiManager
import com.mifos.core.network.mappers.offices.GetOfficeResponseMapper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This DataManager is for Managing Offices API, In which Request is going to Server
 * and In Response, We are getting Offices API Observable Response using Retrofit2.
 * DataManagerOffices saving response in Database and response to Presenter as accordingly.
 *
 * Created by Rajan Maurya on 7/7/16.
 */
@Singleton
class DataManagerOffices @Inject constructor(
    val mBaseApiManager: BaseApiManager,
//    private val mDatabaseHelperOffices: DatabaseHelperOffices,
    private val baseApiManager: org.mifos.core.apimanager.BaseApiManager,
//    private val prefManager: com.mifos.core.datastore.PrefManager,
) {
    /**
     * return all List of Offices from DatabaseHelperOffices
     */
    suspend fun offices(): List<Office> {
        return baseApiManager.getOfficeApi().retrieveOffices(null, null, null).map(
            GetOfficeResponseMapper::mapFromEntity,
        )
    }
//    val offices: Observable<List<Office>>
//        get() = when (prefManager.userStatus) {
//            false -> baseApiManager.getOfficeApi().retrieveOffices(null, null, null)
//                .map(GetOfficeResponseMapper::mapFromEntityList)
//
//            true ->
//                /**
//                 * return all List of Offices from DatabaseHelperOffices
//                 */
//                /**
//                 * return all List of Offices from DatabaseHelperOffices
//                 */
//                mDatabaseHelperOffices.readAllOffices()
//        }
}
