package org.hopto.dklis.open_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.asus.robotframework.API.RobotAPI;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotCommand;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.results.DetectPersonResult;
import com.asus.robotframework.API.results.GesturePointResult;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends RobotActivity {

    public Button my_button, sensor_button;
    public static Context context;
    // static robotAPI
    public static RobotAPI x_robotAPI;

    public static RobotCallback robotCallback = new RobotCallback() {
        @Override
        public void onResult(int cmd, int serial, RobotErrorCode err_code, Bundle result) {
            super.onResult(cmd, serial, err_code, result);
        }

        @Override
        public void onStateChange(int cmd, int serial, RobotErrorCode err_code, RobotCmdState state) {
            super.onStateChange(cmd, serial, err_code, state);

            String txt_out = "onStateChange:"
                    + RobotCommand.getRobotCommand(cmd).name()
                    + ", serial:" + serial + ", err_code:" + err_code
                    + ", state:" + state.toString();

            Toast.makeText(context, txt_out, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDetectPersonResult(List<DetectPersonResult> resultList) {
            super.onDetectPersonResult(resultList);

            Log.d("RobotDevSample", "onDetectPersonResult: " + resultList.get(0).getBodyLoc().toString());

            // use toast to show detected persons
            String toast_result = "Detect Person";
            Toast toast = Toast.makeText(context, toast_result, Toast.LENGTH_SHORT);
            toast.show();

        }

        @Override
        public void onGesturePoint(GesturePointResult result) {
            super.onGesturePoint(result);
        }

        @Override
        public void initComplete() {
            super.initComplete();
            x_robotAPI.vision.requestDetectPerson(1000);
        }
    };

    public static RobotCallback.Listen robotListenCallback = new RobotCallback.Listen() {
        @Override
        public void onFinishRegister() {

        }

        @Override
        public void onVoiceDetect(JSONObject jsonObject) {

        }

        @Override
        public void onSpeakComplete(String s, String s1) {

        }

        @Override
        public void onEventUserUtterance(JSONObject jsonObject) {

        }

        @Override
        public void onResult(JSONObject jsonObject) {

        }

        @Override
        public void onRetry(JSONObject jsonObject) {

        }
    };

    public MainActivity() {
        super(robotCallback, robotListenCallback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        x_robotAPI = this.robotAPI;

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

        sensor_button = (Button)findViewById(R.id.button2);
        sensor_button.setOnClickListener(new Button.OnClickListener(){
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
                activityIntent.setComponent(new ComponentName("com.asus.zenbogotolocation","com.asus.zenbogotolocation.MainActivity"));
                startActivity(activityIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // start detect person
        x_robotAPI.vision.requestDetectPerson(1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // stop detect person
        x_robotAPI.vision.cancelDetectPerson();
    }

}
