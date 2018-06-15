package uiautomator.zw.com.myapplication;

import android.content.ServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import uiautomator.zw.com.myapplication.api.Api;
import uiautomator.zw.com.myapplication.api.ApiGenerator;
import uiautomator.zw.com.myapplication.api.req.ReqLogin;
import uiautomator.zw.com.myapplication.model.User;

public class MainActivity extends AppCompatActivity implements MyService.OnServiceProgressListener{

     String json = "{\n" +
            "\t“user_name” : “zhaowei”,\n" +
            "\t“password”:”123”,\n" +
            "\t“list”:[\n" +
            "\t\t{\n" +
            "\t\t\t“name”:”  语文”,\n" +
            "\t\t\t“number”:”100”\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t“name”:”  数学”,\n" +
            "\t\t\t“number”:”100”\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t“map”:{\n" +
            "“user_name” : “zhaowei”,\n" +
            "\t“password”:”123”\n" +
            "\n" +
            "\t}\n" +
            "}";


    ServiceConnection connection;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReqLogin reqLogin = new ReqLogin("zhaowei@email.com");
                ApiGenerator.Companion.createService(Api.class)
                        .login(reqLogin)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<User>() {
                            @Override
                            public void call(User user) {

                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                                Log.e("zhaowei", "call: " + throwable.getMessage());
                            }
                        });
            }
        });

        findViewById(R.id.unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyService.unbind(MainActivity.this, connection);
            }
        });

    }

    @Override
    public void onProgressChanged(int progress) {

    }
}
