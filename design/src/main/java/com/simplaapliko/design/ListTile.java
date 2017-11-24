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

package com.simplaapliko.design;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListTile extends LinearLayout {

    private int image;
    private String primaryText;
    private String secondaryText;
    private int primaryTextAppearance;
    private int secondaryTextAppearance;

    private LinearLayout rootView;
    private FrameLayout imageLayout;
    private ImageView imageView;
    private TextView primaryTextView;
    private TextView secondaryTextView;

    public ListTile(Context context) {
        this(context, null);
    }

    public ListTile(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListTile(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        inflate(context, R.layout.list_tile, this);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ListTile, 0, 0);

        try {
            image = a.getResourceId(R.styleable.ListTile_image, 0);
            primaryText = a.getString(R.styleable.ListTile_primaryText);
            secondaryText = a.getString(R.styleable.ListTile_secondaryText);
            primaryTextAppearance = a.getResourceId(R.styleable.ListTile_primaryTextAppearance, 0);
            secondaryTextAppearance = a.getResourceId(R.styleable.ListTile_secondaryTextAppearance, 0);
        } finally {
            a.recycle();
        }

        rootView = findViewById(R.id.root_view);
        imageLayout = findViewById(R.id.image_layout);
        imageView = findViewById(R.id.image);
        primaryTextView = findViewById(R.id.primary_text);
        secondaryTextView = findViewById(R.id.secondary_text);

        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        setBackgroundResource(outValue.resourceId);

        if (primaryTextAppearance == 0) {
            primaryTextView.setTextAppearance(context, R.style.ListTileText_Primary);
        } else {
            primaryTextView.setTextAppearance(context, primaryTextAppearance);
        }

        if (secondaryTextAppearance == 0) {
            secondaryTextView.setTextAppearance(context, R.style.ListTileText_Secondary);
        } else {
            secondaryTextView.setTextAppearance(context, secondaryTextAppearance);
        }

        setImage(image);
        setPrimaryText(primaryText);
        setSecondaryText(secondaryText);

        secondaryTextView.setText(secondaryText);
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;

        if (this.image == 0) {
            imageLayout.setVisibility(GONE);
        } else {
            imageView.setImageResource(this.image);
            imageLayout.setVisibility(VISIBLE);
        }
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public void setPrimaryText(String primaryText) {
        this.primaryText = primaryText;
        primaryTextView.setText(this.primaryText);

        if (this.primaryText == null) {
            primaryTextView.setVisibility(GONE);
        }
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;

        secondaryTextView.setText(this.secondaryText);

        if (this.secondaryText == null) {
            secondaryTextView.setVisibility(GONE);
        }
    }
}
