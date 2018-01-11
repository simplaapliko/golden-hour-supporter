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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.simplaapliko.goldenhour.supporter.R;
import com.simplaapliko.goldenhour.supporter.analytics.Analytics;
import com.simplaapliko.goldenhour.supporter.analytics.AnalyticsStrings;
import com.simplaapliko.goldenhour.supporter.analytics.FirebaseAnalyticsImpl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Analytics analytics;
    private MainNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        analytics = new FirebaseAnalyticsImpl(this);
        navigator = new MainNavigator(this);

        setContentView(R.layout.activity_main);

        findViewById(R.id.beta_group_tile).setOnClickListener(this);
        findViewById(R.id.feedback_tile).setOnClickListener(this);
        findViewById(R.id.rate_tile).setOnClickListener(this);
        findViewById(R.id.translate_tile).setOnClickListener(this);

        analytics.sendScreen(AnalyticsStrings.SCREEN_MAIN);
    }

    @Override public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.beta_group_tile:
                onBetaGroupClick();
                break;
            case R.id.feedback_tile:
                onFeedbackClick();
                break;
            case R.id.rate_tile:
                onRateClick();
                break;
            case R.id.translate_tile:
                onTranslateClick();
                break;
        }
    }

    private void onBetaGroupClick() {
        analytics.sendEvent(
                AnalyticsStrings.CATEGORY_SUPPORT_PROJECT,
                AnalyticsStrings.labelForTouch("Beta_Tester"));

        navigator.goToJoinBetaGroup();
    }

    private void onFeedbackClick() {
        analytics.sendEvent(
                AnalyticsStrings.CATEGORY_FEEDBACK,
                AnalyticsStrings.labelForTouch("Feedback"));

        navigator.sendFeedback();
    }

    private void onRateClick() {
        analytics.sendEvent(
                AnalyticsStrings.CATEGORY_FEEDBACK,
                AnalyticsStrings.labelForTouch("Rate_App"));

        navigator.goToPlayStore();
    }

    private void onTranslateClick() {
        analytics.sendEvent(
                AnalyticsStrings.CATEGORY_SUPPORT_PROJECT,
                AnalyticsStrings.labelForTouch("Translate"));

        navigator.sendTranslationFeedback();
    }
}
