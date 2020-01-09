// Groovy lists already have stack capabilities

def stack = []

stack.push(1)
stack.push(2)
stack.push('Hello')
println stack.last()	// prints the string
println stack.pop()		// prints the string again
println stack.size()	// prints 2
 
println stack
