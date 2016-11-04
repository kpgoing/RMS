package org.sel.rms.exception;

import org.sel.rms.status.AdminStatus;

/**
 * Created by Huxh on 2016/11/1.
 */
public class AdminException extends RuntimeException {
    AdminStatus adminStatus = AdminStatus.ERROR;

    public AdminStatus getAdminStatus() {
        return adminStatus;
    }

    public AdminException() {
    }

    public AdminException(String message, AdminStatus adminStatus) {
        super(message);
        this.adminStatus = adminStatus;
    }

    public AdminException(String message, Throwable cause, AdminStatus adminStatus) {
        super(message, cause);
        this.adminStatus = adminStatus;
    }

    public AdminException(Throwable cause, AdminStatus adminStatus) {
        super(cause);
        this.adminStatus = adminStatus;
    }

    public AdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, AdminStatus adminStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.adminStatus = adminStatus;
    }
}
