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

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.PurchaseEvent;
import com.simplaapliko.goldenhour.supporter.settings.Settings;
import com.simplaapliko.goldenhour.supporter.settings.SharedPreferencesSettings;

import java.math.BigDecimal;
import java.util.Currency;

public class AppInteractor {

    private final Settings settings;

    public AppInteractor(Context context) {
        this.settings = new SharedPreferencesSettings(context);
    }

    public void onAppLaunched() {
        if (settings.isFirstLaunch()) {

            PurchaseEvent purchaseEvent = new PurchaseEvent().putItemPrice(BigDecimal.valueOf(4.99))
                    .putCurrency(Currency.getInstance("USD"))
                    .putItemName("Supporter_App")
                    .putSuccess(true);

            Answers.getInstance().logPurchase(purchaseEvent);

            settings.setFirstLaunch(false);
        }
    }
}
