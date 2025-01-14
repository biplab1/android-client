/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
@file:OptIn(ExperimentalMaterial3Api::class)

package com.mifos.core.designsystem.component

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MifosBottomSheet(
    content: @Composable () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(true) }

    fun dismissSheet() {
        coroutineScope.launch { modalSheetState.hide() }.invokeOnCompletion {
            if (!modalSheetState.isVisible) {
                showBottomSheet = false
            }
        }
        onDismiss.invoke()
    }

    BackHandler(modalSheetState.isVisible) {
        dismissSheet()
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = showBottomSheet,
    ) {
        ModalBottomSheet(
            containerColor = Color.White,
            onDismissRequest = {
                showBottomSheet = false
                dismissSheet()
            },
            sheetState = modalSheetState,
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun MifosBottomSheetPreview() {
    MifosBottomSheet(
        {
            Box {
                Modifier.height(100.dp)
            }
        },
        {},
    )
}
