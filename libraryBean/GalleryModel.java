package uml.yangwenjing.wenjingandroidlibrary.libraryBean;

/**
 * Created by yamengwenjing on 2016/8/16.
 */
public class GalleryModel {
    private int imgurl;
    private String title;

    public GalleryModel() {
    }

    public GalleryModel(int imgurl, String title) {
        this.imgurl = imgurl;
        this.title = title;
    }

    public int getImgurl() {
        return imgurl;
    }

    public void setImgurl(int imgurl) {
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GalleryModel{" +
                "imgurl=" + imgurl +
                ", title='" + title + '\'' +
                '}';
    }
}
