/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.room.entities.client

/**
 * Created by ishankhanna on 09/02/14.
 */
data class Page<T>(
    val totalFilteredRecords: Int = 0,

    val pageItems: List<T> = ArrayList(),
)
