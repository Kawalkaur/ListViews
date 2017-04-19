package listview.com.listviews;

/**
 * Created by kawaldeep on 3/28/2017.
 */

public class FileBean {

    int image;
    String titles;

    public FileBean() {
    }

    public FileBean(int image) {
        this.image = image;
    }

    public FileBean(int image, String titles) {
        this.image = image;
        this.titles = titles;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }


}
