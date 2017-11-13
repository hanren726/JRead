package com.minglei.jread.beans.zhihu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by minglei on 2017/11/13.
 */

public class ZhihuLatestNews implements Serializable{

    /**
     * date : 20171113
     * stories : [{"images":["https://pic3.zhimg.com/v2-c3120669972f6a25f5470175a4bcf622.jpg"],"type":0,"id":9656658,"ga_prefix":"111315","title":"江歌遇害后第 294 天，江母和刘鑫终于见面"},{"images":["https://pic2.zhimg.com/v2-2c07775e27dced9f5cec264eeee0a6f9.jpg"],"type":0,"id":9656133,"ga_prefix":"111314","title":"张着口呼吸，为什么对一个人的容貌影响那么大？"},{"images":["https://pic3.zhimg.com/v2-4ffc8f204ecd4d567fbf06ffc82f07da.jpg"],"type":0,"id":9656042,"ga_prefix":"111313","title":"一个必备的效率常识：用手机的日历功能，也可以高效处理待办"},{"images":["https://pic1.zhimg.com/v2-6f7c31fb16ac510d46bb85382f27ff7c.jpg"],"type":0,"id":9655214,"ga_prefix":"111312","title":"大误 · 游戏里的壮举"},{"title":"每次逛美术馆，拍照发朋友圈后，就不知道该干什么了","ga_prefix":"111311","images":["https://pic2.zhimg.com/v2-902472e04cb93316959494f085a60319.jpg"],"multipic":true,"type":0,"id":9656481},{"title":"鲸鱼会痒吗？痒的话自己怎么挠？","ga_prefix":"111310","images":["https://pic1.zhimg.com/v2-310b04ab9874931d8534d7cf7d010a78.jpg"],"multipic":true,"type":0,"id":9655812},{"title":"这广告连续播了十几次，怕不是在放鬼畜吧\u2026\u2026","ga_prefix":"111309","images":["https://pic3.zhimg.com/v2-28bb06ab3c0aa3aea9cfdadfa7a42422.jpg"],"multipic":true,"type":0,"id":9656290},{"images":["https://pic2.zhimg.com/v2-ee58fd156a86fa4a7290271069d50c19.jpg"],"type":0,"id":9656056,"ga_prefix":"111308","title":"东北人爱吃冻梨和冻柿子，还拿冻了的葡萄酿酒"},{"images":["https://pic1.zhimg.com/v2-39784f8c23239506052bbd7ba13fabc0.jpg"],"type":0,"id":9656074,"ga_prefix":"111307","title":"这么和 HR 谈薪资，入职后秒杀周围同事"},{"images":["https://pic3.zhimg.com/v2-63665f5775235a76db2286bfc3f22ed6.jpg"],"type":0,"id":9656490,"ga_prefix":"111307","title":"今年双十一优惠规则可真复杂，像是做了好几道数学题"},{"images":["https://pic2.zhimg.com/v2-2aebe7ec42828d93d19c2786fd6bde81.jpg"],"type":0,"id":9656245,"ga_prefix":"111307","title":"26 万个「鹿晗」：社交公司围堵「00 后」"},{"images":["https://pic2.zhimg.com/v2-e8fd1723026616c79ce2d0a29cc8913d.jpg"],"type":0,"id":9655007,"ga_prefix":"111306","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-471b8b3243c6b3d6ac4c061e81402c69.jpg","type":0,"id":9656658,"ga_prefix":"111315","title":"江歌遇害后第 294 天，江母和刘鑫终于见面"},{"image":"https://pic3.zhimg.com/v2-d390cfe25baa92ece6f59208b09cfdb2.jpg","type":0,"id":9656490,"ga_prefix":"111307","title":"今年双十一优惠规则可真复杂，像是做了好几道数学题"},{"image":"https://pic2.zhimg.com/v2-f9ca9ee9cc3d3ddeb179f25980cfd55d.jpg","type":0,"id":9655812,"ga_prefix":"111310","title":"鲸鱼会痒吗？痒的话自己怎么挠？"},{"image":"https://pic2.zhimg.com/v2-9dfd29a5ea3671f00b4a7025a1178579.jpg","type":0,"id":9656461,"ga_prefix":"111217","title":"- 我们能换情侣头像吗？\r\n- 嗯\u2026\u2026"},{"image":"https://pic3.zhimg.com/v2-03780e23d5fb21cbb2a487c891ee0bd2.jpg","type":0,"id":9656122,"ga_prefix":"111210","title":"同样是载客出游，为什么机长和船长比长途大巴司机更受尊重？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    @Override
    public String toString() {
        return "ZhihuLatestNews{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
