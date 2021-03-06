package com.loopfire.meitaotao.common;

import android.view.View.OnClickListener;

/**
 * 基础BaseActivity的回调接口
 *
 */
public interface IBaseActivity extends OnClickListener{

	void initView();// 初始化界面数据

	void initListener(); // / 初始化事件

	void refresh(Object... param);// 数据回调刷新

}
