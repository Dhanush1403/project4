package simplf;

class Environment {
    private AssocList values;
    final Environment enclosing;

    Environment() {
        this.values = null;
        this.enclosing = null;
    }

    Environment(Environment enclosing) {
        this.values = null;
        this.enclosing = enclosing;
    }

    Environment(AssocList assocList, Environment enclosing) {
        this.values = assocList;
        this.enclosing = enclosing;
    }

    Environment define(Token varToken, String name, Object value) {
        this.values = AssocList.prepend(this.values, name, value);
        return this;
    }
    

    void assign(Token name, Object value) {
        if (AssocList.contains(values, name.lexeme)) {
            AssocList updated = AssocList.update(values, name.lexeme, value);
            this.values = updated;
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    Object get(Token name) {
        if (AssocList.contains(values, name.lexeme)) {
            return AssocList.lookup(values, name.lexeme);
        }

        if (enclosing != null) {
            return enclosing.get(name);
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }
}
