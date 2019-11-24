/** simple testbed for trying out DBM stuff directly from C,
 * as an aid in troubleshooting.
 */

#include <dbm.h>

int
main(int argc, char **argv){
	extern int errno;
	int i = dbminit("/tmp/xxxdb");
	printf("Ret %d errno %d\n", i, errno);
}
