/*
 * Copyright © 2019 Zhenjie Yan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liany.csiserverapp.andServer.manager;

import android.content.Context;

import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.model.common.base.BaseApplication;
import com.yanzhenjie.andserver.annotation.Config;
import com.yanzhenjie.andserver.framework.config.Multipart;
import com.yanzhenjie.andserver.framework.config.WebConfig;
import com.yanzhenjie.andserver.framework.website.AssetsWebsite;
import com.yanzhenjie.andserver.framework.website.StorageWebsite;

import java.io.File;

/**
 * Created by Zhenjie Yan on 2019-06-30.
 */
@Config
public class AppConfig implements WebConfig {

    @Override
    public void onConfig(Context context, Delegate delegate) {
        delegate.addWebsite(new StorageWebsite(Constants.photoPath));
//        delegate.addWebsite(new AssetsWebsite(BaseApplication.getContext(),"/web"));

        delegate.setMultipart(Multipart.newBuilder()
            .allFileMaxSize(1024 * 1024 * 100) // 100M
            .fileMaxSize(1024 * 1024 * 30) // 30M
            .maxInMemorySize(1024 * 100) // 1024 * 100 bytes
            .uploadTempDir(new File(context.getCacheDir(), "_server_upload_cache_"))
            .build());
    }
}