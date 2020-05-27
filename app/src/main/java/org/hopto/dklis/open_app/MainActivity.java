package org.hopto.dklis.open_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button my_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_button = (Button)findViewById(R.id.button);
        my_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                /**
                 * 《Android》『呼叫外部 App』- 透過 startActivity 執行外部 App 的基本方法
                 * https://xnfood.com.tw/android-call-app-startactivity/
                 *
                 * [透過 Package Name、Class Name 呼叫另一支程式]
                 * 若是透過 Package Name 與 Class Name 的方式，我們可以直接由 A 啟動 B 而不需要額外註冊，由 A 啟動 B 之程式碼如下
                 * pkg : Package Name
                 * cls : Class Name
                 */
                Intent activityIntent = new Intent();
                activityIntent.setComponent(new ComponentName("org.hopto.dklis.zenbo_qa_service","org.hopto.dklis.zenbo_qa_service.MainActivity"));
                startActivity(activityIntent);
            }
        });
    }
}
