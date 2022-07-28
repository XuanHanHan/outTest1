package org.optaplanner.examples.projectjobscheduling.domain.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.optaplanner.examples.projectjobscheduling.domain.AbstractPersistable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamInclude;
@XStreamAlias("PjsResource")
@XStreamInclude({
        GlobalResource.class,
        LocalResource.class
})
public abstract class Resource extends AbstractPersistable {

    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************
    @JsonIgnore
    public abstract boolean isRenewable();

}
