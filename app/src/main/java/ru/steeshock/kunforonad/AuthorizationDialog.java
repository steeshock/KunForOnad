package ru.steeshock.kunforonad;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthorizationDialog extends DialogFragment implements OnClickListener {


    final String LOG_TAG = "myLogs";
    public static final String LOGIN = "onad";
    public static final String PASSWORD = "1111";

    private EditText et_login, et_password;

    public interface TheFragmentListener {

        //данный интерфейс необходим для общения с главной активити
        // а именно - чтобы дисейблить поля

        void setFieldsEnabled();

    }

    private TheFragmentListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (TheFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "Must implement ChanFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Авторизуйтесь");
        View v = inflater.inflate(R.layout.authorization, null);
        Button btn_yes = v.findViewById(R.id.btnYes);
        Button btn_no = v.findViewById(R.id.btnNo);

        btn_yes.setOnClickListener(this);
        btn_no.setOnClickListener(this);

        et_login = v.findViewById(R.id.et_login);
        et_password = v.findViewById(R.id.et_password);

        return v;
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnYes:

                if (et_login.getText().toString().equals(LOGIN) && et_password.getText().toString().equals(PASSWORD)){
                    MainActivity.MENU_FLAG = true;
                    getActivity().invalidateOptionsMenu();
                    mListener.setFieldsEnabled();
                    et_login.getText().clear();
                    et_password.getText().clear();
                    Toast.makeText(getActivity(), R.string.welcome, Toast.LENGTH_LONG).show();
                    getDialog().cancel();
                } else {
                    et_login.setError(getString(R.string.error));
                    et_password.setError(getString(R.string.error));
                }
                break;

            case R.id.btnNo:
                dismiss(); break;
        }
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}