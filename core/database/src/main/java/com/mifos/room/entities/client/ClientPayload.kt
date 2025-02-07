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
@Entity(tableName = "ClientPayload")
data class ClientPayload(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "clientCreationTime")
    @Transient
    val clientCreationTime: Long? = null,

    @ColumnInfo(name = "errorMessage")
    @Transient
    val errorMessage: String? = null,

    @ColumnInfo(name = "firstname")
    val firstname: String? = null,

    @ColumnInfo(name = "lastname")
    val lastname: String? = null,

    @ColumnInfo(name = "middlename")
    val middlename: String? = null,

    @ColumnInfo(name = "officeId")
    val officeId: Int? = null,

    @ColumnInfo(name = "staffId")
    val staffId: Int? = null,

    @ColumnInfo(name = "genderId")
    val genderId: Int? = null,

    @ColumnInfo(name = "active")
    val active: Boolean? = null,

    @ColumnInfo(name = "activationDate")
    val activationDate: String? = null,

    @ColumnInfo(name = "submittedOnDate")
    val submittedOnDate: String? = null,

    @ColumnInfo(name = "dateOfBirth")
    val dateOfBirth: String? = null,

    @ColumnInfo(name = "mobileNo")
    val mobileNo: String? = null,

    @ColumnInfo(name = "externalId")
    val externalId: String? = null,

    @ColumnInfo(name = "clientTypeId")
    val clientTypeId: Int? = null,

    @ColumnInfo(name = "clientClassificationId")
    val clientClassificationId: Int? = null,

    @ColumnInfo(name = "address")
    val address: String? = null,

    @ColumnInfo(name = "dateFormat")
    val dateFormat: String? = "dd MMMM YYYY",

    @ColumnInfo(name = "locale")
    val locale: String? = "en",

    @ColumnInfo(name = "datatables")
    val datatables: String? = null,
) : Parcelable
