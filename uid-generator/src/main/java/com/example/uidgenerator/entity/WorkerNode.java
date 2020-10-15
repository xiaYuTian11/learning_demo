package com.example.uidgenerator.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author TMW
 * @date 2020/10/15 11:07
 */
@Getter
@Setter
public class WorkerNode {
    private Long work_node_id;
    private String host_name;
    private String port;
    private Integer type;
    private Date launch_date;
    private Date modeified;
    private Date created;
}
