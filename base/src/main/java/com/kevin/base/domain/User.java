package com.kevin.base.domain;

import com.kevin.base.enums.City;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:Kevin
 * @Date:Created in 21:20 2020/11/23
 */
public class User {

    private Long id;

    private String name;
    private City city;

    private City[] workCitys;

    private List<City> lifeCitys;
    private Resource configFileLocation;

    private Company company;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public City[] getWorkCitys() {
        return workCitys;
    }

    public void setWorkCitys(City[] workCitys) {
        this.workCitys = workCitys;
    }

    public List<City> getLifeCitys() {
        return lifeCitys;
    }

    public void setLifeCitys(List<City> lifeCitys) {
        this.lifeCitys = lifeCitys;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Kevin");
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", workCitys=" + Arrays.toString(workCitys) +
                ", lifeCitys=" + lifeCitys +
                ", configFileLocation=" + configFileLocation +
                ", company=" + company +
                '}';
    }

    @PostConstruct
    public void init() {
        System.out.println("Init user , id : " + id);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy user , id : " + id);
    }
}
