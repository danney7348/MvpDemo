package com.bwie.lianxi0927.bean;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/19.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GetOrders {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-10-20T21:02:50","orderid":377,"price":48155.79,"status":0,"uid":170},{"createtime":"2017-10-21T15:41:30","orderid":475,"price":18785.9,"status":0,"uid":170},{"createtime":"2017-10-21T15:41:55","orderid":477,"price":12097,"status":0,"uid":170},{"createtime":"2017-10-21T15:42:09","orderid":478,"price":0,"status":0,"uid":170},{"createtime":"2017-10-21T15:42:15","orderid":480,"price":12097,"status":0,"uid":170},{"createtime":"2017-10-21T15:50:29","orderid":484,"price":12119.9,"status":0,"uid":170},{"createtime":"2017-10-21T16:17:42","orderid":509,"price":297,"status":0,"uid":170},{"createtime":"2017-10-21T16:17:42","orderid":510,"price":22,"status":0,"uid":170},{"createtime":"2017-10-21T16:17:49","orderid":511,"price":297,"status":0,"uid":170},{"createtime":"2017-10-21T16:24:44","orderid":515,"price":0,"status":0,"uid":170}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-10-20T21:02:50
         * orderid : 377
         * price : 48155.79
         * status : 0
         * uid : 170
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
