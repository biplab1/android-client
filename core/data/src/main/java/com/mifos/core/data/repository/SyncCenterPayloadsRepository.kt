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

import com.mifos.core.entity.center.CenterPayload
import com.mifos.core.objects.responses.SaveResponse
import rx.Observable

/**
 * Created by Aditya Gupta on 16/08/23.
 */
interface SyncCenterPayloadsRepository {

    fun allDatabaseCenterPayload(): Observable<List<CenterPayload>>

    fun createCenter(centerPayload: CenterPayload): Observable<SaveResponse>

    fun deleteAndUpdateCenterPayloads(id: Int): Observable<List<CenterPayload>>

    fun updateCenterPayload(centerPayload: CenterPayload): Observable<CenterPayload>
}
