package com.sorcererxw.demo.friendcircle.models;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/10/20
 */

public class HeadBean {
    private String mHeadBackground;

    private String mHeadAvatar;

    private String mHeadName;

    public HeadBean(String headBackground, String headAvatar, String headName) {
        mHeadBackground = headBackground;
        mHeadAvatar = headAvatar;
        mHeadName = headName;
    }

    public String getHeadBackground() {
        return mHeadBackground;
    }

    public void setHeadBackground(String headBackground) {
        mHeadBackground = headBackground;
    }

    public String getHeadAvatar() {
        return mHeadAvatar;
    }

    public void setHeadAvatar(String headAvatar) {
        mHeadAvatar = headAvatar;
    }

    public String getHeadName() {
        return mHeadName;
    }

    public void setHeadName(String headName) {
        mHeadName = headName;
    }
}
