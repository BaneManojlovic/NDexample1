package com.bitnovisad.ndexample1.home;

import android.view.View;

public interface IntHomeFragment {

    void onClickSend(View view);
    void onFbClick(View v);
    void onClickCall(View view);
    boolean isConnectedToInternet();
}
