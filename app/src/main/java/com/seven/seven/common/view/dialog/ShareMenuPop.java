package com.seven.seven.common.view.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.home.contract.HomeContract;

import static android.widget.ListPopupWindow.WRAP_CONTENT;

/**
 * Created by seven
 * on 2018/5/29
 * email:seven2016s@163.com
 */

public class ShareMenuPop extends PopupWindow {
    private Context mContext;

    public ShareMenuPop(Context context) {
        super(context);
        this.mContext = context;
        init();
    }
    public ShareMenuPop(View contentView, Context mContext) {
        //通过构造方法进行传递一个view进来
        super(contentView);
        this.mContext = mContext;
        init();
    }
    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_share_menu, null);
        setContentView(view);
        TextView weixinShare = view.findViewById(R.id.tv_weixin);
        TextView pyqShare = view.findViewById(R.id.tv_weixin_pyq);
        weixinShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popListenter != null) {
                    popListenter.wxItemShare();
                    dismiss();
                }
            }
        });
        pyqShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popListenter != null) {
                    popListenter.pyqItemShare();
                    dismiss();
                }
            }
        });
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable());
        setTouchable(true);
        setWidth(WRAP_CONTENT);
        setHeight(WRAP_CONTENT);
    }

    public sharePopListenter popListenter;

    public void setPopListenter(sharePopListenter sharePopListenter) {
        this.popListenter = sharePopListenter;
    }

    public interface sharePopListenter {
        void wxItemShare();

        void pyqItemShare();
    }
}
