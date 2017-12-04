package com.minglei.jread.beans.zhihu.zhuanlan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minglei on 2017/12/1.
 */

public class Post implements Parcelable{

    /**
     * isTitleImageFullScreen : false
     * rating : none
     * sourceUrl :
     * publishedTime : 2014-10-25T17:41:07+08:00
     * links : {"comments":"/api/posts/19877106/comments"}
     * author : {"bio":"无聊就答题","isFollowing":false,"hash":"aae3ece94ed646750dc056f2035ab032","uid":40087551213568,"isOrg":false,"slug":"svenshi","isFollowed":false,"description":"希望自己每天都能变得更好。","name":"Sven","profileUrl":"https://www.zhihu.com/people/svenshi","avatar":{"id":"bab849a83","template":"https://pic4.zhimg.com/50/{id}_{size}.jpg"},"isOrgWhiteList":false,"isBanned":false}
     * url : /p/19877106
     * title : 偏好，偏见，歧视
     * titleImage : https://pic1.zhimg.com/72a623a3dc0889cc7358cc0d70a0ac68_r.jpg
     * summary :
     * content : 偏好就是你喜欢吃香菜；<p>偏见就是你觉得不喜欢吃香菜的人味觉有问题；</p><p>歧视就是你觉得不喜欢吃香菜的人智商有问题。</p><p>对偏好要包容，对偏见要努力消除，对歧视要抵制。看着是不是很简单？</p><p>可惜这三者的界限往往很模糊。</p><p>举个例子，30岁的你想让婚介帮自己找个年轻的人女性结婚，所以他加上了一个择偶条件“30岁以下20岁以上”。这时候一个人走了过来，告诉他:“你这是歧视!你有没有想过为什么现在所有人都要求女性30岁以下，30岁以上的女性难道真的就有什么不同了吗？抵制歧视，就应该从现在做起。“</p><p>你一想似乎是这个道理啊，而后你就把择偶条件改成了60岁以下。正当这个时候，又有个人走过来，他问了你第二个问题：”你有没有想过60岁以上女性的感受？难道人退休了就不应该有爱情了吗？为什么你要在纸面上区别对待某类人呢？“</p><p>是啊，为什么要做这样的事情呢？</p><p>你毅然决然的划掉了你的择偶年龄标准。在经过一番无比复杂的筛选之后，你终于找到了一个年芳25的完美伴侣。正当你和她一起去民政局准备领证的时候，一大帮人跑到了你的面前，愤怒的指责你：”你这个人怎么就是不听劝呢？我们那么苦口婆心的和你讲，不要歧视30岁以上的女子，你最后怎么还是选了一个不到30的女子？你心里还是有歧视啊！抵制歧视，应该从你我做起！“<br></p><p>你不好意思的放开了伴侣的手，独自一个人走回家里，快到家门的时候才想起来：”不对啊，我只是想找个比我年轻的结婚罢了。“</p><p>看到这里你可能会觉得上面的事情很荒诞，可惜这样的事情其实每天都发生在我们周围。偏好的过度表达造成了偏见，偏见的集中表达又引起了歧视。贝克尔（Gary Becker）在他的《种族歧视经济学》（The Economics of Discrimination ）写的更加精辟:歧视往往是一种社会习俗，对个体来说，不遵从规则往往就意味着被赶出这个群体，但是留在这个群体里面对普通人来说往往是利大于弊的。歧视成了主流，非歧视的行为就会反过来受损。违背规则，往往会受到损失。</p><p>有的时候，一个人想说自己不喜欢吃香菜也是件不容易的事情。</p>
     * state : published
     * href : /api/posts/19877106
     * meta : {"previous":null,"next":null}
     * commentPermission : anyone
     * snapshotUrl :
     * canComment : false
     * slug : 19877106
     * commentsCount : 51
     * likesCount : 558
     */

    private boolean isTitleImageFullScreen;
    private String rating;
    private String sourceUrl;
    private String publishedTime;
    private LinksBean links;
    private AuthorBean author;
    private String url;
    private String title;
    private String titleImage;
    private String summary;
    private String content;
    private String state;
    private String href;
    private String commentPermission;
    private String snapshotUrl;
    private boolean canComment;
    private int slug;
    private int commentsCount;
    private int likesCount;

    protected Post(Parcel in) {
        isTitleImageFullScreen = in.readByte() != 0;
        rating = in.readString();
        sourceUrl = in.readString();
        publishedTime = in.readString();
        url = in.readString();
        title = in.readString();
        titleImage = in.readString();
        summary = in.readString();
        content = in.readString();
        state = in.readString();
        href = in.readString();
        commentPermission = in.readString();
        snapshotUrl = in.readString();
        canComment = in.readByte() != 0;
        slug = in.readInt();
        commentsCount = in.readInt();
        likesCount = in.readInt();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public boolean isIsTitleImageFullScreen() {
        return isTitleImageFullScreen;
    }

    public void setIsTitleImageFullScreen(boolean isTitleImageFullScreen) {
        this.isTitleImageFullScreen = isTitleImageFullScreen;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getCommentPermission() {
        return commentPermission;
    }

    public void setCommentPermission(String commentPermission) {
        this.commentPermission = commentPermission;
    }

    public String getSnapshotUrl() {
        return snapshotUrl;
    }

    public void setSnapshotUrl(String snapshotUrl) {
        this.snapshotUrl = snapshotUrl;
    }

    public boolean isCanComment() {
        return canComment;
    }

    public void setCanComment(boolean canComment) {
        this.canComment = canComment;
    }

    public int getSlug() {
        return slug;
    }

    public void setSlug(int slug) {
        this.slug = slug;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isTitleImageFullScreen ? 1 : 0));
        dest.writeString(this.rating);
        dest.writeString(this.sourceUrl);
        dest.writeString(this.publishedTime);
        dest.writeParcelable(this.links, flags);
        dest.writeParcelable(this.author, flags);
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.titleImage);
        dest.writeString(this.summary);
        dest.writeString(this.content);
        dest.writeString(this.state);
        dest.writeString(this.href);
        dest.writeString(this.commentPermission);
        dest.writeString(this.snapshotUrl);
        dest.writeByte((byte) (canComment ? 1 : 0));
        dest.writeInt(this.slug);
        dest.writeInt(this.commentsCount);
        dest.writeInt(this.likesCount);
    }

    @Override
    public String toString() {
        return "Post{" +
                "isTitleImageFullScreen=" + isTitleImageFullScreen +
                ", rating='" + rating + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", publishedTime='" + publishedTime + '\'' +
                ", links=" + links +
                ", author=" + author +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", titleImage='" + titleImage + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", href='" + href + '\'' +
                ", commentPermission='" + commentPermission + '\'' +
                ", snapshotUrl='" + snapshotUrl + '\'' +
                ", canComment=" + canComment +
                ", slug=" + slug +
                ", commentsCount=" + commentsCount +
                ", likesCount=" + likesCount +
                '}';
    }
}
