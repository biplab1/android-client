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
    val code: String? = null,

    val name: String? = null,

    val decimalPlaces: Int? = null,

    val inMultiplesOf: Int? = null,

    val displaySymbol: String? = null,

    val nameCode: String? = null,

    val displayLabel: String? = null,
) : Parcelable
