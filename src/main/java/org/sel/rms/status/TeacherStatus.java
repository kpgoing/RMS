package org.sel.rms.status;

import org.sel.rms.status.inter.Statusable;

/**
 * Created by Huxh on 2016/11/3.
 */
public enum TeacherStatus implements Statusable {
    SUCCESS(0),ERROR(1),USERNAME_ERROR_OR_EXIST(2),PASSWORD_ERROR(3),ARGUMENTS_ERROR(4),
    SAVE_INFO_ERROR(5), SAVE_CHECK_STATUS_ERROR(6), MODIFY_PASSWORD_ERROR(7),LOGINED(8),
    DELETE_ERROR(9),UPLOAD_AVATAR_ERROR(10),OLDPASSWORD_ERROR(11),MODIFY_TEACHER_INFO_ERROR(12),
    RESET_PASSWORD_ERROR(13),FORGET_PASSWORD_ERROR(14);

    private int status;

    TeacherStatus(int status) {
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
