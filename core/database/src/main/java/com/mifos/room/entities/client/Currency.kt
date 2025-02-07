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
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "ClientChargeCurrency")
data class Currency(
    @PrimaryKey
    @ColumnInfo(name = "code")
    val code: String? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "decimalPlaces")
    val decimalPlaces: Int? = null,

    @ColumnInfo(name = "inMultiplesOf")
    val inMultiplesOf: Int? = null,

    @ColumnInfo(name = "displaySymbol")
    val displaySymbol: String? = null,

    @ColumnInfo(name = "nameCode")
    val nameCode: String? = null,

    @ColumnInfo(name = "displayLabel")
    val displayLabel: String? = null,
) : Parcelable
