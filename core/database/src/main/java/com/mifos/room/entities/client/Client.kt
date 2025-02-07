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
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mifos.core.entity.group.Group
import com.mifos.room.entities.Timeline
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "Client",
    foreignKeys = [
        ForeignKey(
            entity = Status::class,
            parentColumns = ["id"],
            childColumns = ["status"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = ClientDate::class,
            parentColumns = ["clientId"],
            childColumns = ["clientDate"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class Client(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "groupId")
    @Transient
    val groupId: Int? = 0,

    @ColumnInfo(name = "accountNo")
    val accountNo: String? = null,

    @ColumnInfo(name = "clientId")
    val clientId: Int? = null,

    @ColumnInfo(name = "status")
    val status: Status? = null,

    @ColumnInfo(name = "sync")
    @Transient
    val sync: Boolean = false,

    @ColumnInfo(name = "active")
    val active: Boolean = false,

    @ColumnInfo(name = "clientDate")
    val clientDate: ClientDate? = null,

    @ColumnInfo(name = "activationDate")
    val activationDate: List<Int?> = emptyList(),

    @ColumnInfo(name = "dobDate")
    val dobDate: List<Int?> = emptyList(),

    @ColumnInfo(name = "groups")
    val groups: List<Group?> = emptyList(),

    @ColumnInfo(name = "mobileNo")
    val mobileNo: String? = null,

    @ColumnInfo(name = "firstname")
    val firstname: String? = null,

    @ColumnInfo(name = "middlename")
    val middlename: String? = null,

    @ColumnInfo(name = "lastname")
    val lastname: String? = null,

    @ColumnInfo(name = "displayName")
    val displayName: String? = null,

    @ColumnInfo(name = "officeId")
    val officeId: Int = 0,

    @ColumnInfo(name = "officeName")
    val officeName: String? = null,

    @ColumnInfo(name = "staffId")
    val staffId: Int = 0,

    @ColumnInfo(name = "staffName")
    val staffName: String? = null,

    @ColumnInfo(name = "timeline")
    val timeline: Timeline? = null,

    @ColumnInfo(name = "fullname")
    val fullname: String? = null,

    @ColumnInfo(name = "imageId")
    val imageId: Int = 0,

    @ColumnInfo(name = "imagePresent")
    val imagePresent: Boolean = false,

    @ColumnInfo(name = "externalId")
    val externalId: String? = null,
) : Parcelable {

    val groupNames: String
        get() {
            var groupNames = ""
            if (groups.isEmpty()) return ""
            for (group in groups) {
                groupNames += group!!.name + ", "
            }
            return groupNames.substring(0, groupNames.length - 2)
        }
}
