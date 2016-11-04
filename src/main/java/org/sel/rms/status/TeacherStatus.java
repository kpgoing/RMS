package org.sel.rms.status;

import org.sel.rms.status.inter.Statusable;

/**
 * Created by Huxh on 2016/11/3.
 */
public enum TeacherStatus implements Statusable {
    SUCCESS(0),ERROR(1),USERNAME_ERROR_OR_EXIST(2),PASSWORD_ERROR(3),ARGUMENTS_ERROR(4);

    private int status;

    TeacherStatus(int status) {
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
