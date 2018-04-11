package com.smyy.sharetour.buyer.bean;


import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:44
 * E-Mail Address：wuzf2012@sina.com
 */
public class CommentsBean extends MultiItemEntity {
    public static int SINGLE_CHAT = 0;
    public static int MORE_CHAT = 1;
    public static int MORE_REPLY = 2;
    private List<MainList> mainList;

    public List<MainList> getMainList() {
        return mainList;
    }

    public void setMainList(List<MainList> mainList) {
        this.mainList = mainList;
    }

    public class MainList {
        private String imageUrl;

        private String name;

        private int praise;

        private String content;

        private String time;

        private List<CsList> csList;

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public int getPraise() {
            return this.praise;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return this.time;
        }

        public void setCsList(List<CsList> csList) {
            this.csList = csList;
        }

        public List<CsList> getCsList() {
            return this.csList;
        }

        public void setCsCount() {
            if (getCsList() != null) {
                if (getCsList().size() > 0) {
                    if (getCsList().size() > 3) {
                        CsList bean = getCsList().get(0);
                        bean.setCount(getCsList().size());
                        List<CsList> list = new ArrayList<>();
                        list.add(bean);
                        setCsList(list);
                    }
                }
            }
        }

    }

    public class CsList extends MultiItemEntity {

        private String TImageUrl;

        private String TName;

        private String TPassiveName;

        private String TContent;
        private int Count;

        public int getCount() {
            return Count;
        }

        public void setCount(int count) {
            Count = count;
        }

        private int TPraise;

        public void setTImageUrl(String TImageUrl) {
            this.TImageUrl = TImageUrl;
        }

        public String getTImageUrl() {
            return this.TImageUrl;
        }

        public void setTName(String TName) {
            this.TName = TName;
        }

        public String getTName() {
            return this.TName;
        }

        public void setTPassiveName(String TPassiveName) {
            this.TPassiveName = TPassiveName;
        }

        public String getTPassiveName() {
            return this.TPassiveName;
        }

        public void setTContent(String TContent) {
            this.TContent = TContent;
        }

        public String getTContent() {
            return this.TContent;
        }

        public void setTPraise(int TPraise) {
            this.TPraise = TPraise;
        }

        public int getTPraise() {
            return this.TPraise;
        }

        @Override
        public int getItemType() {
            if (getCount() > 3) {
                return CommentsBean.MORE_REPLY;
            }
            if (TextUtils.isEmpty(getTPassiveName())) {
                return CommentsBean.SINGLE_CHAT;
            } else {
                return CommentsBean.MORE_CHAT;
            }
        }
    }
}
