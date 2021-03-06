import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<Item> selectedItems = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime current_time = LocalTime.now();
        if(current_time.compareTo(this.openingTime)>0 && current_time.compareTo(this.closingTime)<0)
            return true;
        else
            return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return this.menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }


    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    //Part 3 - New feature Implementation
    private Item findItemByNameinSelectedList(String itemName){
        for(Item item: selectedItems) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToCart(String name, int price) {
        Item newItem = new Item(name,price);
        selectedItems.add(newItem);
    }

    public void removeFromCart(Item item) throws itemNotFoundException {
        Item itemToBeRemoved = findItemByNameinSelectedList(item.getName());
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(item.getName());
        menu.remove(item);
    }

    public List<Item> getSelectedItems() {
        return this.selectedItems;
    }
}
