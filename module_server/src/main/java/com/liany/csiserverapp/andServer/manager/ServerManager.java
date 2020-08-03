package com.liany.csiserverapp.andServer.manager;

import android.content.Context;

import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.utils.IpUtils;
import com.liany.csiserverapp.utils.LogUtils;
import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @创建者 ly
 * @创建时间 2020/3/3
 * @描述 服务启动manager
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class ServerManager {
    private Server mServer;

    /**
     * Create server.
     */
    public ServerManager(Context mContext) {
        InetAddress inetAddress = null;
        try {
            //热点为固定ip 192.168.43.1
//            inetAddress = InetAddress.getByName("0.0.0.0");
            inetAddress = InetAddress.getByName(Constants.ip);
//            inetAddress = InetAddress.getByName(IpUtils.getIpAddress(mContext));
            LogUtils.e("ipAddress ip:" + Constants.ip);
            LogUtils.e("ipAddress:" + Constants.ipAddress);
            LogUtils.e("ipAddress local:" + IpUtils.getIpAddress(mContext));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        mServer = AndServer.serverBuilder(mContext)
                .inetAddress(inetAddress)
                .port(8080)
                .timeout(10, TimeUnit.SECONDS)
                .listener(new Server.ServerListener() {
                    @Override
                    public void onStarted() {
                        // TODO The server started successfully.
                        LogUtils.e("onStarted");
                    }

                    @Override
                    public void onStopped() {
                        // TODO The server has stopped.
                        LogUtils.e("onStopped");
                    }

                    @Override
                    public void onException(Exception e) {
                        // TODO An exception occurred while the server was starting.
                        LogUtils.e("onException:" + e.getMessage());
                    }
                })
                .build();


    }

    /**
     * Start server.
     */
    public void startServer() {
        if (mServer.isRunning()) {
            // TODO The server is already up.
        } else {
            mServer.startup();
        }
    }

    /**
     * Stop server.
     */
    public void stopServer() {
        if (mServer.isRunning()) {
            mServer.shutdown();
        } else {
            LogUtils.e("The server has not started yet.");
        }
    }
}