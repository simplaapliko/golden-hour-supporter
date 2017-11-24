/*
 * Copyright (C) 2017 Oleg Kan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simplaapliko.goldenhour.supporter.analytics;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public final class FirebaseAnalyticsImpl implements Analytics {

    private static final String TAG = "FirebaseAnalyticsImpl";

    private FirebaseAnalytics firebaseAnalytics;

    public FirebaseAnalyticsImpl(Context context) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    @Override
    public void sendScreen(String screen) {
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsStrings.PARAM_SCREEN, screen);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    public void sendEvent(String category, String label) {
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsStrings.PARAM_CATEGORY, category);
        bundle.putString(AnalyticsStrings.PARAM_LABEL, label);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}