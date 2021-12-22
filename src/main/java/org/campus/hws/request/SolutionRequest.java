package org.campus.hws.request;

import lombok.Data;
import org.campus.hws.entity.TaskType;


@Data
public class SolutionRequest {
    private TaskType taskType;
}
