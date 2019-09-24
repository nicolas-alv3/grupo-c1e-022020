package com.desapp.grupoc1e022019.model;

import com.desapp.grupoc1e022019.exception.InsufficientCreditException;
import com.desapp.grupoc1e022019.exception.MaximumMenusSizeException;
import com.desapp.grupoc1e022019.exception.RepeatedIDException;
import com.desapp.grupoc1e022019.model.providerComponents.providerState.ProviderState;
import com.desapp.grupoc1e022019.model.providerComponents.schedule.BussinessTime;
import com.desapp.grupoc1e022019.model.providerComponents.schedule.Schedule;
import com.desapp.grupoc1e022019.model.providerComponents.location.Address;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

public class Provider {

    private String name;
    private String logo;
    private String city;
    private Address address;
    private String description;
    private String webURL;
    private String email;
    private String telNumber;
    private Schedule schedule;
    private Double deliveryMaxDistanceInKM;
    private List<Menu> menus;
    private Credit credit;
    private Integer strikesMenu;
    private ProviderState providerState;

    public Provider(String name, String logo, String city, Address address, String description, String webURL,
                    String email, String telNumber, Schedule schedule, Credit credit, Double deliveryMaxDistanceInKM,
                    List<Menu> menus,ProviderState providerState,Integer strikesMenu) {

        setName(name);
        setLogo(logo);
        setCity(city);
        setAddress(address);
        setDescription(description);
        setWebURL(webURL);
        setEmail(email);
        setTelNumber(telNumber);
        setSchedule(schedule);
        setDeliveryMaxDistanceInKM(deliveryMaxDistanceInKM);
        this.menus = menus;
        this.credit = credit;
        setStrikesMenu(strikesMenu);
        this.providerState = providerState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public Double getDeliveryMaxDistanceInKM() {
        return deliveryMaxDistanceInKM;
    }

    public void setDeliveryMaxDistanceInKM(Double deliveryMaxDistanceInKM) {
        this.deliveryMaxDistanceInKM = deliveryMaxDistanceInKM;
    }

    public Integer getStrikesMenu() {
        return strikesMenu;
    }

    public void setStrikesMenu(Integer strikesMenu) {
        this.strikesMenu = strikesMenu;
    }

    public void setProviderState(ProviderState providerState){
        this.providerState = providerState;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void addMenu(Menu menu) {
        checkMaxMenus();
        checkIdNotRepeated(menu.getId());
        menus.add(menu);
    }

    public void deleteMenu(Menu menu) {
        menus.remove(menu);
    }

    //TODO
    // HACERLO POR SERVICE (BASE DE DATO)
    public void updateMenu(int id, Menu updatedMenu) {
        menus = menus.stream().map((m) -> swap(m,updatedMenu)).collect(Collectors.toList());
    }

    private Menu swap(Menu menu, Menu updatedMenu) {
        //TODO
        // BORRAR ESTO CUANDO ESTE EL SERVICE
        if(menu.getId() == updatedMenu.getId()){
            return updatedMenu;
        }
        return menu;
    }

    public void recievesCredit(Credit receiveCredit){
        this.credit = this.credit.sum(receiveCredit);
    }

    public Credit getCredit(){
        return this.credit;
    }

    public Credit withdrawCredit(Credit amountToWithdraw){
        if(! this.credit.isGreaterOrEqual(amountToWithdraw)){
            throw new InsufficientCreditException("Insufficient credits");
        }

        this.credit = this.credit.minus(amountToWithdraw);
        return amountToWithdraw;
    }

    public void addAStrike(){
        providerState.addStrike(this);
    }

    public boolean isPenalized(){
        return providerState.isPenalized();
    }

    public boolean isNormalProvider(){
        return providerState.isNormalProvider();
    }

    public String getProviderStateName(){
        return providerState.toString();
    }

    public Map<DayOfWeek, BussinessTime> getSchedule(){
        return schedule.getDaysAndBussinessTime();
    }

    public void setSchedule(Schedule schedule){
        this.schedule = schedule;
    }

    private void checkIdNotRepeated(Integer id ) {
        if( menus.stream().anyMatch(menu -> menu.getId() == id)){
            throw new RepeatedIDException("The menu's id is already in our system :)");
        }
    }

    private void checkMaxMenus(){
        if(this.menus.size() == 20){
            throw new MaximumMenusSizeException("Can't add more than twenty menus");
        }
    }
}
