/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.bulletin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * This class persist data to the <code>tb_subscriber</code> database table.
 * 
 * @author  YAGE (lourdes)
 * @version 2.0
 */

@Entity
@Table(name="tb_subscriber_topic")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="subscriber_topic",
								allocationSize=20
								)
public class SubscriberTopic {
	
    private Long code;

    private Subscriber subscriber;
    private Topic topic;
    

    /**
     * Creates a new instance of Account
     */
    public SubscriberTopic() {
        super();
    }
   
    
    @Id 
    @Column(name="subscriber_topic_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
    @ManyToOne
	@JoinColumn(name="subscriber_code")
	public Subscriber getSubscriber() {
		return subscriber;
	}
	
	 @ManyToOne
		@JoinColumn(name="topic_code")
		public Topic getTopic() {
			return topic;
		}
		
	    public void setTopic(Topic topic) {
			this.topic = topic;
		}
		

		public void setSubscriber(Subscriber subscriber) {
			this.subscriber = subscriber;
		}

		public void setCode(Long code) {
			this.code = code;
		}
	    
	
	}
