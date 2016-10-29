package org.sel.rms.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by xubowei on 29/10/2016.
 */
@Entity
@Table(name = "Project", schema = "RMS", catalog = "")
public class ProjectEntity {
    private int idProject;
    private String name;
    private String source;
    private Date projectTime;
    private String master;
    private BigDecimal funds;
    private Date publishTime;
    private String introduction;
    private byte checkStatus;
    private String param1;
    private String param2;

    @Id
    @Column(name = "idProject", nullable = false)
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
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
    @Column(name = "source", nullable = false, length = 45)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "projectTime", nullable = false)
    public Date getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(Date projectTime) {
        this.projectTime = projectTime;
    }

    @Basic
    @Column(name = "master", nullable = false, length = 45)
    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    @Basic
    @Column(name = "funds", nullable = false, precision = 2)
    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    @Basic
    @Column(name = "publishTime", nullable = false)
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "introduction", nullable = false, length = -1)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "checkStatus", nullable = false)
    public byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Basic
    @Column(name = "param1", nullable = true, length = 45)
    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    @Basic
    @Column(name = "param2", nullable = true, length = 45)
    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (idProject != that.idProject) return false;
        if (checkStatus != that.checkStatus) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (projectTime != null ? !projectTime.equals(that.projectTime) : that.projectTime != null) return false;
        if (master != null ? !master.equals(that.master) : that.master != null) return false;
        if (funds != null ? !funds.equals(that.funds) : that.funds != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (param1 != null ? !param1.equals(that.param1) : that.param1 != null) return false;
        if (param2 != null ? !param2.equals(that.param2) : that.param2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProject;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (projectTime != null ? projectTime.hashCode() : 0);
        result = 31 * result + (master != null ? master.hashCode() : 0);
        result = 31 * result + (funds != null ? funds.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (int) checkStatus;
        result = 31 * result + (param1 != null ? param1.hashCode() : 0);
        result = 31 * result + (param2 != null ? param2.hashCode() : 0);
        return result;
    }
}
