package com.fourtsoft.bvc.utils;

import android.content.Context;

import com.fourtsoft.bvc.dao.Preference;
import com.fourtsoft.bvc.dao.PreferenceHelper;
import com.fourtsoft.bvc.model.Contact;

import java.util.List;

public class ContactUtils {

    public static List<Contact> getPersonalNumber(Context context,String phoneNumber){
        Preference preference = new PreferenceHelper(context.getApplicationContext());
        return preference.getPersonalContact(phoneNumber);
    }
}
