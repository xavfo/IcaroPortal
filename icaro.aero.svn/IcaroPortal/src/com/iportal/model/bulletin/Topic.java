/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.bulletin;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tb_topic")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="topic",
								allocationSize=20
								)
public class Topic {
	
	private Long code;
	
	private String description;
	
	private String name;
	
	private Boolean enabled;
	
	private Set <Bulletin>Bulletins;
	
	private Set <SubscriberTopic>subscriberTopics;


    /**
     * Creates a new instance of Topic
     */
    public Topic() {
        super();
    }
    
    @Id 
    @Column(name="topic_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
   
     @Column(name="topic_description")
    public String getDescription() {
        return description;
    }
       
    
    @Column(name="topic_name")
    public String getName() {
        return name;
    }
    
    @Column(name="topic_enabled")
    public Boolean getEnabled() {
        return enabled;
    }
    
    @OneToMany(mappedBy="subscriber")
	public Set<SubscriberTopic> getSubscriberTopics() {
		return subscriberTopics;
	}
    
	@OneToMany(mappedBy="topic")
	public Set<Bulletin> getBulletins() {
		return Bulletins;
	}

	/**
	 * @param bulletins The bulletins to set.
	 */
	public void setBulletins(Set<Bulletin> bulletins) {
		Bulletins = bulletins;
	}

	public void setSubscriberTopics(Set<SubscriberTopic> subscriberTopics) {
		this.subscriberTopics = subscriberTopics;
	}
    
   

	public void setCode(Long code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setName(String name) {
		this.name = name;
	}
}
