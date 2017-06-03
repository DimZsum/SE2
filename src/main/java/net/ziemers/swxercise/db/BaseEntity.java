package net.ziemers.swxercise.db;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    
    @Id
    @GeneratedValue
    protected Long id;
    
    public Long getId() {
        return id;
    }

}