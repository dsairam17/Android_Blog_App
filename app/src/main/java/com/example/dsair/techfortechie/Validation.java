package com.example.dsair.techfortechie;

import android.text.TextUtils;
import android.util.Patterns;

public class Validation {
    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
