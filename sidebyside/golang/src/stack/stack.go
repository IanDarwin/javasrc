// You should just use built-in lists with slice operators in your code
// var intStack []int
// intStack = append(intStack, 7)
// Unpacking similar to Python: gets last item and updates stack
// popped, intStack = intStack[len(intStack)-1], intStack[:len(intStack)-1]
// Test for empty stack:
// len(intStack) == 0
// peek at the top of the stack:
// intStack[len(intStack)-1]

// However, so you can see that it's doable, here's a stack class
// from rosettacode.org/wiki/Stack#Go

package main
 
import "fmt"
 
type stack []interface{}
 
func (k *stack) push(s interface{}) {
    *k = append(*k, s)
}
 
func (k *stack) pop() (s interface{}, ok bool) {
    if k.empty() {
        return
    }
    last := len(*k) - 1
    s = (*k)[last]
    *k = (*k)[:last]
    return s, true
}
 
func (k *stack) peek() (s interface{}, ok bool) {
    if k.empty() {
        return
    }
    last := len(*k) - 1
    s = (*k)[last]
    return s, true
}
 
func (k *stack) empty() bool {
    return len(*k) == 0
}
 
func main() {
    var s stack
    fmt.Println("new stack:", s)
    fmt.Println("empty?", s.empty())
    s.push(3)
    fmt.Println("push 3. stack:", s)
    fmt.Println("empty?", s.empty())
    s.push("four")
    fmt.Println(`push "four" stack:`, s)
    if top, ok := s.peek(); ok {
        fmt.Println("top value:", top)
    } else {
        fmt.Println("nothing on stack")
    }
    if popped, ok := s.pop(); ok {
        fmt.Println(popped, "popped.  stack:", s)
    } else {
        fmt.Println("nothing to pop")
    }
}
