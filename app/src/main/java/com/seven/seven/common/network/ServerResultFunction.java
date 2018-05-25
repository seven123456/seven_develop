package com.seven.seven.common.network;

import com.seven.seven.common.utils.GsonTools;
import com.seven.seven.common.utils.LogUtils;

import java.util.function.Function;

/**
 * Created by seven
 * on 2018/5/17
 * email:seven2016s@163.com
 */

public class ServerResultFunction implements io.reactivex.functions.Function<ResponseCustom, Object> {
    @Override
    public Object apply(ResponseCustom responseCustom) {
        LogUtils.jsonE("服务器返回结果===>" + GsonTools.createGsonString(responseCustom));

        return responseCustom;
    }
}
