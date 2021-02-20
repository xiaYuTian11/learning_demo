package top.tanmw.mybatis.entity;

import java.io.Serializable;

/**
 * @author TMW
 * @date 2021/2/18 10:40
 */
public class Jobs implements Serializable {
    private static final long serialVersionUID = 1951884924987284915L;

    private String jobId;
    private String jobTitle;
    private String minSalary;
    private String maxSalary;

    public String getJobId() {
        return jobId;
    }

    public Jobs setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Jobs setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public Jobs setMinSalary(String minSalary) {
        this.minSalary = minSalary;
        return this;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public Jobs setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
        return this;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "jobId='" + jobId + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", minSalary='" + minSalary + '\'' +
                ", maxSalary='" + maxSalary + '\'' +
                '}';
    }
}
