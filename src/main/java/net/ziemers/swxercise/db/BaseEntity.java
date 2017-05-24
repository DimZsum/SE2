package net.ziemers.swxercise.db;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class BaseEntity {
    
    @Id
    @GeneratedValue
    protected Long id;
    
    public Long getId() {
        return id;
    }

}