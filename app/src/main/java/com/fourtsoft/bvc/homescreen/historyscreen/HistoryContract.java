package com.fourtsoft.bvc.homescreen.historyscreen;

import com.fourtsoft.bvc.base.BaseContract;
import com.fourtsoft.bvc.model.History;
import java.util.List;

public interface HistoryContract {

  interface View extends BaseContract.View {

    void showListHistory(List<History> history);

    void removeList();

  }

  interface Presenter<V extends View> extends BaseContract.Presenter<V> {

    void getListHistory();

    void deleteHistory(History history);

    void deleteAll();

  }

}
