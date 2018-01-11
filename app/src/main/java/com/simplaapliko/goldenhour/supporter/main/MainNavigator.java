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

package com.simplaapliko.goldenhour.supporter.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.simplaapliko.about.util.Assistant;
import com.simplaapliko.goldenhour.supporter.R;
import com.simplaapliko.goldenhour.supporter.utils.SupportProjectUtils;

import java.util.List;

public class MainNavigator {

    private AppCompatActivity context;

    public MainNavigator(AppCompatActivity context) {
        this.context = context;
    }

    void goToJoinBetaGroup() {
        String betaGroupUrl = "https://play.google.com/apps/testing/com.simplaapliko.goldenhour";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(betaGroupUrl));
        context.startActivity(browserIntent);
    }

    void goToPlayStore() {
        String packageName = "com.simplaapliko.goldenhour";

        // create Play Store intent
        Intent rate =
                new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));

        if (!isIntentCallable(context, rate)) {
            // if Play Store doesn't exist show in browser
            rate = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + packageName));
        }

        if (!isIntentCallable(context, rate)) {
            // if browser app doesn't exist, show toast and quit
            Toast.makeText(context, context.getString(R.string.unable_to_find_google_play),
                    Toast.LENGTH_LONG).show();
            return;
        }

        context.startActivity(rate);
    }

    void sendFeedback() {
        String feedbackEmail = context.getString(R.string.developer_feedback_email);
        String appName = context.getString(R.string.app_name);
        Assistant.sendFeedback(context, feedbackEmail, appName);
    }

    void sendTranslationFeedback() {
        Intent intentToStart = SupportProjectUtils.getHelpWithTranslationIntent(context);
        Assistant.startActivity(context, intentToStart);
    }

    private boolean isIntentCallable(Context context, Intent intent) {
        List<ResolveInfo> list = context.getPackageManager()
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
