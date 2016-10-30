package org.sel.rms.status;

import org.sel.rms.status.inter.Statusable;

/**
 * Created by xubowei on 30/10/2016.
 */
public enum  PaperStatus implements Statusable {
    SUCCESS(0),ERROR(1),DATABASE_ERROR(2);


    private int status;

    PaperStatus(int status) {
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }


}
