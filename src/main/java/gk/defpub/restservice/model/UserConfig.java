package gk.defpub.restservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * UserConfig class.
 * <p>
 * Date: Dec 3, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Entity
public class UserConfig {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(columnDefinition = "char(32)")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @Column
    @JsonIgnore
    private String userId;
    @Column
    @NotBlank
    private String cronSchedule;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCronSchedule() {
        return cronSchedule;
    }

    public void setCronSchedule(String cronSchedule) {
        this.cronSchedule = cronSchedule;
    }
}
