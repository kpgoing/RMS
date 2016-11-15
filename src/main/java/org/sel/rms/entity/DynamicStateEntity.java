package org.sel.rms.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by xubowei on 15/11/2016.
 */
@Entity
@Table(name = "dynamic_state", schema = "RMS", catalog = "")
public class DynamicStateEntity {
    private int idDynamicState;
    private int idTeacher;
    private int idContent;
    private String kind;
    private String name;
    private String teacherName;
    private Timestamp publishDate;



    public DynamicStateEntity(int idTeacher, int idContent, String kind, String name, String teacherName, Timestamp publishDate) {
        this.idTeacher = idTeacher;
        this.idContent = idContent;
        this.kind = kind;
        this.name = name;
        this.teacherName = teacherName;
        this.publishDate = publishDate;
    }

    public DynamicStateEntity() {
    }

    @Id
    @Column(name = "id_dynamic_state", nullable = false)
    public int getIdDynamicState() {
        return idDynamicState;
    }

    public void setIdDynamicState(int idDynamicState) {
        this.idDynamicState = idDynamicState;
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
    @Column(name = "id_content", nullable = false)
    public int getIdContent() {
        return idContent;
    }

    public void setIdContent(int idContent) {
        this.idContent = idContent;
    }

    @Basic
    @Column(name = "kind", nullable = false, length = 45)
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "teacher_name", nullable = false, length = 45)
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Basic
    @Column(name = "publish_date", nullable = false)
    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DynamicStateEntity that = (DynamicStateEntity) o;

        if (idDynamicState != that.idDynamicState) return false;
        if (idTeacher != that.idTeacher) return false;
        if (idContent != that.idContent) return false;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (teacherName != null ? !teacherName.equals(that.teacherName) : that.teacherName != null) return false;
        if (publishDate != null ? !publishDate.equals(that.publishDate) : that.publishDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDynamicState;
        result = 31 * result + idTeacher;
        result = 31 * result + idContent;
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        return result;
    }
}
