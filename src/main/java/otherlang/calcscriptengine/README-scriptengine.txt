This is a demo of a do-it-yourself scripting engine, believed to
be JSR-223 compliant, for the rare case of needing to implement
an interface to a new or rare scripting language.

The ScriptEngine impl consists of these source files PLUS
the file META-INF/services/javax.script.ScriptEngineFactory
(which, for Maven, is stored in src/main/resources).
This file just has just one line for the name of each
ScriptEngineFactory implementation(s).
