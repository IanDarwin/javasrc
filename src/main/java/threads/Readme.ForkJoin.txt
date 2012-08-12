There are two demos of the Fork/Join framework here, named after
the ForkJoinTask which each subclasses:

RecursiveTaskDemo uses fork() and join() directly.

RecursiveActionDemo uses invokeAll(). Invoke() is just fork(); join(); and
invokeAll does this repeatedly until done.