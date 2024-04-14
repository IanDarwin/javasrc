package ffi;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.Optional;
import java.util.function.Consumer;

// tag::main[]
/**
 * Demo program calling two methods in C code, updatesketch() and
 * pythonsketch(). The C code hello-ffi.c has a list of strings that pythonsketch()
 * can retrieve by index number, and an updatesketch() that allows putting
 * a new sketch title into the slot by number. So we update slot #2
 * and then retrieve it to prove that we successfully passed the string
 * in, had it stored by the C code, and that we then retrieved it.
 */
public class CallCFromJava {
    void main() throws Throwable {
        String newSketch = "Killer Bunny";

        try (Arena arena = Arena.ofConfined()) {
            // Allocate off-heap memory and copy 'message' into it
            MemorySegment nativeString = arena.allocateUtf8String(newSketch);
            // Name of C function to find and invoke
            String updateMethod = "updatesketch", getMethod = "pythonsketch";
            // First, get an instance of the native linker
            Linker linker = Linker.nativeLinker();
            // Now get the address of the C function signature
            SymbolLookup ourLib = SymbolLookup.libraryLookup(
                    "/home/ian/git/javasrc/main/src/main/java/ffi/hello-ffi.so", arena);
            Optional<MemorySegment> segment = ourLib.find(updateMethod);
            if (!segment.isPresent()) {
                throw new IllegalArgumentException(STR."Method \{updateMethod} not found!");
            }
            MemorySegment foreignFuncAddr = segment.get();
            // Create argument-list description of the C function
            FunctionDescriptor update_sig=
                    FunctionDescriptor.of(ValueLayout.JAVA_LONG, // return
                            ValueLayout.JAVA_LONG,  // first argument
                            ValueLayout.ADDRESS);   // second
            // Obtain a downcall handle for the C function
            MethodHandle strlen = linker.downcallHandle(foreignFuncAddr, update_sig);
            // Finally, what you've all been waiting for: call a C function directly from Java
            var ret = (long) strlen.invokeExact(2L, nativeString);
            System.out.println(STR."Update function returned \{ret}");

            // Now call getSketch(2) to prove that it got updated
            segment = ourLib.find(getMethod);
            if (!segment.isPresent()) {
                throw new IllegalArgumentException(STR."Method \{getMethod} not found!");
            }
            foreignFuncAddr = segment.get();
            // Create argument-list description of the C function
            update_sig=
                    FunctionDescriptor.of(ValueLayout.ADDRESS, // return
                            ValueLayout.JAVA_LONG);  // only input argument
            // Obtain a downcall handle for the C function
            MethodHandle getter = linker.downcallHandle(foreignFuncAddr,
                    update_sig);
            // Once again we call a C function directly from Java
            var retStr = (MemorySegment)getter.invokeExact(2L);
            Consumer<MemorySegment> cleanup = s -> {
                // Maybe some cleanup here what we had allocated in native space
            };
            retStr = retStr.reinterpret(newSketch.length() + 1, arena, cleanup);
            System.out.println(STR."Update function returned \{retStr}");
            String updatedValue = retStr.getUtf8String(0);
            System.out.println(STR."Retrieved updatedValue as \{updatedValue}");
        }
    }
}
// end::main[]
