package com.andela.plans.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "plan")
@Data
public class Plan extends BaseEntity {
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "limited")
    private boolean limited = false;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public boolean isValid() {
        if (limited && (startDate == null || endDate == null)) {
            return false;
        }
        return true;
    }
}
