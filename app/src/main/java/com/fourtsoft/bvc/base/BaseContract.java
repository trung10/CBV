package com.fourtsoft.bvc.base;

public interface BaseContract {

  interface View {

    void showLoading();

    void showError();

    void showDataEmpty();

    void setData(BaseModel baseModel);

    boolean isHaveInternet();
  }

  interface Presenter<V extends View> {

    void onAttach(V v);

    void onDettach();

    V getView();

    /**
     *
     * @return
     */
    boolean isViewAttach();
  }
}
