C
C	Fortran program to read two numbers and multiply
	integer num1, num2;
	integer result;

	read *, num1
	read *, num2
	result = num1 * num2
	write(6,100) result
100	format("result =", I4);
	end
