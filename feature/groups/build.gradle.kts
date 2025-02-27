/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
plugins {
    alias(libs.plugins.mifos.android.feature)
    alias(libs.plugins.mifos.android.library.compose)
    alias(libs.plugins.mifos.android.library.jacoco)
}


android {
    namespace = "com.mifos.feature.groups"
}

dependencies {
    implementation(projects.core.domain)

    // swipe refresh
    implementation(libs.accompanist.swiperefresh)

    // paging 3
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)

    //DBFlow dependencies
    implementation(libs.dbflow)

    androidTestImplementation(libs.androidx.compose.ui.test)
    debugApi(libs.androidx.compose.ui.test.manifest)

    testImplementation(libs.hilt.android.testing)
    testImplementation(projects.core.testing)

    androidTestImplementation(projects.core.testing)
}