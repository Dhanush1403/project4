package simplf;

public class AssocList {
    final String name;
    Object value;
    final AssocList next;

    AssocList(String nameIn, Object valueIn, AssocList nextIn) {
        name = nameIn;
        value = valueIn;
        next = nextIn;
    }

    // Add a new (name, value) binding to the front of the list
    public static AssocList prepend(AssocList list, String name, Object value) {
        return new AssocList(name, value, list);
    }

    // Check if a name exists in the list
    public static boolean contains(AssocList list, String name) {
        for (AssocList current = list; current != null; current = current.next) {
            if (current.name.equals(name)) return true;
        }
        return false;
    }

    // Get the value associated with a name
    public static Object lookup(AssocList list, String name) {
        for (AssocList current = list; current != null; current = current.next) {
            if (current.name.equals(name)) return current.value;
        }
        throw new RuntimeException("Undefined variable '" + name + "'");
    }

    // Update the value of an existing name
    public static AssocList update(AssocList list, String name, Object newValue) {
        for (AssocList current = list; current != null; current = current.next) {
            if (current.name.equals(name)) {
                current.value = newValue;
                return list;
            }
        }
        throw new RuntimeException("Undefined variable '" + name + "'");
    }
}
