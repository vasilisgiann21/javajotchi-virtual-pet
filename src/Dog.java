public class Dog extends GeneralPet{
    protected String sound = "bark";

    public Dog(String name) {
        super(name, 15,10);
    }

    @Override
    public void makeSound(){
        System.out.println(this.getName() + " says : " + sound);
    }


}