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
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.json.Json

/**
 * Created by nellyk on 2/15/2016.
 */
/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
@Parcelize
@Entity(
    tableName = "Charges",
    foreignKeys = [
        ForeignKey(
            entity = ChargeTimeType::class,
            parentColumns = ["id"],
            childColumns = ["chargeTimeType"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = ClientDate::class,
            parentColumns = ["clientId"],
            childColumns = ["chargeDueDate"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = ChargeCalculationType::class,
            parentColumns = ["id"],
            childColumns = ["chargeCalculationType"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Currency::class,
            parentColumns = ["code"],
            childColumns = ["currency"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class Charges(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "clientId")
    val clientId: Int? = null,

    @ColumnInfo(name = "loanId")
    val loanId: Int? = null,

    @ColumnInfo(name = "chargeId")
    val chargeId: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "chargeTimeType")
    val chargeTimeType: ChargeTimeType? = null,

    @ColumnInfo(name = "chargeDueDateId")
    val chargeDueDate: ClientDate? = null,

    @ColumnInfo(name = "dueDate")
    val dueDate: String? = null,

    @ColumnInfo(name = "chargeCalculationType")
    val chargeCalculationType: ChargeCalculationType? = null,

    @ColumnInfo(name = "currency")
    val currency: Currency? = null,

    @ColumnInfo(name = "amount")
    val amount: Double? = null,

    @ColumnInfo(name = "amountPaid")
    val amountPaid: Double? = null,

    @ColumnInfo(name = "amountWaived")
    val amountWaived: Double? = null,

    @ColumnInfo(name = "amountWrittenOff")
    val amountWrittenOff: Double? = null,

    @ColumnInfo(name = "amountOutstanding")
    val amountOutstanding: Double? = null,

    @ColumnInfo(name = "penalty")
    val penalty: Boolean? = null,

    @ColumnInfo(name = "active")
    val active: Boolean? = null,

    @ColumnInfo(name = "paid")
    val paid: Boolean? = null,

    @ColumnInfo(name = "waived")
    val waived: Boolean? = null,
) : Parcelable {

    val formattedDueDate: String
        get() {
            val pattern = "%s-%s-%s"

            val dueDateList = try {
                dueDate?.let { Json.decodeFromString<List<Int>>(it) }
            } catch (e: kotlinx.serialization.SerializationException) {
                emptyList()
            }

            if (dueDateList != null) {
                if (dueDateList.size > 2) {
                    return String.format(pattern, dueDateList[0], dueDateList[1], dueDateList[2])
                }
            }
            return "No Due Date"
        }
}
