package com.fourtsoft.bvc.dao;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.fourtsoft.bvc.di.AppContext;
import com.fourtsoft.bvc.model.Contact;
import com.fourtsoft.bvc.utils.LogUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class PreferenceHelper implements Preference {
    private Context context;

    @Inject
    public PreferenceHelper(@AppContext Context context) {
        this.context = context;
    }

    @Override
    public Flowable<List<Contact>> getAllContact() {
        LogUtils.setLogSequenceMethod();
        return Flowable.fromCallable(new Callable<List<Contact>>() {
            @Override
            public List<Contact> call() {
                return getListContact();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<Contact>> getObserverPersonalContact(final String mobileNumber) {
        Log.e("++++", mobileNumber);

        return Flowable.fromCallable(new Callable<List<Contact>>() {
            @Override
            public List<Contact> call() throws Exception {
                return getContact(mobileNumber);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public List<Contact> getPersonalContact(String mobileNumber) {
        return getContact(mobileNumber);
    }


    private List<Contact> getListContact() {
        final List<Contact> listContact = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        // contacts
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor == null) {
            return listContact;
        }

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {

                    Cursor cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                            ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(id)));

                    Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(id));
                    Uri pURI = Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

                    Bitmap photo = null;
                    if (inputStream != null) {
                        photo = BitmapFactory.decodeStream(inputStream);
                    }

                    while (Objects.requireNonNull(cursorInfo).moveToNext()) {
                        Contact info = new Contact();
                        info.setId(id);
                        info.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                        info.setMobileNumber(cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                        info.setPhoto(photo);
                        info.setPhotoURI(pURI);
                        listContact.add(info);
                    }

                    if (!cursorInfo.isClosed()) {
                        cursorInfo.close();
                    }
                }
            }
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return listContact;
    }


    private List<Contact> getContact(String number) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        List<Contact> list = new ArrayList<>();

        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//                ContactsContract.CommonDataKinds.Photo.PHOTO_URI,
        };
        if (Objects.requireNonNull(cursor).getCount() > 0) {
            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {


                    Cursor cursorPhone
                            = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            PROJECTION,
                            ContactsContract.CommonDataKinds.Phone.NUMBER + "=?",
                            new String[]{number},
                            null, null);
                    if (cursorPhone != null && cursor.moveToFirst()) {
                        do {
                            Contact contact = new Contact();
                            contact.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                            list.add(contact);
                        } while (cursor.moveToNext());
                    }

                    Objects.requireNonNull(cursorPhone).close();

                }
            }
        }
        cursor.close();
        return list;
    }
}
