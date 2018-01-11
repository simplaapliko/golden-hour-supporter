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

package com.simplaapliko.goldenhour.supporter.settings;

import android.content.Context;

public class SharedPreferencesSettings implements Settings {

    private static final String PREFERENCES = "com.simplaapliko.goldenhour.supporter.preferences";
    private static final String PREF_IS_FIRST_LAUNCH = "is_first_launch";

    private final Context context;

    public SharedPreferencesSettings(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public boolean isFirstLaunch() {
        return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                .getBoolean(PREF_IS_FIRST_LAUNCH, true);
    }

    @Override
    public void setFirstLaunch(boolean isFirstLaunch) {
        context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(PREF_IS_FIRST_LAUNCH, isFirstLaunch)
                .apply();
    }
}
