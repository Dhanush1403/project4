package simplf;
 
import java.util.List;

class SimplfFunction implements SimplfCallable {
    private final Stmt.Function declaration;
    private Environment closure;
    
    SimplfFunction(Stmt.Function declaration, Environment closure) {
        this.declaration = declaration;
        this.closure = closure;
    }

    public void setClosure(Environment environment) {
        this.closure = environment;
    }
    

    @Override
    public Object call(Interpreter interpreter, List<Object> args) {
        Environment localEnv = new Environment(closure);
    
        for (int i = 0; i < declaration.params.size(); i++) {
            Token param = declaration.params.get(i);
            localEnv = localEnv.define(param, param.lexeme, args.get(i));
        }
    
        // Save previous env and restore after call
        Environment previous = interpreter.environment;
        try {
            interpreter.environment = localEnv;
            for (Stmt stmt : declaration.body) {
                interpreter.execute(stmt);
            }
        } finally {
            interpreter.environment = previous;
        }
    
        return null; // No return values in Simplf
    }
    

    @Override
    public String toString() {
        return "<fn >";
    }

}