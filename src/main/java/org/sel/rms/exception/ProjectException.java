package org.sel.rms.exception;

import org.sel.rms.status.ProjectStatus;

/**
 * Created by xubowei on 01/11/2016.
 */
public class ProjectException extends RuntimeException {
    ProjectStatus projectStatus = ProjectStatus.ERROR;

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public ProjectException(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public ProjectException(String message, ProjectStatus projectStatus) {
        super(message);
        this.projectStatus = projectStatus;
    }

    public ProjectException(String message, Throwable cause, ProjectStatus projectStatus) {
        super(message, cause);
        this.projectStatus = projectStatus;
    }

    public ProjectException(Throwable cause, ProjectStatus projectStatus) {
        super(cause);
        this.projectStatus = projectStatus;
    }
}
