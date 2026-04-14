public class Cat extends GeneralPet{
    private String sound = "miaou";

    public Cat(String name){
        super(name, 10, 10);
    }

    @Override
    public void makeSound(){
        System.out.println(this.getName() + " says : " + sound);
    }
}