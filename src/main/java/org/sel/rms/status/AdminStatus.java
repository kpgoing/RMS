package org.sel.rms.status;

import org.sel.rms.status.inter.Statusable;

/**
 * Created by Huxh on 2016/11/1.
 */
public enum AdminStatus implements Statusable {
    SUCCESS(0),ERROR(1),USERNAME_ERROR_OR_EXIST(2),PASSWORD_ERROR(3);

    private int status;

    AdminStatus(int status) {
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
