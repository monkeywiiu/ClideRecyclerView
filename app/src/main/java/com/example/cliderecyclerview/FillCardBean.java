package com.example.cliderecyclerview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

public class FillCardBean {
    public int position;
    public String url;
    public String name;
    FillCardBean(int position, String url, String name) {
        this.position = position;
        this.url = url;
        this.name = name;
    }
    public static List<FillCardBean> initDatas() {
        List<FillCardBean> list = new ArrayList<>();
        list.add(new FillCardBean(0, "http://a0.att.hudong.com/24/30/01300538288874136786306808451.jpg", "娜美"));
        list.add(new FillCardBean(1, "http://pic24.nipic.com/20121102/10849215_133336203000_2.jpg", "罗宾"));
        list.add(new FillCardBean(2, "http://pic25.nipic.com/20121109/10204421_231259180108_2.jpg", "女帝"));
        list.add(new FillCardBean(3, "http://snowcoal.com/IMAGES_72324/201507/14361297987300.jpg", "薇薇"));
        list.add(new FillCardBean(4, "http://img1.gamedog.cn/2013/08/08/43-130PQ441090-50.jpg", "路飞"));
        list.add(new FillCardBean(5, "http://bizhi.drame.cn/uploads/allimg/120728/1-120HR22046.jpg", "乔巴"));
        return list;
    }
}
