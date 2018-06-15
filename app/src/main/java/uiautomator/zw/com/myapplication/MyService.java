package uiautomator.zw.com.myapplication;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by zhaowei on 2018/6/7.
 */

public class MyService extends Service {


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        //return super.onUnbind(intent);
        return true;
    }


    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    public interface OnServiceProgressListener {
        void onProgressChanged(int progress);
    }

    public void setOnServiceProgressChangedListener(OnServiceProgressListener listener) {
        //this.listener = listener;
        //this.listener = listener;
    }


    public static ServiceConnection bind(Context context, final OnServiceProgressListener listener) {
        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService myService = ((MyService.MyBinder) service).getService();
                myService.setOnServiceProgressChangedListener(listener);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                //HolaPrint.pr("service disconnected.");
            }
        };
        Intent i = new Intent(context, MyService.class);
        context.bindService(i, connection, BIND_AUTO_CREATE);
        return connection;
    }

    public static void unbind(Context context, final ServiceConnection connection) {


        context.unbindService(connection);
    }
}
