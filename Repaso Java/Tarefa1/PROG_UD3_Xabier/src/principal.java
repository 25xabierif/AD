public class principal {
    public static void main(String[] args) throws Exception {

        Mago mago1 = new Mago("Gandalf", 250, 75, Hechizo.piromancia);

        Monstruo monstruo1 = new Monstruo("Smoug", 450, 35, Tipo.orgro);

        Bosque bosque1 = new Bosque("Fraga", 3, monstruo1);

        while(mago1.getVida() > 0 && monstruo1.getVida() > 0){
            System.out.println(mago1.getNombre()+" ataca a "+monstruo1.getNombre()+".\n Pierde "+mago1.getNivelMagia()+" puntos de vida.");
            monstruo1.recibirDaño(mago1.getNivelMagia());
            System.out.println(monstruo1.getNombre()+" contraataca. \n"+mago1.getNombre()+" pierde "+monstruo1.getFuerza()+" puntos de vida.");
            monstruo1.atacar(mago1);
            if(mago1.getVida() <= 0){
                System.out.println(mago1.getNombre()+" ha sido derrotado. "+monstruo1.getNombre()+" domina "+bosque1.getNombre()+".");
            }else if(monstruo1.getVida() <= 0){
                System.out.println(monstruo1.getNombre()+" ha sido derrotado. "+mago1.getNombre()+" domina "+bosque1.getNombre()+".");
            }
            System.out.println(mago1.getNombre()+" vida restante: "+mago1.getVida()+".\n"+monstruo1.getNombre()+" vida restante: "+monstruo1.getVida()+".");
        }
    }
}
