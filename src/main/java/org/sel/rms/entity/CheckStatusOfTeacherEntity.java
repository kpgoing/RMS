package org.sel.rms.entity;

import javax.persistence.*;

/**
 * Created by xubowei on 30/10/2016.
 */
@Entity
@Table(name = "check_status_of_teacher", schema = "RMS", catalog = "")
public class CheckStatusOfTeacherEntity {
    private int idCheckStatusOfTeacher;
    private int idTeacher;
    private byte checkStatus;

    @Id
    @Column(name = "id_check_status_of_teacher", nullable = false)
    public int getIdCheckStatusOfTeacher() {
        return idCheckStatusOfTeacher;
    }

    public void setIdCheckStatusOfTeacher(int idCheckStatusOfTeacher) {
        this.idCheckStatusOfTeacher = idCheckStatusOfTeacher;
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
    @Column(name = "check_status", nullable = false)
    public byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckStatusOfTeacherEntity that = (CheckStatusOfTeacherEntity) o;

        if (idCheckStatusOfTeacher != that.idCheckStatusOfTeacher) return false;
        if (idTeacher != that.idTeacher) return false;
        if (checkStatus != that.checkStatus) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCheckStatusOfTeacher;
        result = 31 * result + idTeacher;
        result = 31 * result + (int) checkStatus;
        return result;
    }
}
