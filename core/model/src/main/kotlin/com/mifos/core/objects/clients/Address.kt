/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.objects.clients

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Rajan Maurya on 15/12/16.
 */
@Parcelize
data class Address(
    var addressTypeId: Int? = null,

    var active: Boolean? = null,

    var street: String? = null,

    var stateProvinceId: Int? = null,

    var countryId: Int? = null,
) : Parcelable
