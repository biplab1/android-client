/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.room.entities.noncore

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Rajan Maurya on 01/10/16.
 */
@Parcelize
data class IdentifierPayload(
    val documentTypeId: Int? = null,

    val documentKey: String? = null,

    val status: String? = null,

    val description: String? = null,
) : Parcelable
