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
            localEnv.define(param, param.lexeme, args.get(i));  // using mutating define()
        }
    
        Environment previous = interpreter.environment;
        Object result = null;
        try {
            interpreter.environment = localEnv;
            for (Stmt stmt : declaration.body) {
                result = interpreter.execute(stmt);
            }
        } finally {
            interpreter.environment = previous;
        }
        return result;
    }
    
    

    @Override
    public String toString() {
        return "<fn >";
    }

}