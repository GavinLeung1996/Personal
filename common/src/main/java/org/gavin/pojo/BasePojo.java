package org.gavin.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
class BasePojo implements Serializable {
    private Date created;
    private Date updated;
}