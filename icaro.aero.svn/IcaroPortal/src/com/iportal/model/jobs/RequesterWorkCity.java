package com.iportal.model.jobs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iportal.model.City;

@Entity
@Table(name="tb_requester_work_city")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="requester_work_city",
								allocationSize=20
								)
public class RequesterWorkCity {
    private Long code;
    private City city;
    private Requester requester;

    /**
     * @return Returns the code.
     */
    @Id 
    @Column(name="requester_work_city_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }

    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * @return Returns the city.
     */
    @ManyToOne
    @JoinColumn(name="city_code")
    public City getCity() {
        return city;
    }

    /**
     * @param city The city to set.
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * @return Returns the requester.
     */
    @ManyToOne
    @JoinColumn(name="requester_code")
    public Requester getRequester() {
        return requester;
    }

    /**
     * @param requester The requester to set.
     */
    public void setRequester(Requester requester) {
        this.requester = requester;
    }

}
