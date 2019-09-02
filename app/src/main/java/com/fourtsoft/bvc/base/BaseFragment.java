package com.fourtsoft.bvc.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;

import java.util.Objects;

public abstract class BaseFragment extends Fragment implements BaseContract.View {

  @Override public void showLoading() {

  }

  @Override public void showError() {

  }

  @Override public void showDataEmpty() {

  }

  @Override public void setData(BaseModel baseModel) {
    ((BaseActivity) Objects.requireNonNull(getActivity())).setData(baseModel);
  }

  @Override
  public boolean isHaveInternet() {
    return ((BaseActivity) Objects.requireNonNull(getActivity())).isHaveInternet();
  }
}
