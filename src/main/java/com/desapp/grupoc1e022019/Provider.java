package com.desapp.grupoc1e022019;

import java.util.List;
import java.util.stream.Collectors;

public class Provider {
    private List<Menu> menus;
    private String name;
    private String logo;
    private String city;
    private MapsLocation mapsLocation;
    private String description;
    private String webURL;
    private String email;
    private String telNumber;
    private Schedule schedule;
    private Integer deliveryMaxDistanceInKM;

    public Provider(String name, String logo, String city, MapsLocation mapsLocation, String description, String webURL, String email, String telNumber, Schedule schedule, Integer deliveryMaxDistanceInKM, List<Menu> menus) {

        this.name = name;
        this.logo = logo;
        this.city = city;
        this.mapsLocation = mapsLocation;
        this.description = description;
        this.webURL = webURL;
        this.email = email;
        this.telNumber = telNumber;
        this.schedule = schedule;
        this.deliveryMaxDistanceInKM = deliveryMaxDistanceInKM;
        this.menus = menus;

    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void addMenu(Menu menu) {
        checkMaxMenus();
        checkIdNotRepeated(menu.id());
        menus.add(menu);
    }

    private void checkIdNotRepeated(Integer id ) {
        if( menus.stream().anyMatch(menu -> menu.id().equals(id))){
            throw new RepeatedIDException("The menu's id is already in our system :)");
        }
    }

    private void checkMaxMenus(){
        if(this.menus.size() == 20){
            throw new MaximumMenusSizeException("Can't add more than twenty menus");
        }
    }

    public void deleteMenu(Menu menu) {
        menus.remove(menu);
    }


    public void updateMenu(int id, Menu updatedMenu) {
        menus = menus.stream().map((m) -> swap(m,updatedMenu)).collect(Collectors.toList());
    }

    private Menu swap(Menu menu, Menu updatedMenu) {
        if(menu.id().equals(updatedMenu.id())){
            return updatedMenu;
        }
        return menu;
    }
}
