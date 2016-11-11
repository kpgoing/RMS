package org.sel.rms.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.sel.rms.entity.ValidGroup.PaperGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

import static com.sun.tools.doclint.Entity.not;
import static com.sun.tools.doclint.Entity.nu;


/**
 * Created by xubowei on 30/10/2016.
 */
@Entity
@Table(name = "Paper", schema = "RMS", catalog = "")
public class PaperEntity {
    @NotNull(groups = {PaperGroup.modify.class})
    private int idPaper;
    @NotNull(groups = {PaperGroup.publish.class, PaperGroup.modify.class})
    private int idTeacher;
    @NotBlank(groups = {PaperGroup.publish.class, PaperGroup.modify.class})
    private String title;
    @NotNull(groups = {PaperGroup.publish.class, PaperGroup.modify.class})
    private Date releaseDate;
    @NotBlank(groups = {PaperGroup.publish.class, PaperGroup.modify.class})
    private String writer;
    private Date publishDate;
    @NotBlank(groups = {PaperGroup.publish.class, PaperGroup.modify.class})
    private String publishPlace;
    @NotBlank(groups = {PaperGroup.publish.class, PaperGroup.modify.class})
    private String keyWord;
    @NotBlank(groups = {PaperGroup.publish.class, PaperGroup.modify.class})
    private String abstractContent;
    @NotBlank(groups = {PaperGroup.publish.class, PaperGroup.modify.class})
    private String content;
    private String param1;
    private String param2;

    @Id
    @Column(name = "id_paper", nullable = false)
    public int getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(int idPaper) {
        this.idPaper = idPaper;
    }

    @Basic
    @Column(name = "id_teacher", nullable = false)
    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
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
    @Column(name = "release_date", nullable = false)
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
    @Column(name = "publish_date", nullable = false)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "publish_place", nullable = false, length = 45)
    public String getPublishPlace() {
        return publishPlace;
    }

    public void setPublishPlace(String publishPlace) {
        this.publishPlace = publishPlace;
    }

    @Basic
    @Column(name = "key_word", nullable = false, length = 45)
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Basic
    @Column(name = "abstract_content", nullable = false, length = -1)
    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
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
        if (idTeacher != that.idTeacher) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
        if (writer != null ? !writer.equals(that.writer) : that.writer != null) return false;
        if (publishDate != null ? !publishDate.equals(that.publishDate) : that.publishDate != null) return false;
        if (publishPlace != null ? !publishPlace.equals(that.publishPlace) : that.publishPlace != null) return false;
        if (keyWord != null ? !keyWord.equals(that.keyWord) : that.keyWord != null) return false;
        if (abstractContent != null ? !abstractContent.equals(that.abstractContent) : that.abstractContent != null)
            return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (param1 != null ? !param1.equals(that.param1) : that.param1 != null) return false;
        if (param2 != null ? !param2.equals(that.param2) : that.param2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPaper;
        result = 31 * result + idTeacher;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (writer != null ? writer.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (publishPlace != null ? publishPlace.hashCode() : 0);
        result = 31 * result + (keyWord != null ? keyWord.hashCode() : 0);
        result = 31 * result + (abstractContent != null ? abstractContent.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (param1 != null ? param1.hashCode() : 0);
        result = 31 * result + (param2 != null ? param2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PaperEntity{" +
                "idPaper=" + idPaper +
                ", idTeacher=" + idTeacher +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", writer='" + writer + '\'' +
                ", publishDate=" + publishDate +
                ", publishPlace='" + publishPlace + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", abstractContent='" + abstractContent + '\'' +
                ", content='" + content + '\'' +
                ", param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                '}';
    }
}
