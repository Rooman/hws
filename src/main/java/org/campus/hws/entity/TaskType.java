package org.campus.hws.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskType {
    ONLINE_SHOP("OS", "Online shop"),
    FILE_ANALYSER("FA", "File analyser"),
    FILE_MANAGER("FM", "File manager"),
    LINKED_LIST("LL", "Linked list"),
    WEB_SERVER("WS", "Web server"),
    ECHO_SERVER("ES", "Echo Server");


    private final String id;
    private final String name;

    public  static TaskType getById(String id){
        for (TaskType taskType: values()){
            if(taskType.id.equalsIgnoreCase(id)){
                return taskType;
            }
        }
        throw new IllegalArgumentException("No found Type for id: " + id);
    }
}
