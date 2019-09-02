package com.fourtsoft.bvc.homescreen.contactscreen;

import com.fourtsoft.bvc.model.Contact;

public interface ContactFragmentListener {

    void callClick(Contact contact);

    void detailContactClick(Contact contact);
}
