package org.sel.rms.exception;

import org.sel.rms.exception.annoation.WithStatus;
import org.sel.rms.status.PaperStatus;

/**
 * Created by xubowei on 30/10/2016.
 */
public class PaperException extends RuntimeException {

    PaperStatus paperStatus = PaperStatus.ERROR;

    public PaperException() {
    }

    public PaperException(String message) {
        super(message);
    }

    public PaperException(String message, PaperStatus paperStatus) {
        super(message);
        this.paperStatus = paperStatus;
    }

    public PaperException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaperException(String message, Throwable cause, PaperStatus paperStatus) {
        super(message, cause);
        this.paperStatus = paperStatus;
    }

    public PaperException(Throwable cause) {
        super(cause);
    }

    public PaperStatus getPaperStatus() {
        return paperStatus;
    }
}
