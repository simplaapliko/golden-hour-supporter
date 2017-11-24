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

package com.simplaapliko.goldenhour.supporter;

import android.content.Context;
import android.content.Intent;

import com.simplaapliko.about.util.AppInfo;
import com.simplaapliko.about.util.Assistant;

import java.util.Locale;

public class SupportProjectUtils {

    public static Intent getHelpWithTranslationIntent(Context context) {
        String subject = getHelpWithTranslationEmailSubject(context);
        String body = getHelpWithTranslationEmailBody(context);
        String email = getHelpWithTranslationEmailAddress(context);

        return Assistant.getSendToIntent(subject, body, email);
    }

    public static String getHelpWithTranslationEmailBody(Context context) {
        String newLine = "\n";
        StringBuilder body = new StringBuilder();
        body.append(context.getString(R.string.a_feedback_region));
        body.append(Locale.getDefault().getLanguage());
        body.append(" / ");
        body.append(Locale.getDefault().getCountry());
        body.append(newLine);

        String appVersion = AppInfo.getAppVersion(context);

        body.append(context.getString(R.string.a_feedback_app_version));
        body.append(appVersion);
        body.append(newLine);
        body.append(newLine);

        body.append(context.getString(R.string.translate_to_your_language_message));

        return body.toString();
    }

    public static String getHelpWithTranslationEmailAddress(Context context) {
        return context.getString(R.string.developer_feedback_email);
    }

    public static String getHelpWithTranslationEmailSubject(Context context) {
        return "[" + context.getString(R.string.app_name) + "] "
                + context.getString(R.string.translate_to_your_language_subject);
    }
}
