package tms.cloud.com.model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Set;

@Table(name = "ORGN_IMG")
@Entity
public class LogImgDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer imgId;

    @Column(length = 20, unique = true, nullable = false)
    private String title;

    @Column(name = "IMAGES", nullable = false)
    private Blob images;

    // Constructors

    // Getters
    public Integer getImgId() {
        return imgId;
    }

    public String getTitle() {
        return title;
    }

    public Blob getImages() {
        return images;
    }

    // Setters
    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImages(Blob images) {
        this.images = images;
    }
}
