package com.smyy.sharetour.buyer.ui.test2;

import com.smyy.sharetour.buyer.network.BaseJsonData;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/16 10:22
 * E-Mail Address：wuzf2012@sina.com
 */
public class Test2JsonData extends BaseJsonData<Test2JsonData> {
    private String reason;

    private List<Result> result ;

    private int error_code;

    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return this.reason;
    }
    public void setResult(List<Result> result){
        this.result = result;
    }
    public List<Result> getResult(){
        return this.result;
    }
    public void setError_code(int error_code){
        this.error_code = error_code;
    }
    public int getError_code(){
        return this.error_code;
    }
    public class Result {
        private String content;

        private String hashId;

        private int unixtime;

        private String url;

        public void setContent(String content){
            this.content = content;
        }
        public String getContent(){
            return this.content;
        }
        public void setHashId(String hashId){
            this.hashId = hashId;
        }
        public String getHashId(){
            return this.hashId;
        }
        public void setUnixtime(int unixtime){
            this.unixtime = unixtime;
        }
        public int getUnixtime(){
            return this.unixtime;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }

    }
    @Override
    public Test2JsonData obtainUIModel() {
        return this;
    }
}
