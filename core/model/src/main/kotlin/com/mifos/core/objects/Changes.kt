/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.objects

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class Changes(
    @Expose
    var transactionDate: String? = null,

    @Expose
    var transactionAmount: String? = null,

    @Expose
    var locale: String? = null,

    @Expose
    var dateFormat: String? = null,

    @Expose
    var note: String? = null,

    @Expose
    var accountNumber: String? = null,

    @Expose
    var checkNumber: String? = null,

    @Expose
    var routingCode: String? = null,

    @Expose
    var receiptNumber: String? = null,

    @Expose
    var bankNumber: String? = null,
) : Parcelable
