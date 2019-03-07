package com.sankuai.waimai.router.fragment;
/*
 * Copyright (C) 2005-2018 Meituan Inc.All Rights Reserved.
 * Description：
 * History：
 *
 * @desc
 * @author chenmeng06
 * @date 2019/3/6
 */


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.sankuai.waimai.router.common.PageAnnotationHandler;
import com.sankuai.waimai.router.components.ActivityLauncher;
import com.sankuai.waimai.router.core.UriRequest;

import java.io.Serializable;


/**
 * Fragment路由基类 支持了extra参数
 */
public abstract class AbsFragmentUriTransactionRequest extends UriRequest {
    public final static String CUSTOM_FRAGMENT_NAME = "CUSTOM_FRAGMENT_NAME";

    protected final static int TYPE_ADD = 1;
    protected final static int TYPE_REPLACE = 2;
    protected final static int TYPE_CUSTOM = 3;

    protected int mType = TYPE_ADD;
    protected int mContainerViewId;
    protected boolean mAllowingStateLoss;
    protected String mTag;

    public AbsFragmentUriTransactionRequest(@NonNull Context context, String uri) {
        super(context, PageAnnotationHandler.SCHEME_HOST + uri);
    }

    /**
     * 在containerViewId上添加指定的Fragment
     *
     * @param containerViewId 容器ID
     * @return this
     */
    public AbsFragmentUriTransactionRequest add(@IdRes int containerViewId) {
        mContainerViewId = containerViewId;
        mType = TYPE_ADD;
        return this;
    }

    /**
     * 在containerViewId上替换指定的Fragment
     *
     * @param containerViewId 容器ID
     * @return this
     */
    public AbsFragmentUriTransactionRequest replace(@IdRes int containerViewId) {
        mContainerViewId = containerViewId;
        mType = TYPE_REPLACE;
        return this;
    }

    /**
     * 自定义处理fragment 通过CUSTOM_FRAGMENT_NAME 在完成的时候自行获取处理Fragment
     * @return this
     */
    public AbsFragmentUriTransactionRequest custom() {
        mContainerViewId = 0;
        mType = TYPE_CUSTOM;
        return this;
    }

    /**
     * 指定tag
     *
     * @param tag 指定tag
     * @return this
     */
    public AbsFragmentUriTransactionRequest tag(String tag) {
        mTag = tag;
        return this;
    }

    /**
     * 允许状态丢失的提交
     *
     * @return this
     */
    public AbsFragmentUriTransactionRequest allowingStateLoss() {
        mAllowingStateLoss = true;
        return this;
    }

    @Override
    public void start() {
        putField(StartFragmentAction.START_FRAGMENT_ACTION, getStartFragmentAction());
        super.start();
    }

    protected abstract StartFragmentAction getStartFragmentAction();

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, Serializable value) {
        extra().putSerializable(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, boolean[] value) {
        extra().putBooleanArray(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, byte[] value) {
        extra().putByteArray(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, short[] value) {
        extra().putShortArray(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, char[] value) {
        extra().putCharArray(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, int[] value) {
        extra().putIntArray(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, long[] value) {
        extra().putLongArray(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, float[] value) {
        extra().putFloatArray(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, double[] value) {
        extra().putDoubleArray(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, String[] value) {
        extra().putStringArray(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, CharSequence[] value) {
        extra().putCharSequenceArray(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtra(String name, Bundle value) {
        extra().putBundle(name, value);
        return this;
    }

    /**
     * 附加到Intent的Extra
     */
    public AbsFragmentUriTransactionRequest putExtras(Bundle extras) {
        if (extras != null) {
            extra().putAll(extras);
        }
        return this;
    }

    @NonNull
    private synchronized Bundle extra() {
        Bundle extra = getField(Bundle.class, ActivityLauncher.FIELD_INTENT_EXTRA, null);
        if (extra == null) {
            extra = new Bundle();
            putField(ActivityLauncher.FIELD_INTENT_EXTRA, extra);
        }
        return extra;
    }
}
