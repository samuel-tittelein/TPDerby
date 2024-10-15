//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        CreationBD.main(new String[] { "bd_prets"});
        RemplissageBD.main(new String[] { "bd_prets", "Client", "1", "John Doe", "John", "30" });
    }

}
