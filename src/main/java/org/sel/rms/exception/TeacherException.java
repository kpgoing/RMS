package org.sel.rms.exception;

import org.sel.rms.entity.TeacherEntity;
import org.sel.rms.status.TeacherStatus;

/**
 * Created by Huxh on 2016/11/4.
 */
public class TeacherException extends RuntimeException {

    TeacherStatus teacherStatus = TeacherStatus.ERROR;

    public TeacherStatus getTeacherStatus() {
        return teacherStatus;
    }

    public TeacherException(TeacherStatus teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    public TeacherException(String message, TeacherStatus teacherStatus) {
        super(message);
        this.teacherStatus = teacherStatus;
    }

    public TeacherException(String message, Throwable cause, TeacherStatus teacherStatus) {
        super(message, cause);
        this.teacherStatus = teacherStatus;
    }

    public TeacherException(Throwable cause, TeacherStatus teacherStatus) {
        super(cause);
        this.teacherStatus = teacherStatus;
    }

    public TeacherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, TeacherStatus teacherStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.teacherStatus = teacherStatus;
    }
}
