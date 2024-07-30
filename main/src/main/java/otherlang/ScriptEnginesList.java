package otherlang;

import javax.script.ScriptEngineManager;

public class ScriptEnginesList {
    public static void main(String[] args) {
        // tag::main[]
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        System.out.println("Available script engines are: ");
        scriptEngineManager.getEngineFactories().forEach(factory ->
                System.out.println(factory.getLanguageName()));
        // end::main[]
    }
}
