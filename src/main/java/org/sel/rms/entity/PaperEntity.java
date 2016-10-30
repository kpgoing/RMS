package org.sel.rms.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by xubowei on 29/10/2016.
 */
@Entity
@Table(name = "Paper", schema = "RMS", catalog = "")
public class PaperEntity {
    private int idPaper;
    private String title;
    private Date releaseDate;
    private String writer;
    private Date publishDate;
    private String publishPlace;
    private String keyWord;
    private String abstractText;
    private String content;
    private String param1;
    private String param2;

    @Id
    @Column(name = "idPaper", nullable = false)
    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "releaseDate", nullable = false)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "writer", nullable = false, length = 45)
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Basic
    @Column(name = "publishDate", nullable = false)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "publishPlace", nullable = false, length = 45)
    public String getPublishPlace() {
        return publishPlace;
    }

    public void setPublishPlace(String publishPlace) {
        this.publishPlace = publishPlace;
    }

    @Basic
    @Column(name = "keyWord", nullable = false, length = 45)
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Basic
    @Column(name = "abstractText", nullable = false, length = -1)
    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Basic
    @Column(name = "param1", nullable = true, length = 45)
    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    @Basic
    @Column(name = "param2", nullable = true, length = 45)
    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaperEntity that = (PaperEntity) o;

        if (idPaper != that.idPaper) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
        if (writer != null ? !writer.equals(that.writer) : that.writer != null) return false;
        if (publishDate != null ? !publishDate.equals(that.publishDate) : that.publishDate != null) return false;
        if (publishPlace != null ? !publishPlace.equals(that.publishPlace) : that.publishPlace != null) return false;
        if (keyWord != null ? !keyWord.equals(that.keyWord) : that.keyWord != null) return false;
        if (abstractText != null ? !abstractText.equals(that.abstractText) : that.abstractText != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (param1 != null ? !param1.equals(that.param1) : that.param1 != null) return false;
        if (param2 != null ? !param2.equals(that.param2) : that.param2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPaper;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (writer != null ? writer.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (publishPlace != null ? publishPlace.hashCode() : 0);
        result = 31 * result + (keyWord != null ? keyWord.hashCode() : 0);
        result = 31 * result + (abstractText != null ? abstractText.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (param1 != null ? param1.hashCode() : 0);
        result = 31 * result + (param2 != null ? param2.hashCode() : 0);
        return result;
    }
}
