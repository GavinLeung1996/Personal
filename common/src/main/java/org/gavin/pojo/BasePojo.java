package org.gavin.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
class BasePojo implements Serializable {
    private static final long serialVersionUID = -2184744130950302747L;
    private Date created;
    private Date updated;
}
