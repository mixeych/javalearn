package com.company.bandlist;

class Band {

    private Long id;

    private String name;
    
    private String mainStar;

    private int membersNumber;
 
    public Band() {
    }
 
    public Band(String name, String mainStar, int membersNumber) {
        this.name = name;
        this.mainStar = mainStar;
        this.membersNumber = membersNumber;
    }
    
    public Band(Long id, String name, String mainStar, int membersNumber) {
        this.id = id;
        this.name = name;
        this.mainStar = mainStar;
        this.membersNumber = membersNumber;
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
 
    public String getMainStar() {
        return mainStar;
    }
 
    public void setMainStar(String mainStar) {
        this.mainStar = mainStar;
    }
 
    public int getMembersNumber() {
        return membersNumber;
    }
 
    public void setMembersNumber(int membersNumber) {
        this.membersNumber = membersNumber;
    }
 
    @Override
    public String toString() {
        return "Band{" + "id=" + id + ", name=" + name + ", mainStar=" + mainStar + ", membersNumber=" + membersNumber + '}';
    }
}
