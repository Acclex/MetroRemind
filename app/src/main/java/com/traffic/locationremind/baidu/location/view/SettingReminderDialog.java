package com.traffic.locationremind.baidu.location.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.traffic.locationremind.R;


public class SettingReminderDialog extends Dialog implements OnClickListener{
    Context context;
    private String transferInfo;
    private String exitInfo;
    private String lineId;
    private String station;
    private String city;
    private NoticeDialogListener listener;
    //对话框事件监听接口，用于处理回调点击事件
    public interface NoticeDialogListener {
        public void onClick(View view);
    }
    public SettingReminderDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
    }
    public SettingReminderDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }
    public SettingReminderDialog(Context context, int theme, NoticeDialogListener listener
            , String transferInfo, String exitInfo, String lineId, String city, String station){
        super(context, theme);
        this.context = context;
        this.listener = listener;
        this.transferInfo = transferInfo;
        this.exitInfo = exitInfo;
        this.station = station;
        this.city = city;
        this.lineId = lineId;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView title = (TextView) findViewById(R.id.title);
        TextView transfer = (TextView) findViewById(R.id.transfer);
        TextView exit_info = (TextView) findViewById(R.id.exit_info);
       // Button cancel_action = (Button) findViewById(R.id.cancel_action);
       // Button save_action = (Button) findViewById(R.id.save_action);
        Button start = (Button) findViewById(R.id.start);
        Button end = (Button) findViewById(R.id.end);

        title.setText(convertString());
        transfer.setText(transferInfo);
        exit_info.setText(exitInfo);

       // cancel_action.setOnClickListener(this);
       // save_action.setOnClickListener(this);

        start.setOnClickListener(this);
        end.setOnClickListener(this);

        setCanceledOnTouchOutside(false);

    }
    private String convertString() {
        return city + context.getResources().getString(R.string.subway) + lineId + context.getResources().getString(R.string.subway_tail)
                +" " + station;
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        listener.onClick(v);
    }
}