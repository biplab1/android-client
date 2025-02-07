/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.room.entities.client

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "ClientDate",
    primaryKeys = ["clientId", "chargeId"],
)
data class ClientDate(
    @ColumnInfo(name = "clientId")
    val clientId: Long = 0,

    @ColumnInfo(name = "chargeId")
    val chargeId: Long = 0,

    @ColumnInfo(name = "day")
    val day: Int = 0,

    @ColumnInfo(name = "month")
    val month: Int = 0,

    @ColumnInfo(name = "year")
    val year: Int = 0,
) : Parcelable
