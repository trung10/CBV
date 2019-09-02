package com.fourtsoft.bvc.homescreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.homescreen.contactscreen.ContactFragment;
import com.fourtsoft.bvc.homescreen.dialscreen.DialNumberFragment;
import com.fourtsoft.bvc.homescreen.historyscreen.HistoryFragment;
import com.fourtsoft.bvc.homescreen.settingscreen.SettingFragment;
import com.fourtsoft.bvc.homescreen.themescreen.ThemeFragment;
import com.fourtsoft.bvc.model.Contact;
import com.fourtsoft.bvc.utils.Constant;

import java.lang.reflect.Field;

public class HomeFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private Fragment nowFragmet;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        bottomNavigationView = view.findViewById(R.id.bottom_navigation);

        //set default screen
        nowFragmet = ThemeFragment.newInstance();
        bottomNavigationView.setSelectedItemId(R.id.action_theme);

        //Disable shiftMode
        disableShiftMode(bottomNavigationView);
        /*default is theme screen*/
        nowFragmet = ThemeFragment.newInstance();
        setView(nowFragmet, Constant.THEME_PAGE);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        String tag = "";
                        switch (item.getItemId()) {
                            case R.id.action_call:
                                nowFragmet = DialNumberFragment.newInstance();
                                tag = Constant.CALL_PAGE;
                                break;

                            case R.id.action_contact:
                                nowFragmet = ContactFragment.newInstance();
                                tag = Constant.CONTACT_PAGE;
                                break;

                            case R.id.action_history:
                                nowFragmet = HistoryFragment.newInstance();
                                tag = Constant.HISTORY_PAGE;
                                break;

                            case R.id.action_setting:
                                nowFragmet = SettingFragment.newInstance();
                                tag = Constant.SETTING_PAGE;
                                break;

                            case R.id.action_theme:
                                nowFragmet = ThemeFragment.newInstance();
                                tag = Constant.THEME_PAGE;
                                break;
                        }
                        setView(nowFragmet, tag);
                        return true;
                    }
                });
    }

    // in per activity, layout must have a FrameLayout with name "contentLayout"
    public void setView(Fragment fragment, String tag) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContentLayout, fragment, tag);
        ft.commit();
    }

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }
}