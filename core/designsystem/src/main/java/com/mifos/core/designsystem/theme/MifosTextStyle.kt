/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.designsystem.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val aboutItemTextStyle = TextStyle(
    color = Color.Black,
    textAlign = TextAlign.Center,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
)

val aboutItemTextStyleBold = TextStyle(
    color = Color.Black,
    textAlign = TextAlign.Center,
    fontSize = 24.sp,
    fontWeight = FontWeight.SemiBold,
)

val identifierTextStyleDark = TextStyle(
    color = Color.Black,
    textAlign = TextAlign.Start,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
)

val identifierTextStyleLight = TextStyle(
    color = DarkGray,
    textAlign = TextAlign.Start,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
)
