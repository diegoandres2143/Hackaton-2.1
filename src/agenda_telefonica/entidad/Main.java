package agenda_telefonica.entidad;

public class Main {
    public static void main(String[] args) {

        Contacto contacto = new Contacto();
        System.out.println(contacto.toString());

        Contacto contacto2 = new Contacto();
        contacto2.setNombre("");
        System.out.println(contacto2.toString());
    }
}
