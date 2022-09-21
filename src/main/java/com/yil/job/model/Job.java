package com.yil.job.model;

import com.yil.job.base.IEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode
@Entity
@Data
@Table(name = "JOB",
        schema = "PRS",
        indexes = {
                @Index(name = "IDX_JOB_ID", columnList = "ID")
        }

)
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Job implements IEntity {
    @Id
    @SequenceGenerator(name = "JOB_SEQUENCE_GENERATOR",
            schema = "PRS",
            sequenceName = "SEQ_JOB_ID",
            allocationSize = 1)
    @GeneratedValue(generator = "JOB_SEQUENCE_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", nullable = false, length = 1000)
    private String name;
    @Column(name = "PARENT_ID")
    private Integer parentId;
    @ColumnDefault(value = "true")
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "CREATED_USER_ID")
    private Long createdUserId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFY_DATE")
    private Date lastModifyDate;
    @Column(name = "LAST_MODIFY_USER_ID")
    private Long lastModifyUserId;

}
