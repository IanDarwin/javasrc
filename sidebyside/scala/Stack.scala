class Stack[T] {
  private var elements: List[T] = Nil
  def push(x: T) { elements = x :: elements }
  def peek: T = elements.head
  def pop(): T = {
    val currentTop = peek
    elements = elements.tail
    currentTop
  }
}

val iStack = Stack[Int]
iStack.push(1)
iStack.push(2)
iStack.push(3)
println(iStack.pop) // prints 3

val pStack = Stack[Person]
pStack.push(Person("Ian"))
pStack.push(Person("Jodi"))
println(pStack.pop) // prints Jodi

